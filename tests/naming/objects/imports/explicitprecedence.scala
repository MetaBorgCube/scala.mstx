object A  {
  def f = true;
};

object B  {
  def f = 42;
};

object C {
  import B.f;
  import A._;

  def g: Int = f;
};