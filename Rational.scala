
/*
  - Curly braces aren't necessary for a class definition
  - 'n' and 'd' are class parameters
  - The compiler will gather the class parameters and create a primary
    constructor. In Java, there would be a primary constructor explicitly stated
    but in Scala, the compiler does this for you. Magic.
*/
class Rational(n: Int, d: Int) {
  println("Created " + n + "/" + d)
}
