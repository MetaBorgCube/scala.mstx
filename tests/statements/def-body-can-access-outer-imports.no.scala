object N {
  val x : Boolean = true;
};
object O {
  import N._;
  def f: Int = {
    x
  };
};
