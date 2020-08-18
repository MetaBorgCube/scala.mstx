STATIX fail scala\.type\.reference\.typeref-ok.*\"X\"
SCALAC fail not found\: type X

object O {
  object N {
    type X = Int;
    type Y = Boolean;
  };

  import N.{X => Y};

  val x : X = 42;
};
