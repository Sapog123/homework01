package fintech.homework01

// Используя функции io.readLine и io.printLine напишите игру "Виселица"
// Пример ввода и тест можно найти в файле src/test/scala/fintech/homework01/HangmanTest.scala
// Тест можно запустить через в IDE или через sbt (написав в консоли sbt test)

// Правила игры "Виселица"
// 1) Загадывается слово
// 2) Игрок угадывает букву
// 3) Если такая буква есть в слове - они открывается
// 4) Если нет - рисуется следующий элемент висельника
// 5) Последней рисуется "веревка". Это означает что игрок проиграл
// 6) Если игрок все еще жив - перейти к пункту 2

// Пример игры:

// Word: _____
// Guess a letter:
// a
// Word: __a_a
// Guess a letter:
// b
// +----
// |
// |
// |
// |
// |

// и т.д.

class Hangman(io: IODevice) {
  def play(word: String): Unit = {
    val letters = word.split("")
    var current = new Array[String](letters.length)
    for(n <- 0 to current.length-1) current(n) = "_"
    var points = 0
    var badAttempt = 0

    while (points != letters.length && badAttempt < stages.size)
      {
        var output = "Word: " + current.mkString("")
        io.printLine(output)
        io.printLine("Guess a letter:")
        var letter = io.readLine()
        var flag =true
        for(n <- 0 to current.length-1)
          {
            if(letters(n) == letter)
              {
                flag = false
                current(n)=letters(n)
                points= points+1
              }
          }
        if(flag)
          {
            badAttempt+=1
          }
        if(badAttempt>0)io.printLine(stages(badAttempt-1))
      }
    if(badAttempt == stages.size) io.printLine("You are dead")
    else  io.printLine("You are win")


  }

  val stages = List(
    """+----
      ||
      ||
      ||
      ||
      ||
      |""".stripMargin,
    """+----
      ||
      ||   O
      ||
      ||
      ||
      |""".stripMargin,
    """+----
      ||
      ||   O
      ||   |
      ||
      ||
      |""".stripMargin,
    """+----
      ||
      ||   O
      ||   |
      ||  /
      ||
      |""".stripMargin,
    """+----
      ||
      ||   O
      ||   |
      ||  / \
      ||
      |""".stripMargin,
    """+----
      ||
      ||   O
      ||  /|
      ||  / \
      ||
      |""".stripMargin,
    """+----
      ||
      ||   O
      ||  /|\
      ||  / \
      ||
      |""".stripMargin,
    """+----
      ||   |
      ||   O
      ||  /|\
      ||  / \
      ||
      |""".stripMargin
  )
}

trait IODevice {
  def printLine(text: String): Unit
  def readLine(): String
}
