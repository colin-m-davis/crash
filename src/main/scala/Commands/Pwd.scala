package Commands

def pwd(args: List[String]) =
  try Right(System.getProperty("user.dir"))
  catch case e: SecurityException => Left("insufficient permissions")
