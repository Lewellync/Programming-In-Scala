
// Currying is a functional prorgramming technique that essentially creates
// layered functions, with multiple arg lists. It's magic.

def plainOldSum(x: Int, y: Int) = x + y
plainOldSum(1, 2)

def curriedSum(x: Int)(y: Int) = x + y
curriedSum(1)(2)

// Obviously this example is trivial, but explains how currying works. You are
// getting two traditional function invocations when you call curriedSum The
// first is :
def first(x: Int) = (y: Int) => x + y

// So what you get happening is applying the first function to 1, and applying
// the second function to 2. The below is an illustration of what happens when
// calling curriedSum
val second = first(1)
second(2)

// There is an actual way to access curriedSum's second function.
val onePlus = curriedSum(1)_  // Underscore here is a placeholder for the second
                              // param list
onePlus(2)
