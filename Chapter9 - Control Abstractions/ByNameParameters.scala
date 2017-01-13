
// What if instead of using the curly braces, we want our custom built control
// abstraction to look more like an if or while loop? By-name params.

// Here we have an assertion construct. If assertionsEnabled is true, then it
// will take as passed function, invoke it, and check for trueness.
var assertionsEnabled = true
def myAssert(predicate: () => Boolean) =
  if (assertionsEnabled && !predicate())
    throw new AssertionError

myAssert(() => 5 > 3) // This looks pretty awkward, you'd rather have it look
                      // like : myAssert(5 > 3), that's what by-name params are.

def byNameAssert(predicate: => Boolean) = // Notice the lack of ()
  if (assertionsEnabled && !predicate)    // Here as well
    throw new AssertionError

byNameAssert(5 > 3)

// By-name params exist to create more aesethically pleasing code. Instead of
// giving the param a type, like of type (), you start it with =>.


def testByName(x: => Integer) =
  if (x > 3)
    true
  else
    false

println(testByName(5 % 2))
