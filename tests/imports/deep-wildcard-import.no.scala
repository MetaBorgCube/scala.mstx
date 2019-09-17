object O1 {
  type T = Boolean;
  object O2 {
    type T = Int;
    object O3 {
      object O4 {
        val x : T = 42;
        object O5 {};
      };
    };
  };
};

object N {
  import O1.O2.O3.O4.O5._;
  val y : Int = x;
};
