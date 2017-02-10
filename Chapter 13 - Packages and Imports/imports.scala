/*
  The import clause makes members of a package or object available by their names
  alone, without needing to prefix them with the package or object name.
  (Scala uses '_' instead of '*' for wildcard)
*/

// Easy access to Fruit (Single type import)
import bobsdelights.Fruit

// Easy access to all members of bobsdelights (On demnad import)
import bobsdelights._

// Easy access to all members of Fruits (Static class fields import)
import bobsdelights.Fruits._

// 13.7
package bobsdelights

abstract class Fruit(
  val name: String,
  val color: String
)

object Fruits {
  object Apple extends Fruit("apple", "red")
  object Orange extends Fruit("orange", "orange")
  object Pear extends Fruit("pear", "yellowish")
  val menu = List(Apple, Orange, Pear)
}

/*
  Scala imports are much more general than Java. For one, imports in Scala can appear
  anywhere, not just at the beginning of a compilation unit. Method showFruit imports
  all members of its parameter fruit, which is type Fruit. The subsequent println
  statement can refer to name and color directly. These two are equivalent to fruit.name
  and fruit.color.
*/

// 13.8
def showFruit(fruit: Fruit) = {
  import fruit._ // ANYWHERE
  println(name + "s are " + color)
}

/*
  Another way Scala's imports are more flexible is that they can import packages themselves,
  not just their non-package members. Think of nested packages as being contained in
  their surrounding package. In 13.9, the package java.util.regex is imported. This
  makes regex usable as a simple name. To access the Pattern singleton object from
  the package, you can just say regex.Pattern.
*/

// 13.9 Importing a package name
import java.util.regex

class AStarB {
  val pat = regex.Pattern.compile("a*b")
}

/*
  Imports in scala can also rename or hide members. This is done with an import
  selector clause enclosed in braces, which follows the object from which members
  are imported.
*/

// This imports just members Aplle and Orange from object Fruits
import Fruits.{Apple, Orange}

// This imports the two members Apple and Orange from object Fruits. However,
// the Apple object is renamed to McIntosh so this object can be accessed either with
// Fruits.Apple or McIntosh. Form is "<original-name => <new-name>"
import Fruits.{Apple => McIntosh, Organge}

// This imports the SQL date class as SDate, so you can simultaneously import the
// normal Java date class as simply Date.
import java.sql.{Date => SDate}

// This imports the java.sql package as S, allowin you to write things like S.Date
import java.{sql => S}

// Imports all members from object Fruits
import Fruits.{_}
// Same as
import Fruits._

// Imports allm members from object Fruits, but renames Apple to McIntosh
import Fruits.{Apple => McIntosh, _}

// Imports all members of Fruits except Pear. A clause of the form <original-name> => _
// excludes <original-name> from the names that are imported. In a sense, renaming
// something to _ means hiding it altogether.
import Fruits.{Pear => _, _}

// The above is useful if two packages define the same class and you want all members
// of both but want to choose which overlapping class to import.
import Notebooks._ // contains Apple, included
import Fruits.{Apple => _, _} // also contains Apple, excluded
