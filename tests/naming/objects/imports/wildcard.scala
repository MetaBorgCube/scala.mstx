object A {
  def f = 42;
};

object B {
  import A._;
  def g = f;
};