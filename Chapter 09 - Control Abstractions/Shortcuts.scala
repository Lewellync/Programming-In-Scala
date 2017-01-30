
// So in FileMatcher we saw a theoretical improvement in an API by reducing
// code use by building function literal magic. But what about client code
// optimzation?

// This is a really inefficient way of finding out if a list has a negative
// number. What about builtin higher-order functions?!
def containsNeg(nums: List[Int]): Boolean = {
  var exists = false
  for (num <- nums)
    if (num < 0)
      exists = true
  exists
}

// Like 'exists' which takes a function value. This higher-order function
// represents a control abstraction that is public, and useable in client code.
def containsNegBetter(nums: List[Int]) = nums.exists(_ < 0)

// If you wanted to check for odd, without exists, you'd have to do something
// similarly sloppy like this
def containsOdd(nums: List[Int]): Boolean = {
  var exists = false
  for (num <- nums)
    if (num % 2 == 1)
}

// Or we could just use exists again
def containsOddBetter(nums: List[Int]) = num.exists(_ % 2 == 1)
