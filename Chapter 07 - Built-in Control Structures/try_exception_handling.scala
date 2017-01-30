import java.io.FileReader
import java.io.FileNotFoundException
import java.io.IOException
import java.net.URL
import java.net.MalformedURLException


case class CustomException(msg: String) extends Exception
val n = 6

// The syntax is very similar to Java for a throw.
val half =
  if (n % 2 == 0)
    n / 2
  else
    throw new CustomException("n must be even")

// Same with catches, but more different stuff with Scala syntax. Scala does not
// require you to catch checked exceptions or declare them in a throw clause.
try {
  val f = new FileReader("input.txt")
} catch {
  case ex: FileNotFoundException =>
  case ex: IOException =>
}

// Finally, the finally clause, used to execute code no matter what occurs
// during a try-catch. This can be used for things like closing IO.
val file = new FileReader("input.txt")
try {

} finally {
  file.close()
}

// As with most control structures already looked at, try-catch-finally results
// in a value. Generally, this is in the try-catch stage of the expression, and
// finally should be used for cleanup side effects only.
def urlFor(path: String) =
  try {
    new URL(path)
  } catch {
    case e: MalformedURLException =>
      new URL("http://www.scala-lang.org")
  }
