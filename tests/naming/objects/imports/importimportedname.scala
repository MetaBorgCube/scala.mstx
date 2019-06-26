object A {
  object B {
  };
};

object C {
  import A.B;
  import B._;
};