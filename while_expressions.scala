
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
//Scala, the type of result is 'Unit'
