object A {
  object B {
    val x : Int = 42;
  };
};

object C {
  import A.B;
  val y : Int = x;
};
