
// This isn't functional at all, check out them printing side effects and while
// loops with vars. Gross.
def badPrintMultiTable() = {
  var i = 1

  while (i <= 10) {

    var j = i

    while (j <= 10) {

      val prod = (i * j).toString
      var k = prod.length

      while (k < 4) {
        print(" ")
        k += 1
      }

      print(prod)
      j += 1
    }

    println()
    i += 1
  }
}

badPrintMultiTable

// Let's make it functional! We see that we're gonna need to return a string
// instead of printing it out. We also see the idea of building a sequence
// as necessary in the functional version of the code.

def makeRowSeq(row: Int) =
  for (col <- 1 to 10)
  yield {
    val prod = (row * col).toString
    val padding = " " * (4 - prod.length) // This is some dope string magic
    padding + prod
  }

def makeRow(row: Int) = makeRowSeq(row).mkString

def multiTable() = {

  val tableSeq =
    for (row <- 1 to 10)
    yield makeRow(row)

  tableSeq.mkString("\n")
}

println(multiTable)
