
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
  located in. In 13.11, the access to f in Sub is OK, because f is delcared protected
  in Super and Sub is a subclass of Super. By contrast, the f in Other is not permitted
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
