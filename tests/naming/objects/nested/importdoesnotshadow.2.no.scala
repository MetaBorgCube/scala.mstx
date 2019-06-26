object A {
  object A {};
};
object B { 
  import A._;
  import A._; // ambiguous
};
