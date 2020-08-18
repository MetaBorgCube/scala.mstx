STATIX ok
SCALAC ok

object O {
  object M {
    type A = Int;
  };
  object N {
    type A = Boolean;
  };

  import N.A;
  object I {
    import M.A;
    val x : A = 3;
  };
};
