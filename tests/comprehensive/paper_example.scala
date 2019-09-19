object a {
  object b {
    def f(): Unit = {};
  };
};

object c {
  import a._;
  import b.f;
  def g(): Unit = { f() };
};