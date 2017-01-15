
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
// fields of the containing object (it does not change mutable state)

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
  def contents: Array[String] = conts // '=' means this is an implemented method
}

val ae = new ArrayElement(Array("hello","world"))
println(ae.width)

// Subtyping means that a vlue of the subclass can be used whenever a value of
// the superclass is required. Variable 'e' is defined to be type Element, so
// its initializing value should also be an Element.

val e: Element = new ArrayElement(Array("hello"))
