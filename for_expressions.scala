
/*
  For expressions in Scala are these Swiss army knives of iterative dopeness.
  Simple uses can enable iterating over a sequence of integers, and more
  advanced expressions can iterator over mutliple collections of different kinds
  filter out elements on conditions, and produce new collections.
*/

// filesHere is an Array[java.io.File]
val filesHere = (new java.io.File(".")).listFiles

// the file <- filesHere syntax creates a 'generator'. With each iteration, a
// new file val is created. This for expression syntax works with any kind of
// collection, not just arrays.
for (file <- filesHere)
  println(file)

// For example, ranges!
for (i <- 1 to 4)
  println("Iteration " + i)

// You can use x to y, or x until y
for (i <- 1 until 4)
  println("Iteration " + i)

// Iterating through integers like this is common in Scala, versus other
// languages which might use something like this:
for (i <- 0 to filesHere.length - 1)
  println(filesHere(i))
// Rather than this, why not just iterate over the collection directly? The only
// thing that you might require from this method is the current index, but Scala
// has the ability to record that (will show later on)

/*
  For loops can also filter collections to a subset, cause they're magic.
*/

for (file <- filesHere if file.getName.endsWith(".scala"))
  println(file)

// We need more filters!
for {
  file <- filesHere
  if file.isFile
  if file.getName.endsWith(".scala")
} println(file)

// Nested Iteration - if you add multiple <- claues, you will get loops within
// the for expression.
def fileLines(file: java.io.File) =
  scala.io.Source.fromFile(file).getLines().toList

def grep(pattern: String) = {
  for {
    file <- filesHere
    if file.getName.endsWith(".scala")
    line <- fileLines(file)
    if line.trim.matches(pattern)
  } println(file + ": " + line.trim)
}

grep(".*gcd.*")
