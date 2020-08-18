STATIX fail scala\.def\.no-duplicate-defs.*\"x\"
SCALAC fail x is already defined as value x

object O {
  def f: Unit = {
    val x: Int = 42;
    val x: Boolean = true;
  };
};