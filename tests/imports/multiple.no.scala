STATIX fail scala\.type\.reference\.typeref-ok.*\"R\"
SCALAC fail not found\: type R

object O {
  object N {
    type T = Int;
    type S = Int;
    type R = Int;
  };

  import N.{T, S};
  val y : R = 42;
};
