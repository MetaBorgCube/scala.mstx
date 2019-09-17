object O {
  object N {
    type I = Int;
  };

  import N.I;
  val x : I = 42;
};
