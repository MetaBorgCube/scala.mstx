object O {
  object N {
    type X = Int;
    type Y = Boolean;
  };

  import N.{X => Z};

  val x : Y = true;
};
