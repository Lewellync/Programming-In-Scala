
class Cat {
  val dangerous = false
}

// This is useful when all a subclass does is override, set, or create new fields
class Tiger(
  override val dangerous: Boolean,
  private var age: Int // You can prefix parametric fields just like class fields
) extends Cat

// As in Elements, this is just shorthand for :
class TigerLong(danger: Boolean, agethough: Int) extends Cat {
  override val dangerous = danger
  private var age = agethough
}

// Messing around with garbage
abstract class Car {
  def speed: Float
  def speedToString: String = speed + " mph"
  def length: Float
  def height: Float
}

class Honda extends Car {
  def make = "Honda"
  val speed: Float = 120
  val length: Float = 50
  val height: Float = 40
}
