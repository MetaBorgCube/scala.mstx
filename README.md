# Statix Specification of Scala Imports

We specify the binding of Scala objects with the various imports in Scala in Statix.
The following features are specified:

- `object` declarations
- type annotated `val` declarations in objects
- type declarations in objects
- function defs in objects with multiple, n-ary parameter clauses
- block expressions with and without return expressions
- lexical scoping
- wildcard imports `import A.B.C._`
- specific name imports `import A.B.C`/`import A.B.x`
- import selectors `import A.B.{C,D}`
- import hiding `import A.B.{C => _,_}`
- import renaming `import A.B.{C => D}`

Some silly programs that show of most of these features can be found in
[./tests/comprehensive/](./tests/comprehensive/). For example:

```scala
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
  
  // A nested Functions module.
  // In Rust, importing the content of the outer Functions object
  // would result in ambiguity, because the inner one would be revealed and
  // make the import ambiguous.
  // In Scala this is fine, because imports open only in subsequent scope.
  object Functions {};
};

object Test {
  import Numbers.{I => Number, _}; // const is in scope via the wildcard import
  
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
```

Scoping rules follow the [Scala specification](https://www.scala-lang.org/files/archive/spec/2.13/02-identifiers-names-and-scopes.html).

## Installation

Running the spec on Scala programs requires Ministatix, the Scala spoofax frontend, and
a Scala compiler to be installed.

Instructions for ministatix can be found in the 
[MiniStatix](https://github.com/metaborg/ministatix.hs/) repository.

The Scala spoofax frontend can be build using the `scalafront` make target.
This will download a Spoofax binary and build the parser in a project local directory.

If this recipe does not work for you, please report an 
[issue](https://github.com/metaborg/ministatix.hs/issues).
 
## Usage

The root of the specification can be found in [src/scala.mstx](./src/scala.mstx).

This repository includes a handcrafted test suite of Scala programs in a subset of
the official Scala syntax.
Besides the limitation on the features listed above, a peculiarity of the current parser
is that it enforces semi-colons after every statement/definition.

You can run the entire test suite using:

    make test-clean test

You can run individual test cases using the `./run a/b/test.{no.}scala` 
script in the [./tests/](./tests/) directory.
This will run the scala compiler on it (assuming it is installed) first.
The `no` infix in the filename denotes whether the test is expected to typecheck or not.

Then the script will run the spoofax Scala parser to produce an aterm, which
will then be fed into ministatix along with the specification entrypoint.

Finally the output of both type-checkers is compared to produce a test result.

## Notes on the implementation

This was developed as a case-study for the query delay mechanism in (Mini)Statix.

A difficulty with the implementation was the precedence order on different resolution paths
in Scala.
Path orders in (Mini)Statix are traditionally specified as (lexicographical) 
liftings of label orders.
The Scala path order cannot be constructed in that way.

We aim to extend the language of order definitions in (Mini)Statix, but it is as of yet
unclear how expressive that language should be.
As a work-around for this case-study the label order is built into Ministatix.
This does not in anyway affect scheduling: the delay mechanism works as described in the
paper, which does not assume any particular form for the path order.

### Syntax

The grammar used for the subset tries to follow the structure of the 
[official Scala grammar](https://www.scala-lang.org/files/archive/spec/2.13/13-syntax-summary.html).
This leads to some seemingly unnecessary indirection in the grammar, but makes it easier
to extend the subset in the future.

### Scala precedence as a path order

Scala paths are of the form: 

    B*(PB*){m}(I|W)?(V|T)

Here:

- `B` denotes a "backward" edge induced by import statements in a block scope
- `P` denotes a lexical "parent" edge induced by nesting block scope
- `I` denotes a named "import" edge
- `W` denotes a "wildcard" import edge
- `V` denotes a value declaration
- `T` denotes a type declaration

On these paths we define the scala order as:

    B*(PB*){m}x(V|T) < B*(PB*){n}y(V|T)
    
    iff (m = n and x <ₗ y) or (m < n and x ≤ₗ y)
    
Where `<ₗ` is the strict label order `ε <ₗ I <ₗ W`, and `≤ₗ` is the reflexive closure of `<ₗ`.
