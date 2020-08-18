STATIX fail scala\.type\.reference\.var-ok.*\"x\"
SCALAC fail not found\: value x

object A {
  object B {
    val x : Int = 42;
  };
};

object C {
  import A._;
  val y : Int = x;
};
