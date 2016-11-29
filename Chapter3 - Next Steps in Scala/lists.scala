/*
  Because lists are immutable, they behave like Java strings. When you call a
  method on a list that seems to mutate said list, it instead creates and
  returns a new list with the new value.
*/

val oneTwo = List(1, 2)
val threeFour = List(3, 4)

// ':::' is the concatenation operator for lists
val oneTwoThreeFour = oneTwo ::: threeFour

println(oneTwo + " and " + threeFour + " were not mutated.")
println("Thus, " + oneTwoThreeFour + " is a new list.")

val twoThree = List(2, 3)

// '::' pronounced cons is the prepend operator on lists
val oneTwoThree = 1 :: twoThree
println(oneTwoThree)

/*
  Shorthand way to specify an empty list is 'Nil', one way to initialize a new
  list is to string together elements with cons with Nil as the last element
*/

val twoThreeFour = 2 :: 3 :: 4 :: Nil
println(twoThreeFour)
