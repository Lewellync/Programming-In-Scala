
args.foreach(arg => println(arg))

//More explicit version

args.foreach((arg: String) => println(arg))

//Using a for expression instead of a for loop

for( arg <- args )
  println(arg)

/*
  Note here, arg may seem to be a var but it is a val. arg can't be reassigned
  inside the body of the for expression. Instead, for each element of the args
  array, a new arg val will be created and initialized to the element value.
*/
