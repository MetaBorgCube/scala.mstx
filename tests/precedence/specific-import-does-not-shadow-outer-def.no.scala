STATIX fail scala\.type\.reference\.disambiguate-object
SCALAC fail reference to A is ambiguous

object A {
  object A {
  };
};

object C {
  import A.A;
  import A._;
};
