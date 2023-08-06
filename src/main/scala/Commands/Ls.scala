package Commands

import java.nio.file.{FileSystems, Files}
import scala.collection.JavaConverters._

def ls(args: List[String]) =
  val path_str = if args.isEmpty then "." else args(0)
  val dir = FileSystems.getDefault.getPath(path_str)
  val x = Files.list(dir).iterator().asScala.flatMap(_.toString()).mkString
  Right(x)