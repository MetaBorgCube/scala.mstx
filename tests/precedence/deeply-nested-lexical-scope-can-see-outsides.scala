object N {
  type X = Int;
};

object O {
  import N._;
  def g: Int = 42;
  def f: Int = {
    {
      {
        def h: X = {
          {
            g
          }
        };

        h
      }
    }
  };
};