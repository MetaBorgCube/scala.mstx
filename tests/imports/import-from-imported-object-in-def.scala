object c {
  def g(): Unit = { 
    import a.{h => _, b};
    val x: Int = h();
    import b.{h,i};
    val y: Int = i();
  };
  
  def h(): Int = 42;
};

object a {
  def h(): Unit = {};
  object b {
    def h(): Unit = {};
    def i(): Int  = 42;
  };
};
