object B { 
  def f = 42;
  def g: Boolean = {
    val x: Boolean = f;
    return x;

    val f = true;
    return x;
  };
};
