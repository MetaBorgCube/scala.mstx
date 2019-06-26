class C {
  def f = 42;
};

object A extends C;

object B {
  import A._;
  def g = f;
};
