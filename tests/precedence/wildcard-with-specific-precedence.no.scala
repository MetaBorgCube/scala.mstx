STATIX fail stdlib\.sets\.same-target
SCALAC fail reference to x is ambiguous

object O {
  object N {
    val x : Int = 42;
  };
  object M {
    val x : Int = 42;
  };

  import N.{x,_};
  object I {
    import M._;
    val y : Int = x;
  };
};