object N {
  val x : Int = 42;
};
object O {
  import N._;
  def f: Int = {
    x
  };
};