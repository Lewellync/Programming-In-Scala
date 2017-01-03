import scala.io.Source

// Methods exist as functions inside of objects, just like in Java. Functional
// programming also wants us to make many small methods to be building blocks
// for our programs. This is fine until we fill up possible namespace. To combat
// this, Scala offers us local methods (methods declared within methods).

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
