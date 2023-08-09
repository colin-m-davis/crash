package Command

sealed trait CommandError
case class ExecutionError(what: String) extends CommandError
case class UnknownCommandError() extends CommandError