
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
