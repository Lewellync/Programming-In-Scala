
// 's' string interpolator
//
val name = "reader"
println(s"Hello, $name!")

println(s"The answer is ${6 * 7}.")

// 'raw' string interpolator
// allows you to print anything, no special characters
println(raw"No\\\escape!")

// 'f' string interpolator
// f allows you to use printf style formatting instructions, like %f or %s
println(f"${math.Pi}%.5f")

val pi = "Pi"
println(f"$pi is approximately ${math.Pi}%.8f")
