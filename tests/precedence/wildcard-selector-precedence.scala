STATIX ok
SCALAC ok

object O {
  object N {
    val x : Int = 42;
  };
  object M {
    val x : Int = 42;
  };

  import N.{_};
  object I {
    import M._;
    val y : Int = x;
  };
};