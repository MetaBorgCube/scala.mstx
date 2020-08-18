STATIX fail scala\.type\.reference\.typeref-ok.*\"Boolean\"
SCALAC fail found.*Boolean

object O {
  val x : Int = 42;
  def f(x : Boolean): Int = x;
};
