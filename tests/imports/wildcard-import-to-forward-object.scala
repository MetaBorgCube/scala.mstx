STATIX ok
SCALAC ok

object A {
  import B._;
  val y : X = 42;
};

object B {
  type X = Int;
};