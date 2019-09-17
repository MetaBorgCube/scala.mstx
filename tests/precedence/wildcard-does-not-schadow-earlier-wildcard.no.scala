object O {
  object M {
    type A = Int;
  };
  object N {
    type A = Boolean;
  };

  object I {
    import N._;
    import M._;
    val x : A = 3;
  };
};
