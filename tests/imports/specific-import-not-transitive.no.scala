STATIX fail scala\.type\.path-type-ok.*\"T\"
SCALAC fail value T is not a member of object O\.M

object O {
  object N {
    type T = Int;
  };
  object M {
    import N.T;
  };
  import M.T;
  val x : T = 42;
};
