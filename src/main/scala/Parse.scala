import cats.parse.Parser
import cats.parse.Parser0
import cats.parse.Rfc5234.{alpha, sp}
import cats.data.NonEmptyList

object Parsers {
  val $ = Parser.char('$')
  val open = Parser.char('(')
  val close = Parser.char(')')
  val end = Parser.char('\n')
  val id = alpha.rep.map(_.toList.mkString)
  val expr = Parser.recursive { recurse =>
    val sub = recurse.between($ ~ open, close)
    val call = id ~ (sp *> Parser.oneOf(sub :: id :: Nil)).rep0.map(_.toList)
    Parser.oneOf(call :: id :: Nil)
  }
}

def parse(s: String) = Parsers.expr.parse(s)