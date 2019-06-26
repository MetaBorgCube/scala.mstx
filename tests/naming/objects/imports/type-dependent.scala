abstract class A {
  def f: A;
};

class B extends A {
  def f = this;
};


class C extends A {
  def f = this;
};

object Main {
  val a = new C {};

  import a._;

  def g: C = f;
};
