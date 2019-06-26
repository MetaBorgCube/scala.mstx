object A {
  object B {};
};

object C {
  object B {};
};

object D {
  import A.B;
  import C.B;
  import B._;
};
