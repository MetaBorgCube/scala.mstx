object A {
  def f = 42;
};

object B {
  def f = true;
  object Inner {
     def g = f; // not ambiguous
     import A.f;
  };
};