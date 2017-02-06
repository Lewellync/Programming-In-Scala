/*
  The precise order of linearization is described in a complicated language
  specification, but for the most part you don't need to worry about the complicated
  parts. The general rule is, in any linearization, a class is always linearized in
  front of all its superclasses and mixed in traits. Thus, when you write a method
  that calls super, that method is definitely modifying the behavior of the superclasses
  and mixed in traits, not the other way around.
*/

/*
  In the below example, the lineraizattion of cat is computed as follows.

    Animal doesn't explicity extend a superclass or mixin any supertraits, so it
    uses the defaults.
      Animal -> AnyRef -> Any

    The first mixin trait, Furry, extends Animal which is already in the linearization
    stack. Because it is next in line, the stack will look like this after.
      Furry -> Animal -> AnyRef -> Any

    The second mixin, FourLegged, extends HasLegs, which has not yet been mixed in
    so the stack now looks like.
      FourLegged -> HasLegs -> Furry -> Animal -> AnyRef -> AnyRef

    Finally, the first class in the linearization is Cat itself.
      Cat -> FourLegged -> HasLegs -> Furry -> Animal -> AnyRef -> AnyRef

  When any of these methods invoke a method via super, the implementation invoked
  will be the first implementation to its right in the linearization.
*/

class Animal
trait Furry extends Animal
trait HasLegs extends Animal
trait FourLegged extends HasLegs
class Cat extends Animal with Furry with FourLegged
