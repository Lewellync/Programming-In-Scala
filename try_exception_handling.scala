
// The syntax is very similar to Java for a throw.

val half =
  if (n % 2) == 0
    n / 2
  else
    throw new RunetimeException("n must be even")
