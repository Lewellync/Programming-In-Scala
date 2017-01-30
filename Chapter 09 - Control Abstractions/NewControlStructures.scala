
// How can we use this to create new control structures? By creating methods
// that take functions as arguments like we did with FileMatcherModular
def twice(op: Double => Double, x: Double) = op(op(x))
println(twice(_ + 1, 5))

// Anytime a control pattern is repeated in multiple parts of your code, you
// should look think about implementing a new control structure. Consider a more
// widely used pattern, like opening -> operating -> closing a resource.

/*
  def withPrintWriter(file: File, op: PrintWriter => Unit) = {
    val writer = new PrintWriter(file)
    try {
      op(writer)
    } finally {
      writer.close()
    }
  }

  withPrintWriter(
    new File("date.txt"),
    writer => writer.println(new java.util.Date)
  )
*/

// The advantage of using this method is that the code assures that the file is
// closed, and not the user using the method. It's baked in functionality. When
// a control-abstraction function has a resource that another function uses,
// it's called a loan pattern. The calling function borrows the resource under
// the terms of the control-abstraction function.

// We can combine this idea of loan pattern and currying together to rewrite:
/*
  def withPrintWriter(file: File)(op: PrintWriter => Unit) = {
    val writer = new PrintWriter(file)
    try {
      op(writer)
    } finally {
        writer.close()
    }
  }

  val file = new File("date.txt")
  withPrintWriter(file) { writer => writer.println(new java.util.date) }
*/

// Another advantage of currying is in Scala, if a function only takes on arg,
// you can surround it by curly brackets. With currying, you can surround the
// last arg with curly brackets, making it feel more like Scala supported code
