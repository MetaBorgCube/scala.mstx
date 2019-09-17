object O {
  object N {
    type I = Int;
  };

  import N._;
  val x : I = 42;
};