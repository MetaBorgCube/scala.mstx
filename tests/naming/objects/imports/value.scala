class A {
  def f = 42;
};

object Main {
  val a = new A {};

  import a._;

  def g = f;
};