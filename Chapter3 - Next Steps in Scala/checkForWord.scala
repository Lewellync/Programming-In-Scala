import scala.io.Source

val lines = Source.fromFile(args(0)).getLines().toList

//This is some advanced bullshit and I could definitely do some map function,
//but it works as is so I'm going to leave it
for((line, count) <- lines.zipWithIndex) {
  if (line.contains(args(1))) {
    println(s"Line " + count)
  }
}
