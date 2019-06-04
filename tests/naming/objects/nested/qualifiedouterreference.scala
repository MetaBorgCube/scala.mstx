object A {
  def f = true
  object B {
    def g: Boolean = A.f
    def f = 1
  }
}
