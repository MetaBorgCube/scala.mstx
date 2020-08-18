STATIX ok
SCALAC ok

object A {
  object A { val x : Int = 42; };
  import A._;
  val y : Int = x;
};