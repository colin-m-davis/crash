def eval(tree: Any): Either[String, String] =
  val (command, rest) = tree.asInstanceOf[Tuple2[String, List[Any]]]
  val args = rest.map(eval(_))
  command match
    case "ls" => Right("<list files>")
    case "cd" => Right("<change directory>")
    case _ => Left("unknown command")
  