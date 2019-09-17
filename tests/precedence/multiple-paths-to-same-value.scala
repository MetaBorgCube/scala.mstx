object O {
  object N {
    val x : Int = 42;
  };

  import N.x;
  object I {
    import N._;
    val y : Int = x;
  };
};