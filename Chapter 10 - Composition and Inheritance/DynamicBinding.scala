
abstract class Element {
  def demo() = {
    println("Element's implementation invoked")
  }
}

class ArrayElement extends Element {
  override def demo() = {
    println("ArrayElement's implementation invoked")
  }
}

class LineElement extends ArrayElement {
  override def demo() = {
    println("LineElement's implementation invoked")
  }
}

class UniformElement extends Element

def invokeDemo(e: Element) = {
  e.demo()
}

// Sometimes you don't want to let a subclass override a member, so you can
// declare it as a final. You can also declare a class as final, to prevent it
// from being subclassed at all.
