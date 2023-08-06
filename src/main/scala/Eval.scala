import Commands.pwd

object Eval {
  case class Error(what: String)
}

def eval(tree: Any): Either[String, String] =
  val (command, rest) = tree.asInstanceOf[Tuple2[String, List[Any]]]
  val args = rest.foldLeft(List[String]())((accum, expr) =>
    eval(expr) match
      case Left(err) => throw new IllegalStateException(err)
      case Right(str) => str :: accum
    )
  command match
    case "pwd" => pwd(args)
    case "ls" => ls(args)
    case "cd" => cd(args)
    case _ => Left("Unknown command")