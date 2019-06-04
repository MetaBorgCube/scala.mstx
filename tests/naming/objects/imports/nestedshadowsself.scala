object A {
  object A { def f = 42 }
  import A._
  def g = f
}