object A  {
  def f = true;
};

object B  {
  def f = 42;
};

object C {
  import B._;
  import A._;

  def g: Int = f;
};
