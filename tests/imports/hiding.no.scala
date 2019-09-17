object O {
  object N {
    type X = Int;
    type Y = Int;
  };

  import N.{X => _, _};

  val x : X = 42;
};
