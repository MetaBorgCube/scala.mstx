object O {
  object M {
    type A = Int;
  };
  object N {
    type A = Boolean;
  };

  import M.A;
  object I {
    import N._;
    val x : A = 3;
  };
};