@main def hello: Unit =
  for
    tree <- parse("ls $(pwd $(gwd))")
    result <- eval(tree._2)
  yield (result)