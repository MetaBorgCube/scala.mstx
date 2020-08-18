STATIX ok
SCALAC ok

object O1 {
  type T = Boolean;
  object O2 {
    type T = Int;
    object O3 {
      object O4 {
        object O5 {
          val x : T = 42;
        };
      };
    };
  };
};

object N {
  import O1.O2.O3.O4.O5.x;
  val y : Int = x;
};
