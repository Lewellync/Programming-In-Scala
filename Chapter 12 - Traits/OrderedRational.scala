
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
  Now let's use the Ordered trait to clean up the above. In that trait is a
  method 'compare' which returns an interger. When implemented properly, it should
  take one object of type [T] and return:

    > 0 if the receiver is greater than the argument (this > that)
    < 0 if the receiver is smaller than the argument (this < that)
    0 if the receiver is the same as the argument (this = that)

  Of note, Ordered takes a type parameter (the type will be the elements you are
  comparing).
*/
class Rational(n: Int, d: Int) extends Ordered[Rational] {
  // ...
  def compare(that: Rational) =
    (this.numer * that.denom) - (that.numer * this.denom)
}
