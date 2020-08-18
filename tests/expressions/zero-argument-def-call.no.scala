STATIX fail
SCALAC fail

object O {
  def f: Int = x;
  def g: Int = f(); // no automatic conversion
};
