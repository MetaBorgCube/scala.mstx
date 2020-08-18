STATIX fail scala\.type\.path-type-ok.*\"X\"
SCALAC fail not found\: type X

object O {
  object N {
    type X = Int;
    type Y = Int;
  };

  import N.{X => _, _};

  val x : X = 42;
};
