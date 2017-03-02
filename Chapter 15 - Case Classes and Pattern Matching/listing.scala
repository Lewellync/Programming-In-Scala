
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

// 15.14.1 - Attempted rule to change addition to multiplication
def simplifyAdd(e: Expr) = e match {
  case BinOp("+", x, x) => BinOp("*", x, Number(2)) // Does not compile
  case _ => e
}

// 15.14 - A match expression with a pattern guard
def simplifyAdd(e: Expr) = e match {
  case BinOp("x", x, y) if x == y =>
    BinOp("*", x, Number(2))
  case _ => e
}

// 15.15 - Match expression in which case order matters
def simplifyAll(e: Expr): Expr = e match {
  case UnOp("-", UnOp("-", e)) =>
    simplifyAll(e) // Double negation
  case BinOp("+", e, Number(0)) =>
    simplifyAll(e) // Adding 0
  case BinOp("*", e, Number(1)) =>
    simplifyAll(e) // Multiply by 1
  case UnOp(op, e) =>
    UnOp(op, simplifyAll(e))
  case BinOp(op, l, r) =>
    BinOp(op, simplifyAll(l), simplifyAll(r))
  case _ => expr
}

// 15.15.1 - This is bad, because the catch-all case is first and will always catch
def simplifyBad(expr: Expr): Expr = expr match {
  case UnOp(op, e) => UnOp(op, simplifyBad(e))
  case UnOp("-", UnOp("-", e)) => e
}

// 15.16 - A sealed hierarchy of case classes
sealed abstract class Expr
case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class UnOp(operator: String, arg: Expr) extends Expr
case class BinOp(operator: String,
                 left: Expr, right: Expr) extends Expr

// 15.16.1 - Compiler warning because there are missing cases
def describe(e: Expr): String = e match {
  case Number(_) => "a number"
  case Var(_) => "a variable"
}

// 15.16.2 - Compiler is cool with this
def describe(e: Expr): String = e match {
  case Number(_) => "a number"
  case Var(_) => "a variable"
  case _ => throw new RuntimeException // Should never happen, but stops compiler
}

// 15.16.3 - This is prettier
def describe(e: Expr): String = (e: @unchecked) match {
  case Number(_) => "a number"
  case Var(_) => "a variable"
}

// 15.17.1 - Collection operations producion Optional values
val capitals = Map("France" -> "Paris", "Japan" -> "Tokyo")
capitals get "France" // returns Some(Paris)
capitals get "North Pole" // returns None

// 15.17.2 - Pattern match taking optional values apart
def show(x: Option[String]) = x match {
  case Some(s) => s
  case None => "?"
}

// 15.17 - Defining multiple variables with one assignment
val myTuple = (123, "abc")
val (number, string) = myTuple // results in number: Int = 123 and string: String = "abc"

// 15.17.3 - Deconstructing case classes
val exp = new BinOp("*", Number(5), Number(1))
val BinOp(op, left, right) = exp // results in three variables

// 15.17.4 - Sequence of case classes are general function literals!
val withDefault: Option[Int] => Int = {
  case Some(x) => x
  case None => 0
}

// 15.17.5 - A partial function that should return the second element of a list
val second: List[Int] => Int = {
  case x :: y :: _ => y // But it gives warnings, because it isn't exhaustive
}

// 15.17.6 -  We need to specify that this is a partial function, and not the entire
//            scope of functions from List[Int] => Int
val second: PartialFunction[List[Int], Int] = {
  case x :: y :: _ => y
}

// 15.18 - A for expression with a tuple pattern
for ((country, city) <- capitals)
  println("The capital of " + country + " is " + city)

// 15.19 - Picking elements of a list that match a pattern
val results = List(Some("apple"), None, Some("orange"))
for (Some(fruit) <- results) println
