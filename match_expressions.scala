
// Scala's 'match' expression lets you select from a number of alternatives,
// similar to the switch statement in other languages. It also lets us do
// pattern matching!
val firstArg = if (args.length > 0) args(0) else ""

firstArg match {
  case "salt"   => println("pepper")
  case "chips"  => println("salsa")
  case "eggs"   => println("bacon")
  case _        => println("huh?") // the underscore character is Scala's
                                   // wildcard symbol, used to match unknown
                                   // values
}
