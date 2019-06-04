object A {
  def f = 42
}

object B {
  def f = true
  import A.f
  def g: Boolean = f
}
