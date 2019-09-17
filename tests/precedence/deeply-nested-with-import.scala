object I {
};
object O1 {
  type T = Boolean;
  object O2 {
    type T = Int;
    object O3 {
      import I._;
      object O4 {
        object O5 {
          val x : T = 42;
        };
      };
    };
  };
};
