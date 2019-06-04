object A {
  import B._
  def g: Int = f
}

object B {
  import A._
  def f: Int = g
}
