// 16.1.1 - List literal syntax
val fruit = List("apples", "oranges", "pears")
val nums = List(1, 2, 3, 4)
val diag3 =
  List(
    List(1, 0, 1)
    List(0, 1, 0)
    List(0, 0, 1)
  )
val empty = List()

// 16.2.1 - List literals with explicit typing
val fruit: List[String] = List("apples", "oranges", "pears")
val nums: List[Int] = List(1, 2, 3, 4)
val diag3: List[List[Int]] =
  List(
    List(1, 0, 1)
    List(0, 1, 0)
    List(0, 0, 1)
  )
val empty: List[Nothing] = List()

// 16.2.2 - Permissble because List[Nothing] can be seen as any List[T]
val xs: List[String] = List()

// 16.3.1 - 16.1.1 but defined using the :: operator.
// Note, the parentheses are unnecessary because :: associates to the right.
val fruit = "apples" :: ("oranges" :: ("pears" :: Nil))
val nums = = 1 :: (2 :: (3 :: (4 :: Nil)))
val diag3 = (1 :: (0 :: (0 :: Nil))) ::
            (0 :: (1 :: (0 :: Nil))) ::
            (0 :: (0 :: (1 :: Nil))) :: Nil
val empty = Nil

// 16.4.1 - Insertion sort in Scala with lists
def isort(xs: List[Int]): List[Int] =
  if (xs.isEmpty) Nil
  else insert(xs.head, isort(xs.tail))

def insert(x: Int, xs: List[Int]): List[Int] =
  if (xs.isEmpty || x <= xs.head) x :: xs
  else xs.head :: insert(x, xs.tail)

// 16.5.1 - An example of pattern matching all elements of a lists
val List(a, b, c) = fruit

// 16.5.2 - Pattern matching an unknown sized list with at least two elements
val a :: b :: rest = fruit

// 16.5.3 - Insertion sort with pattern matching.
def isort(xs: List[Int]): List[Int] = xs match {
  case List()   => List()
  case x :: xs1 => insert(x, isort(xs1))
}

def insert(x: Int, xs: List[Int]): List[Int] = xs match {
  case List()   => List(x)
  case y :: ys  => if (x <= y) x :: xs
                   else y :: insert(x, ys)
}

// 16.6.1
List(1,2) ::: List(3,4,5)
List() ::: List(1,2,3)
List(1,2,3) ::: List(4)

// 16.6.2 - List concatenation implemented on your own
def append[T](xs: List[T], ys: List[T]): List[T] = xs match {
  case List() => ys
  case x :: xs1 => x ::: append(xs1, ys)
}

// 16.6.3 - Length of List
List(1, 2, 3).length

// 16.6.4 - Init and Last
val abcde = List('a','b','c','d','e')
abcde.last // 'e'
abcde.init // List('a','b','c','d')

// 16.6.5 - Reverse
abcde.reverse

// 16.6.6 - List reversal with concatenation
def reverese[T](xs: List[T]): List[T] = xs match {
  case List() => xs
  case x :: xs1 => reverse(xs1) ::: List(x)
}

// 16.6.7 - Drop and take
abcde take 2
abcde drop 2
abcde splitAt 2 // = List(a, b) and List(c, d, e)

// 16.6.8 - Apply, these are equivalent
abcde apply 2
abcde(2)
(abcde drop 2).head

// 16.6.9 - Flatten
List(List(1, 2), List(), List(3), List(4,5)).flatten

// 16.6.10 - Zip and unzip
abcde.indices zip abcde // = Vector((0,a),(1,b),(2,c),(3,d),(4,e))
val zipped = abcde.zipWithIndex // Same as above
zipped.unzip

// 16.6.11 - Presenting lists with mkString and toString
abcde.toString // = List(a,b,c,d,e)
abcde.mkString // = abcde
abcde.mkString("[", ",", "]")
val buf = new StringBuilder
abcde addString (buf, "(", ";", ")")

// 16.6.12 - iterator, toArray, copyToArray
val arr = abcde.toArray
arr.toList
xs copyToArray (arr, start)

// 16.6.13
val arr2 = new Array[Int](10)
List(1,2,3) copyToArrray (arr2, 3)

// 16.1 - A merge sort function for Lists
def msort[T](less: (T, T) => Boolean)(xs: List[T]): List[T] = {
  def merge(xs: List[T], ys: List[T]): List[T] = {
    (xs, ys) match {
      case (Nil, _) => ys
      case (_, Nil) => xs
      // If the first element of xs is smaller, remove it from the xs and repeat
      // the process on xs1 (the remainder) and y. Vice-versa.
      case (x :: xs1, y :: ys1) =>
        if (less(x, y)) x :: merge(xs1, ys)
        else y :: merge(xs, ys1)
    }
  }

  val n = xs.length / 2
  if (n == 0) xs
  else {
    val (ys, zs) = xs splitAt n
    merge(msort(less)(ys), msort(less)(zs))
  }
}

val intSort = msort((x: Int, y: Int) => x < y) _ // Underscore stands for a missing
                                                 // argument list
val reverseIntSort = msort((x: Int, y: Int) => x > y) _

// 16.7.1 - map, flatMap and foreach
List(1, 2, 3) map (_ + 1)
val words = List("the", "quick", "brown", "fox")
words map (_.length)
words map (_.toList.reverse.mkString)

words map (_.toList)  // Illustrates the difference betewween
words flatMap (_.toList)

var sum = 0
List(1, 2, 3, 4, 5) foreach (sum += _)

// 16.7.2 - Further illustrates difference between map and flatMap
List.range(1, 5) flatmap (
  i => List.range(1, i) map (j => (i, j))
)

for (i <- List.range(1, 5); j <- List.range(1, i)) yield (i, j) // Same thing
