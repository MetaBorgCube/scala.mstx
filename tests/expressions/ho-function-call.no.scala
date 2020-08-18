STATIX fail
SCALAC fail

object O {
  def f(g : Int => Boolean)(a : Int): Boolean = g(true);
};
