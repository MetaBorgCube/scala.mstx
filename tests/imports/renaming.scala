object O {
  object N {
    type X = Int;
    type Y = Boolean;
  };

  import N.{X => Y};

  val x : Y = 42;
};