import scala.io.Source

//Because an iterator can only be used once (no reset), and we want to use
//the list of lines multiple times, we assign it to a variable
val lines = Source.fromFile(args(0)).getLines().toList

//Calculate the length of the length of the string, for padding
def widthOfLength(s: String) = s.length.toString.length

//reduceLeft applies the passed function to the first two elements (the left) of
//the list. It then takes that result, and applies it to the third element until
//the end of the list is reached.
val longestLine = lines.reduceLeft(
  (a, b) => if (a.length > b.length) a else b
)

//Determine the longest length for padding
val maxWidth = widthOfLength(longestLine)

//Print results with formatting
for(line <- lines) {
  val numSpaces = maxWidth - widthOfLength(line)
  val padding = " " * numSpaces
  println(padding + line.length + " | " + line)
}
