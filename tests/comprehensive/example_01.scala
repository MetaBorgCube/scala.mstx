// name juggling using different kinds of imports, and qualified access

object Numbers {
  type I = Int;
  
  def const(i1: I)(i2: I): Int = i1;
  def flip(f: I => I => I)(i1 : I)(i2 : I): Int = {
    f(i2)(i1)
  };
};

object Test {
  import Numbers.{I => Number, _}; // const is is scope as a weak import
  
  def compute_42(i : Number): Number = {
    val x: Int = const(i)(18);
    import Numbers.{flip => const}; // explicit import shadows outer const
    {
      // the outer const refers to the one from the lexical scope
      // being the renamed flip
      const(Numbers.const)(x)(42)
    }
  };
};
