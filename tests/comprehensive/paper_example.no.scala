STATIX fail scala\.type\.reference\.var-ok.*\"f\"
SCALAC fail not found\: value f

object a {
  object b {
    def f(): Unit = {};
  };
};

object c {
  import a._;
  def g(): Unit = {
    // imports are sequenced, so f is not in scope here
    f();
  };
  import b.f;
};
