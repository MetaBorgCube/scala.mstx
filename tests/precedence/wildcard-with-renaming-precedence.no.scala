STATIX fail stdlib\.sets\.same-target
SCALAC fail reference to y is ambiguous

object O {
  object N {
    val x : Int = 42;
  };
  object M {
    val y : Int = 42;
  };

  import N.{x => y,_};
  object I {
    import M._;
    val z : Int = y;
  };
};
