import scala.collection.mutable

val treasureMap = mutable.Map[Int, String]()
//As a reminder, this is binary operation notation and can be rewritten as
//  (1).->("Go to island.")
treasureMap += (1 -> "Go to island.")
treasureMap += (2 -> "Find big X on ground.")
treasureMap += (3 -> "Dig.")
println(treasureMap)
println(treasureMap(2))

//Like with sets, immutable is the default implementation for maps
val romanNumeral = Map(
  1 -> "I", 2 -> "II", 3 -> "III", 4 -> "IV", 5 -> "V"
)
println(romanNumeral(4))
