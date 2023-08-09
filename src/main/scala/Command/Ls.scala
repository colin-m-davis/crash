package Command

import java.nio.file.{FileSystems, Files}
import scala.collection.JavaConverters._
import java.nio.file.InvalidPathException

def ls(args: List[String]) =
  val path_str = if args.isEmpty then "." else args(0)
  try
    val dir = FileSystems.getDefault.getPath(path_str)
    val x = Files.list(dir).iterator().asScala.flatMap(_.toString() + '\n').mkString
    Right(x)
  catch case e: InvalidPathException => Left(Command.ExecutionError("invalid path\n"))