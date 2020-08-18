STATIX ok
SCALAC ok

object A {
  object B {
    type T = Int;
  };
};

object O {
  val x : A.B.T = 42;
};