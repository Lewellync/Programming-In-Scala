
// We start off with a singleton object for our API, that allows users to check
// if files end with a certain String
object FileMatcher {
  private def filesHere = (new java.io.File(".")).listFiles

  // Initially, this is the only function they have access to
  def filesEnding(query: String) =
    for (file <- filesHere if file.getName.endsWith(query))
      yield file

  // What if you wanted to allow them to search for a string anywhere in the
  // file? That's not so difficult, you'd just add a new method like this:
  def filesContaining(query: String) =
    for (file <- filesHere if file.getName.contains(query))
      yield file

  // Now we have a request to add regex support, and as you can see, looks like
  // we have a lot of code reuse, but how do we fix it?
  def filesRegex(query: String) =
    for(file <- filesHere if file.getName.matches(query))
      yield file

  // So we have a lot of reused code, with only one part changing (the function
  // that is passed the query) so how can we do this in a statically typed
  // language?
}

object FileMathcerModular {
  private def filesHere = (new java.io.File(".")).listFiles

  // The answer is function values
  def filesMatching(matcher: (String, String) => Boolean) = {

    for (file <- filesHere if matcher(file.getName))
      yield file
  }

  def filesEnding(query: String) =
    filesMatching(_.endsWith(query))
  def filesContaining(query: String) =
    filesMatching(_.contains(query))
  def filesRegex(query: String)
    filesMatching(_.matches(query))
}

// The above is using the placeholder syntax in function literals being passed
// into other functions, which I definitely don't feel comfortable using yet.
// Here's how it all goes down:
//  Function Literal : _.endsWith(_)
//  Represents : (filename: String, query: String) => fileName.endsWith(query)

// Because filesMatching has the function signature of (String, String) => Boolean,
// you don't need to specify type in the placeholders when calling it.

// You can also simplify further, by passing in query with the function literal
// as a free variable that is captured by the compiler. This is because
// filesMatching does nothing with the query var except pass it back to the
// calling function, and is made possible by Scala's support of closures.
