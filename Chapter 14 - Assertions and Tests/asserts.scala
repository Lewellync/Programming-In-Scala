/*
  You may want to place an assert in the class after the calls to widen from our Element
  class in Chapter 10.
*/

// 14.1 - Using an assertion
def above(that: Element): Element = {
  val this1 = this widen that.width
  val this2 = that widen this.width
  assert(this1.width = that1.width)
  elem(this1.contents ++ that1.contents)
}

/*
  Another way is to check the widths at the end of the widen method, before you return
  the value. You can accomplish  this by storing the result in a val, performing
  an assertion on the result, then mentioning the val last so the result is returned
  if the assertion succeeds. This can be done very concicsely as in 14.2

  The ensuring method can be used with any result type, and takes one argument; a
  predicate function that takes a result type and returns Boolean, and passes the
  result to the predicate. If the predicate returns true, ensruing will return an error.
*/

// 14.2 - Using 'ensuring' to assert a function's result
private def widen(w: Int): Element =
  if (w <= width)
    this
  else {
    val left = elem(' ', (w - width) / 2, height)
    val right = elem(' ', w - width - left.width, height)
    left beside this beside right
  } ensuring (w <= _.width)
