STATIX ok
SCALAC ok

object O {
  def f: Int = {
    val x: Boolean = true;
    { 
      val x: Int = 42;
      x
    }
  };
};
