object A {
  object B {
    def f = 42
  }
}

object C {
  import A.B
  def g = f // unbound
}
