STATIX fail
SCALAC fail

object O {
  def f(x: Int)(y: Boolean): Boolean = y;
  def g: Boolean = f(42, true); // currying wrong
};
