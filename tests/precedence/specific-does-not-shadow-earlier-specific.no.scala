object O {
  object M {
    type A = Int;
  };
  object N {
    type A = Boolean;
  };

  object I {
    import N.A;
    import M.A;
    val x : A = 3;
  };
};
