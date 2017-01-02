
//While loop in Scala is similar to Java, a condition and a body
def gcdLoop(x: Long, y: Long): Long = {
  var a = x
  var b = y
  while (a != 0) {
    val temp = a
    a = b % a
    b = temp
  }
  b
}

//Do-While loop is also similar, it checks the condition after the body
var line = ""
do {
  line = readLine()
  println("Read: " + line)
} while (line != "")

//These are loops and not expressions, because they do not result in values. In
//Scala, the type of result is 'Unit' for these loops. This is like 'void' from
//Java, but differs in some key cases. For instance, reassignment of vars
//results in Unit. This is important becaause of this while loop idiom:

var line = ""
while ((line = readLine()) != "")
  println("Read: " + line)

//The above will compile with warning, since 'line = readLine()' is var
//reassignment which yields Unit. Loops that do not yield a value are often left
//out of functional language,s but Scala keeps it in cause it's cool with your
//lamer imperative friends and wants them to understand so he can use them.

def gcd(x: Long, y: Long): Long =
  if (y == 0) x else gcd(y, x % y)

//This is an alternate way to calculate GCD without using loops. Generally,
//loops can be replaced with a more functional style of code using recursion.
