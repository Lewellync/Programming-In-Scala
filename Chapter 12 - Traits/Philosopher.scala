/*
  Traits are defined by using the 'trait' keyword (duh), and has the default
  superclass of AnyRef.
*/
trait Philosophical {
  def philosophize() = {
    println("I consume memory, therefore I am!") // Concrete method
  }
}

/*
  You can use the extend keyword to mix in a trait, which causes you to implicitly
  inherit from the trait's superclass. So ThinkerFrog sublcasses AnyRef, the
  superclass of Philosophical, and mixes in Philosophical. Methods inherited from
  a trait can be used just like methods inheried from a superclass.
*/
class ThinkerFrog extends Philosophical {
  override def toString = "green"
}

val ponderousToad = new ThinkerFrog
println(ponderousToad.toString)
ponderousToad.philosophize()

/*
  A trait also defines a type, and can be used as a type. The type of philFrog is
  Philosophical. This means philFrog could have been initiated with any object
  whose class mixes in Philosophical.
*/
val philFrog: Philosophical = ponderousToad
philFrog.philosophize()

/*
  If you want to mix a trait into a class that explicitly extends a superclass,
  you use extends to indicate the superclass and with to mix in the trait. If
  you're only doing traits, the first one still gets extended and all subsequent
  ones get withed, but they all get mixed in.
*/
class Animal
trait HasLegs

class Frog extends Animal with Philosophical with HasLegs {
  override def toString = "green"
}

class P1
class P2
trait T1
trait T2

class mixMasterOne extends T1 with T2
class mixMasterTwo extends P1 with T1
// class mixMasterThree extends P2 with P1 // IS INVALID

/*
  What about overriding inherited methods from traits? Same as with inheritance
  from classes. Because the new definition of Honda still mixes in the trait
  Philosophical, you can still use it from a variable of that type, but you'll
  get new behavior because Honda overrode it.
*/
class Car

class Honda extends Car with Philosophical {
  override def toString = "vroom vroom"
  override def philosophize() = {
    println("It ain't easy being " + toString + "!")
  }
}

val boop = new Honda
boop.philosophize

val boopers: Philosophical = new Honda
boopers.philosophize

/*
  You might be thinking, traits can do anything a class can do! And you'd almost
  be right, except for two noticeable exceptions.

  Firstly, a trait cannot have any class parameters (parameters passed to the
  primary constructor of the class).

  class Point(x: Int, y: Int) is a valid class definition
  trait NoPoint(x: Int, y: Int) is an invalid trait definition

  Secondly, 'super' calls suchas super.toString are statically bound in classes.
  If you call super in a class, you know exactly which method will be invoked.
  When you write the same thing in a trait, the method implementiation to invoke
  for the super call is undefined when you define the trait. The implementiation
  to invoke will be determined anew each time the trait is mixed into a concrete
  class.
*/
