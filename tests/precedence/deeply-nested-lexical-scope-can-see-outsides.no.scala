object N {
  type X = Int;
};

object O {
  import N._;
  def g: Boolean = true;
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
