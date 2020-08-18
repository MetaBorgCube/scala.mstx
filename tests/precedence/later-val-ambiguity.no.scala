STATIX fail scala\.def\.no-duplicate-defs.*\"x\"
SCALAC fail x is already defined as value x

object O {
  val x : Int = 42;
  val y : Int = x;
  val x : Boolean = true;
};