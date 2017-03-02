package element

// Factory Object
object Element {

  /*
    This is a class defined using parametric fields. The below is shorthand
    for an equivalent definition such as:

    private class ArrayElement(conts: Array[String]) extends Element {
      val contents: Array[String] = conts;
    }
  */
  private class ArrayElement(
    val contents: Array[String]
  ) extends Element

  /*
    You're more used to this kind of definition. Note that even though contents
    is a def in Element, it is a val here. A def is the most abstract form for
    defining a member, and can be overriden by a def, val, lazy val, or object.
    Because contents is not implemented, no 'override' is necessary.
  */
  private class LineElement(s: String) extends Element {
    val contents = Array(s)
    override def width = s.length
    override def height = 1
  }

  /*
    Private and override function as you'd remember from Java. Here you can see
    that you need to override width and height in the parameter list, because
    they are now vals and no longer defs.
  */
  private class UniformElement(
    ch: Char,
    override val width: Int,
    override val height: Int
  ) extends Element {
    private val line = ch.toString * width
    def contents = Array.fill(height)(line)
  }

  /*
    These are factory methods that take different parameters and create new
    layout elements fromt hem. Nothing to different from Java jere.
  */
  def elem(contents: Array[String]): Element =
    new ArrayElement(contents)

  def elem(chr: Char, width: Int, height: Int): Element =
    new UniformElement(chr, width, height)

  def elem(line: String): Element =
    new LineElement(line)
}

import Element.elem

abstract class Element {

  // This is an unimplmented method (notice the lack of '=')
  def contents:  Array[String]

  // These methods are both implemented. You can have object fields as a
  // definition or a variable, it's up to you.
  def width: Int = contents(0).length
  def height: Int = contents.length

  // Uses widen to match the width of uneven elements.
  def above(that: Element): Element = {
    val this1 = this widen that.width
    val that1 = that widen this.width
    elem(this1.contents ++ that1.contents)
  }

  // Uses heighten to match the height of uneven elements.
  def beside(that: Element): Element = {
    val this1 = this heighten that.height
    val that1 = that heighten this.height
    elem(
      for ((line1, line2) <- this1.contents zip that1.contents)
      yield line1 + line2)
  }

  // Creates a UniformElement of spaces to append to either side of an element.
  def widen(w: Int): Element =
    if (w <= width) this
    else {
      val left = elem(' ', (w - width) / 2, height)
      var right = elem(' ', w - width - left.width, height)
      left beside this beside right
    }

  // Creates a UniformElement of spaces to append to the top and bottom of an
  // element.
  def heighten(h: Int): Element =
    if (h <= height) this
    else {
      val top = elem(' ', width, (h - height) / 2)
      var bot = elem(' ', width, h - height - top.height)
      top above this above bot
    }

  override def toString = contents mkString "\n"
}

// For testing

/*
object LayoutElement {
  def main(args: Array[String]) {
    println("example [\n" + example + "\n]")
  }

  def example = {
    val column1 = elem("hello") above elem("***")
    val column2 = elem("***") above elem("world")
    column1 beside column2
  }
}

// When running large Scala files as a script (on the command line), you must
// explicitly call the main function. REMEMBER THIS.
LayoutElement.main(args)
*/
