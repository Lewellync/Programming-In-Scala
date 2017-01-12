
// While loop recursion! This is what recursion is. You know what recursion is.
// This example is often used for searching problems, with different
// implementations of what isGopodEnough and improve
/*
  def approximate(guess: Double): Double =
    if (isGoodEnough(guess)) guess
    else aproximate(improve(guess))
*/

// The while loop version of the above code
/*
    def approximateLoop(initialGues: Double): Double = {
      var guess = initialGuess
      while (!isGoodEnough(guess))
        guess = improve(guess)
      guess
    }
*/

// The recursive version is obviously shorter and avoids var, but what about
// speed? Turns out they have almost the same execution time because of tail
// recursion. The Scala compiler sees that the last thing approximate does is
// call itself, so it replaces that recursive call with a jump back to the
// beginning of the function with updated params.
