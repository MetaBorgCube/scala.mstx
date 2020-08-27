STATIX fail stdlib\.sets\.same-target
SCALAC fail reference to f is ambiguous

object A {
  object I {
    val f: Boolean = true;
  };

  object B {
    import I.f;
    val g: Int = f;
  };

  val f: Int = 42;
};
