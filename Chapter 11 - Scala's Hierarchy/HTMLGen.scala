

def title(text: String, anchor: String, style: String): String =
  s"<a id='$anchor'><h1 class='$style'>$text</h1></a>"

/*
  By defining it as it is above, the compiler will in no way be able to help you
  if say, the order of the arguments is incorrect. It will work totally fine,
  but the end result will be a botched HTML statement.
*/
title("chap:vcls", "bold", "Value Classes")

/*
  You can, however, define very simple classes that hold and check these values
  so that you can be sure your code is doing what you mean it to be doing by
  giving the compiler more information.
*/
class Anchor(val value: String) extends AnyVal
class Style(val value: String) extends AnyVal
class Text(val value: String) extends AnyVal
class HTML(val value: String) extends AnyVal

def titleImproved(text: Text, anchor: Anchor, style: Style): HTML =
  new HTML(
    s"<a id='${anchor.value}'>" +
      s"<h1 class='${style.value}'>" +
      text.value +
      "</h1></a>"
  )
