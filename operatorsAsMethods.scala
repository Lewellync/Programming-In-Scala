
// This is really a method call and is written in operator notation
val sum = 1 + 2
println(sum)

// Same as above, but written explicitly
val sumMore = 1.+(2)
println(sumMore)

// There exist a bunch of overloaded methods for '+' too, like creating a long
val longSum = 1 + 2L
println(longSum)

// Let's look at operator notation!
val s = "Hello, world!"
println(s indexOf 'o')

// If one side of the method takes multiple parems, they must be in parens
println(s indexOf ('o', 5))
