
// Scala allows you to have functions with uknown params (within reason)
def echo(args: String*) = // Asterik means repeated param
  for (arg <- args) println(arg)

echo("this","works")
echo("so","does","this")

// The function is actually accepting an array of Strings, but you can't just
// pass an array of strings (the funciton is looking for strings)

val arr = Array("but","does","this","work?")
echo(arr: _*) // This tells the compiler to send each item of arr as its own
              // argument, rather than as one array.

val arr2 = List("what","about","lists?")
echo(arr2: _*) // Works for lists too

// Scala also allows args to be passed in by name, rather than relying on the
// order of the arguments being passed in to determine what value goes to name.
def speed(distance: Float, time: Float): Float =
  distance / time

println(speed(100, 10))
println(speed(distance = 100, time = 10))
println(speed(time = 10, distance = 100))

// Default Parameter Values
// Defaults for params are specified by an '=' after the param type, and the
// value that will be the default
def printTime(out: java.io.PrintStream = Console.out) =
  out.println("time = " + System.currentTimeMillis())
printTime()

def printTime2( out: java.io.PrintStream = Console.out, divisor: Int = 1) =
  out.println("time = " + System.currentTimeMillis()/divisor)
printTime2(divisor = 1000)
