object A {
  object C {
    type Y = Int;
  };
  object B {
    type X = Int;
  };
};

object O {
  type Y = A.B.C.Y;
};