/*
  This is a value class. Value classes compile into Java bytecode and are boxed
  and unboxed. For a class to be made a value class, it must have exactly one
  parameter and it must haven nothing inside beside defs. No other class can
  extend a value class, and a value class cannot redefine equals or hashcode.

  To declare a value class, extend 'AnyVal'
*/
class Dollars(val amount: Int) extends AnyVal { // Parametric fields!
  override def toString() = "$" + amount
}

val dollar = new Dollars(10000)
println(dollar.toString)
println(dollar.amount)

/*
  You can even define multiple value types that are all backed by the same Int
  value, because why wouldn't you be able to?
*/
class SwissFrances(val amount: Int) extends AnyVal {
  override def toString() = amount + "CHF"
}

val francs = new SwissFrances(10000)
println(francs.toString)
println(francs.amount)
