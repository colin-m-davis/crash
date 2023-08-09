import Command.{pwd, ls}

case class EvalError(what: String)

def eval(env: Env, tree: Any): Either[EvalError, String] =
  try
    val (command, rest) = tree.asInstanceOf[Tuple2[String, List[Any]]]
      rest.map(eval(env, _)).partition(_.isLeft) match
      case (Nil, rights) => 
        val args = rights.map(_ match 
          case Right(response) => response
          case _ => throw new IllegalStateException)
        execute(command, args)
      case (lefts, _) =>
        Left(EvalError(lefts.foldLeft(String())((accum, left) => left match
          case Left(EvalError(what)) => accum + what + '\n'
          case _ => throw new IllegalStateException)))
  catch case e: ClassCastException => Right(tree.asInstanceOf[String])

def execute(command: String, args: List[String]) = 
  val executionResult = command match
    case "pwd" => pwd(args)
    case "ls" => ls(args)
    // case "cd" => cd(args)
    case _ => Left(Command.UnknownCommandError)
  executionResult match
    case Left(Command.UnknownCommandError) => Left(EvalError(s"unknown command $command"))
    case Left(Command.ExecutionError(what)) => Left(EvalError(s"$command: $what"))
    case Right(response) => Right(response)