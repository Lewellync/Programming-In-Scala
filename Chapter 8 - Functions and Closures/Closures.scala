
// So far, all function literals have referred only to passed parems (bound
// variables) that have meaning assgined function
// Variables outside of that scope are known as free variables, because the
// function assigns them no meaning

var more = 1
val addMore = (x: Int) => x + more  // More is a free var, x is a bound
                                    // This is also an open term
println(addMore(3))
// The function value that's created at runtime from this function literal is
// called a closure, because it closes the function literal by capturing values
// for free variables.

// A function literal with no free variables is a closed term
val closedTerm = (x: Int) => x + 1

// What happens if the value of a free variable changes after the closure is
// created?
more = 2
println(addMore(3))

// Scala closures capture the variables themselves, not the value, so it can see
// changes made to it outside the closure. Changes made by a closure to a
// captured variable are also visible outside of the closure
val someNumbers = List(-11, -10, -5, 0, 5, 10)
var sum = 0

someNumbers.foreach(sum += _)
println(sum)

// What if a closure accesses some variable that has several different copies as
// the program runs? What if a closure uses a local variable of some function,
// and the function is invoked many times? Which instance of that variable gets
// used at each access? The instance used is the one that was active at the time
// the closure was created.
def makeIncrease(more: Int) = (x: Int) => x + more
val inc10 = makeIncrease(10)
val inc99 = makeIncrease(99)
println(inc10(10) + " " + inc99(10))
// When you call makeIncrease, a closure is created that captures 10 or 99 as
// the binding for more and returns a function with that binding. Neat!
