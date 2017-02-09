
// 13.1 - The package clause places class Navigator into the package named bobsrockets.navigation.
package bobsrockets.navigation
class Navigator

// 13.2 - Same as above
package bobsrockets.navigation {
  class Navigator
}

/*
  When code is divided into a package hierarchy, it helps people brows the code as
  well as telling the compiler that code in the same package is related. Scala
  takes advantage of this relatedness by allowing short, unqualified names when accessing
  code that is in the same package.
*/

/*
  Reasons for using the non syntactic sugar like in 13.1 could be for including a
  class' tests in the same files as the original code, but now you can include them
  in a different package like in 13.3
*/

// 13.3 - Multiple packages in the same file
package bobsrockets {
  package navigation {
    // In package bobsrockets.navigation
    class Navigator

    // In package bobsrockets.navigation.tests
    package tests {
      class NagivatorSuite
    }
  }
}

/*
  As you can see in 13.4, you don't need to spell out the long name when accessing
  the member of a package from within that package. First, a class can be accessed
  from within its own package without needing a prefix. Second, a package itself can
  be accessed from its containing package without needing a prefix.
*/

// 13.4 - Concise access to classes and packages
package bobsrockets {
  package navigation {
    class Navigator {
      // No need to say bobsrockets.navigation.StarMap
      val map = new StarMap
    }
    class StarMap
  }

  class Ship {
    // No need to say bobsrockets.navigation.Navigator
    val nav = new navigation.Navigator
  }

  package fleets {
    class Fleet {
      // No need to say bobsrockets.Ship
      def addShip() = { new Ship }
    }
  }
}

/*
  The kind of access seen in 13.4 is only available if you explicitly nest the packaging.
  If you stick to one package per file, then - like in Java - the only names available
  will be the ones defined in the current package. In 13.5, bobsrockets.fleets is
  no longer enclosed in a packaging for bobsrockets, so Ship is no longer in scope.
*/

// 13.5 - Symbols in enclosing packages not automatically available
package bobsrockets {
  class Ship
}
package bobsrockets.fleets {
  // Does not compile! Ship is not in scope!
  class Fleet {
    def addShip() = { new Ship }
  }
}

// If nesting packages shifts your code to far to the right, you can use multiple
// package clauses without the braces
package bobsrockets
package fleets
class Fleet {
  def addShip() = { new Ship }
}

/*
  One final trick for access in packages is displayed in 13.6. Booster1 is easiest
  to access; a reference to launch by itsaelf will get you to package bobsrockets.navigation.launch
  because that is the launch package defined in the closests enclosing scope. Booster2
  is also not complicated to reach, by prepending bobsrockets.launch clears up
  any doubt as to which you are referencing. The final one is the trick; all packages
  are members of the top level package _root_, and can be reference from this.
*/

// 13.6 - Accessing hidden package names
// File - launch.scala
package launch {
  class Booster3
}

// File - bobsrockets.scala
package bobsrockets {
  package navigation {
    package launch {
      class Booster1
    }

    class MissionControl {
      val booster1 = new launch.Booster1
      val booster2 = new bobsrockets.launch.Booster2
      val booster3 = new _root_.launch.Booster3
    }
  }

  package launch {
    class Booster2
  }

}
