package Command

def pwd(args: List[String]) =
  try Right(System.getProperty("user.dir") + '\n')
  catch case e: SecurityException => Left(Command.ExecutionError("insufficient permissions\n"))
