object O {
  object A {
    type x = Int;
    val x = 3;
  };
  import A.x;

  val y : x = x;
}
