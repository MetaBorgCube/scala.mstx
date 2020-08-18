STATIX ok
SCALAC ok

object O {
  object A {
    type x = Int;
    val x: Int = 3;
  };
  import A.x;

  val y : x = x;
};
