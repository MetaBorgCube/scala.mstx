STATIX ok
SCALAC ok

object A {
  type X = Int;
  type Y = X;
  type Z = Y;
  val x: Z = 42;
};