object O {
  object N {
    type T = Int;
    type S = Int;
    type R = Int;
  };

  import N.{T, S};
  val x : T = 42;
  val y : S = 42;
};