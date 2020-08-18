STATIX fail scala\.type\.path-type-ok.*\"Y\"
SCALAC fail type Y is not a member of object A\.B

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