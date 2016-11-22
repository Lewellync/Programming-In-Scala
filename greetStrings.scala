
val greetStrings = new Array[String](3)

//Scala arrays are accessed by placing the index inside parens, not brackets
greetStrings(0) = "Hello"
greetStrings(1) = ", "
greetStrings(2) = "world!\n"

/*
  Note, 'to' in the for loop is a method that takes one int argument.
*/
for (i <- 0 to 2)
  print(greetStrings(i))

//More explicit version

val explicitGreetStrings: array[String] = new Array[String](3)

explicitGreetStrings.update(0, "Hello")
explicitGreetStrings.update(1, ", ")
explicitGreetStrings.update(2, "world!\n")

for (i <- 0.to(2))
  print(explicitGreetStrings.apply(i))
