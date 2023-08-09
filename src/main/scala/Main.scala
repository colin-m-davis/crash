import cats.effect.IO
import cats.effect.std.Console
import cats.effect.unsafe.implicits.global
import cats.parse.Parser
import cats.effect.ExitCode

@main def entry: ExitCode =
  val code = program.unsafeRunSync()
  if code == ExitCode(0) then entry else code

def program =
  for
    _ <- Console[IO].print("> ")
    line <- Console[IO].readLine
    _ <- Console[IO].println(interpret(line))
  yield (ExitCode(0))

def interpret(s: String) =
  val interpreted = for
    parsed <- parse(s)
    result <- eval(defaultEnv, parsed._2)
  yield (result)
  interpreted match
    case Left(Parser.Error(where, _)) => s"error while parsing at $where\n"
    case Left(EvalError(what)) => s"error: $what\n"
    case Right(response) => response