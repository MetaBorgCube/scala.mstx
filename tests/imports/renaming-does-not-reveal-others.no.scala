STATIX fail scala\.type\.reference\.typeref-ok.*\"Y\"
SCALAC fail not found\: type Y

object O {
  object N {
    type X = Int;
    type Y = Boolean;
  };

  import N.{X => Z};

  val x : Y = true;
};
