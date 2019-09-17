object O {
  object N {
    type T = Int;
    type S = Int;
    type R = Int;
  };

  import N.{T, S};
  val y : R = 42;
};
