object A {
  object B {
    type X = Int;
  };
};

object O {
  type Y = A.B.X;
};