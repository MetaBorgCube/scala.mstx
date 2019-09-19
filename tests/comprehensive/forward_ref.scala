object a {
  object b {
    def f(): Unit = {};
  };
};

object c {
  import a._;
  def g(): Unit = { 
    // imports are sequenced, so f is not in scope here
    // but h is
    h();
  };
  import b.f;
  
  def h(): Unit = {};
};
