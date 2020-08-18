STATIX fail scala\.expr\.path-expr-ok.*UnqualifiedId\(\"x\"\)
SCALAC fail value x is not a member of object O1\.O2\.O3\.O4\.O5

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
  import O1.O2.O3.O4.O5.x;
  val y : Int = x;
};
