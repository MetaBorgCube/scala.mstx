STATIX ok
SCALAC ok

object A {
  object B {
    val x : Int = 42;
  };
};

object O {
  val x : Int = A.B.x;
};
