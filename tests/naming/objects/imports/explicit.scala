object A {
  def f = 42
}

object B {
  import A.f
  def g = f
}
