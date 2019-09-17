object A {
  object C {
    type Y = Int;
  };
  object B {
    import C._;
  };
};

object O {
  type Y = A.B.Y;
};