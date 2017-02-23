
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
