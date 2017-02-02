
// This is some code without using traits to enrich classes.

class Point(val x: Int, val y: Int)

class Rectangle(val topLeft: Point, val bottomRight: Point) {

  // Convenience methods implemented here.
  def left = topLeft.x
  def right = bottomRight.x
  def width = right - left
}

abstract class Component {
  def topLeft: Point
  def bottomRight: Point

  // Notice these are the same methods as those defined in Rectangle above.
  // Can we avoide this repetition? Of course we can!
  def left = topLeft.x
  def right = bottomRight.x
  def width = right - left
}


/*
  Now let's do this with an enrichment trait, that will have two abstract methods
  as Component does above. Much cleaner than code repetition.
*/

trait Rectangular {
  def topLeft: Point
  def bottomRight: Point

  def left = topLeft.x
  def right = bottomRight.x
  def width = right - left
}

abstract class Component extends Rectangular {
  // Other methods...
}

class Rectangle(val topLeft: Point, val bottomRight: Point)
    extends Rectangular {
  // Other methods...
}
