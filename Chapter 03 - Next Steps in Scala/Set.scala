import scala.collection.mutable
import scala.collection.immutable.HashSet

//Scala assumes that unless you specify, Set is immutable
var jetSet = Set("Boeing", "Airbus")
/*
  Because it is imutable, this isn't actually +=, it's creating a new Set and
  adding "Lear" to it. You can't change a var, but you can reassign it!
*/
jetSet += "Lear"
println(jetSet.contains("Cessna"))
println(jetSet)


val movieSet = mutable.Set("Hitch", "Poltergeist")
/*
  '+=' is an actual method for mutable sets, so this call could be rewritten as
  movieSet.+=("Shrek").
*/
movieSet += "Shrek"
println(movieSet)

//Scala has specific implementations of each a variety of Sets if you need them
val hashSet = HashSet("Tomatoes", "Chilies")
println(hashSet + "Coriander")
