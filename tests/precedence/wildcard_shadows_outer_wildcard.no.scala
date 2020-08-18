STATIX fail unification error
SCALAC fail required\: O\.N\.A

object O {
  object M {
    type A = Int;
  };
  object N {
    type A = Boolean;
  };

  import M._;
  object I {
    import N._;
    val x : A = 42;
  };
};
