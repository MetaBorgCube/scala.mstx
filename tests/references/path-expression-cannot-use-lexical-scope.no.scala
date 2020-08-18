STATIX fail scala\.type\.reference\.resolve-object-locally.*\"C\"
SCALAC fail value C is not a member of object A\.B

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