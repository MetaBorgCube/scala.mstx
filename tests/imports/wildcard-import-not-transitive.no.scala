STATIX fail scala\.type\.reference\.typeref-ok.*\"T\"
SCALAC fail not found\: type T

object O {
  object N {
    type T = Int;
  };
  object M {
    import N._;
  };
  import M._;
  val x : T = 42;
};