object A {
  import B._;
  val x : Int = y;
};

object B {
  import A._;
  val y : Int = x;
};
