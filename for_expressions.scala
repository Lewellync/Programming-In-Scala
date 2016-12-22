
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
