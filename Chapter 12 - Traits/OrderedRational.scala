
// Without the 'Ordered' trait

/*
  As we see below, fleshing out the entire list of comparisons operators is
  tediuos and features a lot of repeition. Three of the definitions fall back
  on the definition of '<', and this is a lot to expect an implementer to do.
*/
class Rational(n: Int, d: Int) {
  // ...
  def < (that: Rational) = this.numer * that.denom < that.numer * this.denom
  def > (that: Rational) = that < this

  // This is what a rich interface without fancy magic would require you to do.
  def <= (that: Rational) = (this < that) || (this == that)
  def >= (that: Rational) = (this > that) || (this == that)
}

/*

*/
