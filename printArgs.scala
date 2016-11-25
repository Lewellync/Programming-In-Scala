
//Imperative Style
def imperativePrintArgs(args: Array[String]): Unit = {
  var i = 0
  while(i < args.length) {
    println(args(i))
    i += 1
  }
}

//Functional Style
def functionalPrintArgs(args: Array[String]): Unit = {
  for (arg <- args)
    println(arg)
}

//Even more Functional Style
def superFunctionalPrintArgs(args: Array[String]) {
  args.foreach(println)
}

//None of these are purely functional, because they all have side effects!
//A more functional approach is defing a method that formats and returns the args
def formatArgs(args: Array[String]) = args.mkString("\n")

println(formatArgs(args))

//When code lacks side effects, it makes it easier to test.
val res = formatArgs(Array("zero", "one", "two"))
assert(res == "zero\none\ntwo")
