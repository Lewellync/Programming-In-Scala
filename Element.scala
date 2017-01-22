
// It makes sense to include a member 'contents' in our Elemenet that represents
// the actual contents of the element. This will be represented by an array of
// strings, and is considered an abstract member of class Element because it has
// no implementation

// Some terminology
// Methods that have an implementation are 'concrete'
// Those without are 'abstract'
// Element 'declares' the abstract method contents
// Element currently 'defines' no concrete methods

abstract class Element { // A class with abstract members must be abstract
  def contents: Array[String] // No implementation means no body or '='

// Parameterless methods are common in Scala, and there are two types: Those
// with the paren intact, and those that remove it. Convertion suggests that
// if a method has no params and no side effects, you leave off the parens.

// Another note, parameterless methods should only be used when there are no
// params (obviously) AND the method acceses mutable state only by reading
// fields of the containing object (it does not change mutable state)fdrfff

// For height and width, they could be declared as fields instead of defs. The
// tradeoffs are a def takes no memory space in the Element object, and a field
// may have slightly faster access because the value is precomputed.

  def height: Int = contents.length // No () makes it a parameterless + no s.e.
  def width: Int = if (height == 0) 0 else contents(0).length
}

// Extending has two effects: ArrayElement inherits all non-private members of
// Element, and ArrayElement is a subtype of type 'Element'. ArrayElement
// overrides contents, but inherits height and width

class ArrayElement(conts: Array[String]) extends Element {
  override def contents: Array[String] = conts  // '=' means this is an
                                                // implemented method, and since
                                                // this is implementing an
                                                // abstract class, the override
                                                // isn't necessary
}

val ae = new ArrayElement(Array("hello","world"))
println(ae.width)

// Subtyping means that a vlue of the subclass can be used whenever a value of
// the superclass is required. Variable 'e' is defined to be type Element, so
// its initializing value should also be an Element.

val e: Element = new ArrayElement(Array("hello"))

// In Scala, fields and methods belong to the same namespace, so a field can
// override a parameterless method

class ArrayElementOverride(conts: Array[String]) extends Element {
  val contents: Array[String] = conts // Overrode parameterless method with field
}

// We also have parametric fields, which are confusing but dope. For more on
// these, checkout Cat.Scala

class ArrayElementParametric(
  val contents: Array[String] // Note: this is prefixed by 'val'. This is
                              // shorthand that defines at the same time a param
                              // and field with the same name. It's just like if
                              // it had been written like line 52-54
) extends Element


// Since LineElement extends ArrayElement, and AE's constructor takes a param,
// Array[String], LE needs to pass an arg to the primary constructor of its
// superclass.
class LineElement(s: String) extends ArrayElement(Array(s)) {
  override def width = s.length
  override def height = 1
}

class UniformElement(
  ch: Char,
  override val width: Int,
  override val height: Int
) extends Element {
  private val line = ch.toString * width // I have no idea how this works
  def contents = Array.fill(height)(line)
}
