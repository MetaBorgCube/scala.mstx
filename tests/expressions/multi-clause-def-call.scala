STATIX ok
SCALAC ok

object O {
  def f(x: Int)(y: Boolean): Boolean = y;
  def g: Boolean = f(42)(true); // currying correct
};
