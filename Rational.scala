
/*
  - Curly braces aren't necessary for a class definition
  - 'n' and 'd' are class parameters
  - The compiler will gather the class parameters and create a primary
    constructor. In Java, there would be a primary constructor explicitly stated
    but in Scala, the compiler does this for you. Magic.
*/
class Rational(n: Int, d: Int) {

  //println("Created " + n + "/" + d)

  /*
    How about we override the toString method, instead of printing it out?
    This way, when we create a new Rational in the console, Scala prints out
    something a little more readable.
  */
  override def toString = n + "/" + d

  /*
    Next step is checking for preconditions. A valid rational with our limited
    constructor could end up as 5/0, which is impossible. We use a require to
    put a prcondition on the object. A require method takes one boolean param.
  */
  require (d != 0)
}
