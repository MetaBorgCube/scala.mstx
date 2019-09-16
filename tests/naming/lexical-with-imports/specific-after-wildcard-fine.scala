object O {
  object M {
    type A = Int;
  };
  object N {
    type A = Boolean;
  };

  object P {
    import N._;
    import M.A;
    val x : A = 3;
  };
};
