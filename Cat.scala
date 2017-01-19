
class Cat {
  val dangerous = false
}

class Tiger(
  override val dangerous: Boolean,
  private var age: Int // You can prefix parametric fields just like class fields
) extends Cat
