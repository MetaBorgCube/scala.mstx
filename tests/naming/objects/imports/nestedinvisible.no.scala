object A {
  object B {
    def f = 42;
  };
};

object C {
  import A._;
  def g = f; // unbound ref
};
