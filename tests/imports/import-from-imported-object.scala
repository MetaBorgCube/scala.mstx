STATIX ok
SCALAC ok

object A {
  object B {
    type X = Int;
  };
};

object C {
  import A.B;
  import B._;
  val y : X = 42;
};