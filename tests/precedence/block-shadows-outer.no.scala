STATIX fail unification error
SCALAC fail type mismatch

object O {
  val x: Int = 42;
  def f: Int = {
    val x: Boolean = true;
    x
  };
};
