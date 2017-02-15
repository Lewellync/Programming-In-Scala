
/*
  So far, we've seen classes traits and standalone objects defined in packages.
  These are gonna be the most common, but Scala doesn't limit you to just those.
  For instance, if you want a helper method, you can put it at the top level of
  the package by using a package object. Syntactically like a package, with object
  appended to the end and requires curly braces. The contents of a package object can
  include any definitions you like. In 13.14, it includes a utility method from
  13.8.
*/

// 13.14 - A package object

// In file bobsdelights/package.scala
package object bobsdelights {
  def showFruit(fruit: Fruit) = {
    import fruit._
    println(name + "s are " + color)
  }
}

// In file PrintMenu.scala
package printmenu
import bobsdelights.Fruits
import bobsdelights.showFruit

object PrintMenu {
  def main(args: Array[String]) = {
    for (fruit <- Fruits.menu) {
      showFruit(fruit)
    }
  }
}
