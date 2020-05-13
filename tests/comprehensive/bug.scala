object c {
  def g(): Unit = { 
    import a.{h => _, b};
    // imports are sequenced, so the imported h is not in 
    // scope here but the locally defined h is
    val x: Int = h();
    import b.h;
  };
  
  def h(): Int = 42;
};

object a {
  def h(): Unit = {};
  object b {
    def h(): Unit = {};
  };
};
