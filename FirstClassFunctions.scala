
// First class functions (function literals) are anonymous functions, functions
// without method signatures, I dunno man whatever you want them to be.

// The '=>' designates that converts the thing on the left into the thing
// on the right.
val increase = (x: Int) => x + 1

increase(2)

// If you'd like the function literal to have more than one line, surround it
// with curly braces.
val increaseLong = (x: Int) => {
  println("We")
  println("Are")
  println("Number")
  println("One")
  x + 1
}

increaseLong(2)

// You can pass function literals into other functions, like a foreach on a
// collection. The foreach is definied in List, Set, Array, Map, etc.
val listNumbers = List(-11, 3, 4, 5, -12, 21)
listNumbers.foreach((x: Int) => println(x))

// Another method they have is filter, which selects elements in a collection
// that pass a test the user supplies. The test type is a function, so function
// literals are perfect for this.
listNumbers.filter((x: Int) => x > 0)

// You can also leave the type and parenthesis off of the function literal.
// Previous examples can be written like this sometimes. but sometimes the
// compiler needs the type. You'll get a better feel for this as time goes on.
listNumbers.foreach(x => println(x))
listNumbers.filter(x => x > 0)

// An even more concise way of doing function literals is the placeholder symbol
// _. Think of _ as a blank in the expression, filled in by the compiler. The
// below is equivalent to the filter on line 37.
listNumbers.filter(_ > 0)

// In case you need to specify a  type for the placeholder if it is ambigious,
// like in 'val f = _ + _'
val f = (_: Int) + (_: Int)
println(f(5, 10))
// Note: '_ + _' expands into a literal for a function that takes two parems.
// Note: Placeholder can be used only if each parem is used once and only once.

// For some things you can remove the param list instead of a single parem
// 'x => println(x)' can be rewrittten to be:
listNumbers.foreach(println _)

// Using the underscore as the placeholder like this you create partially
// applied functions. In Scala, when you invoke a function, passing in any
// needed arugments, you apply that function to the args. For example:
def sum(a: Int, b: Int, c: Int) = a + b + c

// This first one creates a function with an apply method that takes 3 params,
// and fills all 3 arguments into the sum signature
val sumVague = sum _ // Don't have to specify parem type with all placeholder
                     // parems
println(sumVague(1,2,3))

// This second one creates a class with an apply method that takes 1 param, and
// fills in the one unknown param in the sum signature
val sumLessVague = sum (1, (_: Int), 3) // Must specify type with non
                                        // placeholder parems
println(sumLessVague(2))
