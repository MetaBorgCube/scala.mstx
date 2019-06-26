object A {
  def f = 42;
};

object B {
  def f = true;
  object Inner {
     import A.f;
     def g = f(); // ambiguous
  };
};