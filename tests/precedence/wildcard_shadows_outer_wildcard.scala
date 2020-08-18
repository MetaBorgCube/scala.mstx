STATIX ok
SCALAC ok

object O {
  object M {
    type A = Int;
  };
  object N {
    type A = Boolean;
  };

  import M._;
  object I {
    import N._;
    val x : A = true;
  };
};