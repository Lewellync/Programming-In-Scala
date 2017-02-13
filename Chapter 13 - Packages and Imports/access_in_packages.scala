
/*
  Private members in Scala are treated simlarly to Java. A memeber labeled private
  is visible only inside the class oro jbect that contains the member definition.
  In Scala, this rule applies also for inner classes, as shown in 13.10.
  (new Inner).f() is illegal because f is declared private in Inner, but the first
  access to f in class InnerMost is OK, because that access is contained in the body
  of class Inner.
*/

// 13.10
class Outer {
  class Inner {
    private def f() = { println("f") }
    class InnerMost {
      f() // Ok
    }
  }
  (new Inner).f() // Error: f is not accessible
}

/*
  Access to protected members in Scala is also more restrictive than in Java. In Scala,
  a protected member is only accessible from sublcasses of the class the member is
  in Super and Sub is a subclass of Super. By contrast, the f in Other is not permitted
  located in. In 13.11, the access to f in Sub is OK, because f is delcared protected
  because Other does not inherit from Super.
*/

// 13.11 - Protected access differs in Scala and Java
package p {
  class Super {
    protected def f() = { println("f") }
  }
  class Sub extends Super {
    f()
  }
  class Other {
    (new Super).f() // Error: f is not accessible
  }
}

/*
  Scala has no exlicit modifier for public members; Any member not labeleled private
  or protected is public. Public members can be accessed from anywhere.

  Access modifiers in Scala can be augmented with qualifiers. A modifiers of the form
  private[X] or protected[X] means that acess is private or protected "up to" X, where
  X designates some enclosing package, object or singleton object. Qualified access
  modifers give you very fine-grained control over visibility. In particular, they
  enable you to express Java's accessibility notions which are not available in Scala's
  default.

  13.12 presents an example with many access qualifiers being used for a myriad of
  different effects!
*/

// 13.12 Flexiblw scope of protection with access qualifiers
package bobsrockets
package navigation {
  private[bobsrockets] class Navigator { // Access wihtin outer package
    protected[navigation] def useStarChar() = {} // Same as packave visibility in Java
    class LegOfJourney {
      private[Navigator] val distance = 100 // Same as private in Java
    }
    private[this] var speed = 200 // Access only from the same object
  }
}
package launch {
  import navigation._
  object Vehicle {
    private[launch] val guide = new Navigator // Same as private in Java
  }
}
private[LegOfJourney] // Same as private in Scala

// 13.13 - Accessing private members of companion classes and objects
class Rocket {
  import Rocket.fuel
  private def canGoHomeAgain = fuel > 20
}
object Rocket {
  private def fuel = 10
  def chooseStrategy(rocket: Rocket) = {
    if(rocket.canGoHomeAgain)
      goHome()
    else
      pickAStar()
  }
  def goHome() = {}
  def pickAStar() = {}
}
