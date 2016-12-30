
// Scala doesn't use break or continue because they're not good with function
// literals (will talk about later). The simplest way to remove these is to
// replace every continue by an if and every break by a boolean.
var i = 0
var foundIt = false

while (i < args.length && !foundIt) {
  if (!args(i).startsWith("-")) {
    if (args(i).endsWith(".scala"))
      println(args(i).toString)
      foundIt = true
  }
}

// What if we hated vars and wanted to get rid of them? (We do). Recursion!
def searchFrom(i: Int): Int =
  if (i >= args.length) -1
  else if (args(i).startsWith("-")) searchFrom(i + 1)
  else if (args(i).endsWith(".scala")) i
  else searchFrom(i + 1)

val a = searchFrom(0)
