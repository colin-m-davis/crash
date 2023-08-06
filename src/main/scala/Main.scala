import cats.effect.IO
import cats.effect.std.Console
import cats.effect.unsafe.implicits.global

@main def hello: Unit =
  while (true) {
    program.unsafeRunSync()
  }

val program =
  for
    _ <- Console[IO].print("> ")
    line <- Console[IO].readLine
    _ <- Console[IO].println(interpret(line))
  yield (interpret(line))

def interpret(s: String) =
  for
    parsed <- parse(s)
    result <- eval(parsed._2)
  yield (result)