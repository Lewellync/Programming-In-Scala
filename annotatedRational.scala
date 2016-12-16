
/*
  - Curly braces aren't necessary for a class definition
  - 'n' and 'd' are class parameters
  - The compiler will gather the class parameters and create a primary
    constructor. In Java, there would be a primary constructor explicitly stated
    but in Scala, the compiler does this for you. Magic.
*/
class annotatedRational(n: Int, d: Int) {

  /*
    Adding fields to the object so that we can access another objects fields!
    Just like in Java, except slightly less clunky
  */
  val numer: Int = n
  val denom: Int = d

  /*
    We should probably make sure the rational is reduced upon creation (and as
    a result after every operation). To do this, we'll use a private field.
    In Scal
  */
  private val g = gcd(n.abs, d.abs)

  private def gcd(a: Int, b: Int): Int =
    if (b==0) a else gcd(b, a % b)
  /*
    Next step is checking for preconditions. A valid rational with our limited
    constructor could end up as 5/0, which is impossible. We use a require to
    put a prcondition on the object. A require method takes one boolean param.
  */
  require (d != 0)

  /*
    How about some new constructors! Besides the default one that runs all code
    not in methods (so numer and denom are set in the default constructor),
    auxillary constructors are defined using 'this'. This constructor allows
    users to create a Ratiional from one number, like 5 and have it represented
    as 5/1

    Note: Every aux constructor must invoke another constructor of the same
    class as its first action (they will have some form of this(...)). Whether
    they are calling the primary constructor, or another auxillary constructor
    that comes textually before it, all aux constructors eventually call the
    primary constructor.
  */
  def this(n: Int) = this(n,1)

  /*
    How about we override the toString method, instead of printing it out?
    This way, when we create a new Rational in the console, Scala prints out
    something a little more readable.
  */
  override def toString = n + "/" + d

  /*
    Now, let's create some operations that we can do on a Rational, like add.
    Because we want Rational to be immutable, the add method has to return a new
    Rational object, rather than just adding the numbers together
  */
  def add(that: Rational): Rational =
    new Rational(
      numer * that.denom + denom * that.numer,
      denom * that.denom
    )

  /*
    You already know about self-referncing through 'this' in Java. It refers to
    the object instance on which the currently executing method was invoked, or
    if used in a constructor, the object being constructed. In the 'lessThan'
    method, this is unnecessary as it's referring to instance variables. In the
    'max' method, hte first this is unnecessary, but the this as a return
    statement is necessary as it represents the result of the method.
  */
  def lessThan(that: Rational) =
    this.numer * that.denom < that.numer * this.denom

  def max(that: Rational) =
    if (this.lessThan(that)) that else this


}
