object A {
  def f = 42
}

object B {
  def g = f
  import A.f
}
