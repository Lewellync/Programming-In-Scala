val listNumbers = List(12, 41, 23, 13, 15, 2, 4 - 2)
listNumbers.foreach(x => println(x / 2))
println(listNumbers.filter(_ > 2))
println(listNumbers.filter(_ == 2))

val increase = (x: Int) => x + 1
