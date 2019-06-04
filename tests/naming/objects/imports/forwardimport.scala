object A {
  import B._
  def g = f
}

object B {
  def f = 42
}