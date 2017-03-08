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
