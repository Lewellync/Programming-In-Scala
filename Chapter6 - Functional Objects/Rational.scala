
//This file is companion to annotatedRational.scala, and will use short comments.

class Rational(n: Int, d: Int) {

  require(d != 0)

  //Sclaa compiler will put these fields in the primary constructor in the order
  //they appear.
  private val g = gcd(n.abs, d.abs)
  val numer:Int = n / g
  val denom:Int = d / g

  def this(n: Int) = this(n, 1)

  def + (that: Rational): Rational =
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom
    )

  def + (that: Integer): Rational =
    new Rational(
      numer + that * denom,
      denom
    )

  def - (that: Rational): Rational =
    new Rational(
      numer * that.denom - that.numer * denom,
      denom * that.denom
    )

  def - (that: Integer): Rational =
    new Rational(
      numer - that * denom,
      denom
    )

  def * (that: Rational): Rational =
    new Rational(
      numer * that.numer,
      denom * that.denom
    )

  def * (that: Integer): Rational =
    new Rational(
      numer * that,
      denom
    )

  def / (that: Rational): Rational =
    new Rational(
      numer * that.denom,
      denom * that.numer
    )

  def / (that: Integer): Rational =
    new Rational(
      numer / that,
      denom
    )

  override def toString = numer + "/" + denom

  private def gcd(a: Int, b: Int):Int =
    if(b == 0) a else gcd(b, a % b)
}
