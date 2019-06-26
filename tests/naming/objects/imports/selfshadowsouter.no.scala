object A { def f = 42; };
object B {
  object A {
    import A._;
    def g = f; // not available
  };
};