package expression
import element.Element
import element.Element._

sealed abstract class Expr
case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class UnOp(operator: String, arg: Expr) extends Expr
case class BinOp(operator: String,
    left: Expr, right: Expr) extends Expr

class ExprFormatter {

  // Contains operators in groups of increasing precedence
  private val opGroups =
    Array(
      Set("|", "||"),
      Set("&", "&&"),
      Set("^"),
      Set("==", "!="),
      Set("<", "<=", ">", ">="),
      Set("+", "-"),
      Set("*", "%")
    )

  // A mapping from operators to their precedence
  private val precedence = {
    val assocs =
      for {
        i <- 0 until opGroups.length
        op <- opGroups(i)
      } yield op -> i
    Map() ++ assocs
  }

  private val unaryPrecedence = opGroups.length
  private val fractionPrecedence = -1

  private def format(e: Expr, enclPrec: Int): Element =

    e match {

      // If the expression is a variable, the result is an element formed from the
      // variable's name.
      case Var(name) =>
        elem(name)

      // If the expression is a number, the resultant element is the number's value
      // with zeros stripped from floating points.
      case Number(num) =>
        def stripDot(s: String) =
          if (s endsWith ".0") s.substring(0, s.length - 2)
          else s
        elem(stripDot(num.toString))

      // If the expression is a unary operation, the result is formed from the operation
      // and the result of formatting the argument with the highest-possible envrionment
      // precedence.
      case UnOp(op, arg) =>
        elem(op) beside format(arg, unaryPrecedence)

      // If the expression is a fraction, an immediate result frac is formed by placing
      // the formatted operands left and right on top of each other, seperated by
      // a horizontal line element. Also checks if the fraction is inside a fraction.
      case BinOp("/", left, right) =>
        val top = format(left, fractionPrecedence)
        val bot = format(right, fractionPrecedence)
        val line = elem('-', top.width max bot.width, 1)
        val frac = top above line above bot
        if (enclPrec != fractionPrecedence) frac
        else elem(" ") beside frac beside elem(" ")

      // For all other operations, that aren't /, and formats the left and right
      // operands and adds parenthesis when necessary.
      case BinOp(op, left, right) =>
        val opPrec = precedence(op)
        val l = format(left, opPrec)
        val r = format(right, opPrec + 1)
        val oper = l beside elem(" "+ op +" ") beside r
        if (enclPrec <= opPrec) oper
        else elem("(") beside oper beside elem(")")
    }

    def format(e: Expr): Element = format(e, 0)
  }
