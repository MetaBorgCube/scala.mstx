//
// name juggling using different kinds of imports, and qualified access
//

object Numbers {
  type I = Int;
  
  def const(i1: I)(i2: I): Int = i1;
};

object Functions {
  def const(i1: Int)(i2: Int): Int = i1;

  def flip(f: Int => Int => Int)(i1 : Int)(i2 : Int): Int = {
    f(i2)(i1)
  };
};

object Test {
  import Numbers.{I => Number, _}; // const is is scope as a weak import
  
  def compute_42(i : Number): Number = {
    val x: Int = const(i)(18);

    // explicit (renaming) import shadows outer wildcard import of const,
    // and we hide the const from the Functions object.
    import Functions.{flip => const, const => _};
    {
      // the outer const refers to the one from the lexical scope
      // being the renamed flip
      const(Numbers.const)(x)(42)
    }
  };
};
