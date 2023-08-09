import scala.collection.immutable.HashMap

type Tree = Any
class Env {
  val values = HashMap[String, String]()
  val functions = HashMap[String, Tree]()
}

val defaultEnv = Env()