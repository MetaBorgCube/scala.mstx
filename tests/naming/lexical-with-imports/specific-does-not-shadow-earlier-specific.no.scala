{
  object M {
    type A = Int
  }
  object N {
    type A = Boolean
  }

  {
    import N.A
    import M.A
    val x : A = 3
  }
}
