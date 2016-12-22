
//This is the bad way to do things, initialize a value and maybe change it
var filename1 = "default.txt"
if (!args.isEmpty)
  filename = args(0)

//What if we used the if statement to return a value?
//This lets us use a val instead of a var #functional
val filename2 =
  if (!args.isEmpty) args(0)
  else "default.txt"

//Second advantage of using a val is that it supports equational reasoning. The
//introduced variable is equal to the expression that computes it. This, in
//practice, means that you could replace the variable with the expression.
