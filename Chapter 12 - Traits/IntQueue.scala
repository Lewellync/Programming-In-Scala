
/*
  Alright, so we have a standard abstract class definition like we've seen before.
  Nothing out of the ordinary here.
*/
abstract class IntQueue {
  def get(): Int
  def put(x: Int)
}

/*
  IntQueue is a basic implementation of our queue, using an ArrayBuffer to get
  elements from the beginning and add to the end.
*/
import scala.collection.mutable.ArrayBuffer

class BasicIntQueue extends IntQueue {
  private val buf = new ArrayBuffer[Int]
  def get() = buf.remove(0)
  def put(x: Int) = { buf += x }
}

// Everything looks normal, as expected
val queue = new BasicIntQueue
queue.put(10)
queue.put(20)
queue.get()
queue.get()

/*
  Now let's modify the behavior, not yet stacking traits, but applying traits
  that modify the default behavior of our queue. Note the super call in the trait
  Doubling that extends an abstract class. This is illegal for normal classes to
  do because they will fail at runtime, but because super calls in traits are
  dynamically bound, the call will work as long as Doubling is mixed in AFTER
  another trait or class that gives a concrete definition to the method.
*/
trait Doubling extends IntQueue {
  // Abstract override is necessary when a trait defines a member that only works
  // when mixed into some class that has a concrete definition of the method in
  // question.
  abstract override def put(x: Int) = { super.put(2 * x) }
}

class MyQueue extends BasicIntQueue with Doubling
val queue = new MyQueue
queue.put(10)
queue.get()

// MyQueue defines no new code, just identifies a class and mixes in a trait.
// The same could be done by suppling them to new instead like below:
val queue = new BasicIntQueue with Doubling

/*
  So we've seen traits that modify default behavior. Now let's stack them. The
  below traits have behavior that could change.
*/
trait Incrementing extends IntQueue {
  abstract override def put(x: Int) = { super.put(x + 1) }
}
trait Filtering extends IntQueue {
  abstract override def put(x: Int) = { if (x >= 0) super.put(x) }
}
