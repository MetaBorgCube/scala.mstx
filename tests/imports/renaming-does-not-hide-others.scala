STATIX ok
SCALAC ok

object O {
  object N {
    type X = Int;
    type Y = Boolean;
  };

  import N.{X => Z,_};

  val x : Y = true;
};
