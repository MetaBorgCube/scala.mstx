STATIX fail scala\.type\.reference\.var-ok.*\"f\"
SCALAC fail not found\: value f

object A {
  def f: Int = 42;
};

object B {
  def g: Int = f;
  import A.f;
};
