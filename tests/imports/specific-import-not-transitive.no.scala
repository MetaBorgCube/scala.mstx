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
