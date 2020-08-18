STATIX fail stdlib\.sets\.same-target.*\"x\"
SCALAC fail x is already defined as value x


object O {
  def f(x: Int)(x : Boolean): Boolean = x;
};
