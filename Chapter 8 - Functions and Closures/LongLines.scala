import scala.io.Source

// Methods exist as functions inside of objects, just like in Java. Functional
// programming also wants us to make many small methods to be building blocks
// for our programs. This is fine until we fill up possible namespace.

object LongLines {

  def processFile(filename: String, width: Int) {
    val source = Source.fromFile(filename)
    for (line <- source.getLines)
      processLine(filename, width, line)
  }

  private def processLine(filename: String, width: Int, line: String) {
    if (line.length > width)
      println(filename + ": " + line.trim)
  }
}

// Totally unrelated to what we're doing, but remember that when you try to run
// Scala programs, the current directory is not in the CLASSPATH so you need to
// add '-cp .' after 'scala' when running a file.

// After compiling, run with 'scala FindLongLines -cp . 45 LongLines.scala'

// Scala offers the private method for functions, same as Java, so as to not
// use too many names, but they also allow us to create local functions.

object LocalLongLines {

  def processFile(filename: String, width: Int) = {

    // Method declared within another method, only exists in this scope. Note,
    // local functions can see parameters of th eir enclosing function.
    def processLine(line: String) = {
      if (line.length > width)
      println(filename + ": " + line.trim)
    }

    val source = Source.fromFile(filename)
    for (line <- source.getLines()) {
      processLine(line)
    }
  }
}
