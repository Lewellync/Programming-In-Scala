
// 15.1 - Defining case classes
abstract class Expr
case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class UnOp(operator: String, arg: Expr) extends Expr
case class BinOp(operator: String,
                 left: Expr, right: Expr) extends Expr

// 15.1.1
val v = Var("x")  // Factory methods added
val op = BinOp("+", Number(1), v) // Nested factory methods
v.name // All arguments are implicitly vals
op.left
println(op) // Natural implementations of toString, hashCode, and equals
op.right == Var("x")
op.copy(operator = "-") // Changing an already created val by specifying the params

// 15.2 - The simplifyTop function, which does a pattern match
def simplifyTop(expr: Expr): Expr = expr match {
  case UnOp("-", UnOp("-", e)) => e // Double negation
  case BinOp("+", e, Number(0)) => e // Adding 0
  case BinOp("*", e, Number(1)) => e // Multiply by 1
  case _ => expr // Anything that gets this far applies to this.
}

// 15.3 - A pattern match with an empty "default" case
expr match {
  case BinOp(op, left, right) => println(expr + " is a binary operation")
  case _ => // Blank is valid (and necessary), it does nothing and returns type Unit
}

// 15.4 - A pattern match with wildcard patterns
expr match {
  case BinOp(_, _, _) => println(expr + " is a binary operation") // WILDCARD
  case _ =>
}

// 15.5 A pattern match with constant patterns
def describe(x: Any) = x match {
  case 5 => "five"
  case true => "truth!"
  case "hello" => "hi!"
  case Nil => "the empty list"
  case _ => "something else"
}

// 15.6 - A pattern match with a variable pattern
expr match {
  case 0 => "zero"
  case somethingElse => "not zero: " + somethingElse
}

// 15.6.1 - Scala uses upper case to signify constants in pattern matching
import math.{E, Pi}
E match {
  case Pi => "strange math? Pi = " + Pi // It'll never hit this, since it's uppercase
  case pi => "this works though" // It'll hit this, since it's not a constant
}

// 15.7 - A pattern match with a constructor pattern
expr match {
  case BinOp("+", e, Number(0)) => println("a deep match")
  case _ =>
}

// 15.8 - A sequence pattern with a fixed length
expr match {
  case List(0, _, _) => println("found it")
  case _ =>
}

// 15.8.1 - Match a bunch of different things look at that nifty thing
expr match {
  case List(0, 1, a, _*) => println("we did it reddit " + a)
  case _ =>
}

// 15.9 - A sequence pattern with an arbitrary length
expr match {
  case List(0, _*) => println("found it")
  case _ =>
}

// 15.10 - A pattern match with a tuple pattern
def tupleDemo(expr: Any) =
  expr match {
    case (a, b, c) => println("matched " + a + b + c)
    case _ =>
  }

// 15.11 - A pattern match with typed patterns
def generalSize(x: Any) = x match {
  case s: String => s.length
  case m: Map[_, _] => m.size
  case _ => -1
}

// 15.11.1
expr.isInstanceOf[String]
expr.asInstanceOf[String]

// 15.12 - Using type testing and casing instead of pattern matching
if (x.isInstanceOf[String]) {
  val s = x.asInstanceOf[String]
  s.length
} else ...

// 15.12.1 - This does not work as expected, will return true for all maps
def isIntIntMap(x: Any) = x match {
  case m: Map[Int, Int] => true
  case _ => false
}

// 15.13 - A pattern with a variable binding
expr match {
  case UnOp("abs", e @ UnOp("abs", _)) => e
  case _ =>
}
