package org.example

class BullsAndCows (val size: Int = 3) {
    private var a = 1
    private val array = Array<String>(50) { ' '.toString() }
    private val records = array

    fun getMenu(): Int {
        println()
        println("환영합니다! 원하시는 번호를 입력해주세요!")
        println("1. 게임 시작하기 | 2. 게임 기록 보기 | 3. 게임 종료하기")
        print(": ")
        val userInput = readln().toInt()

        when (userInput) {
            1 -> bullsAndCows()
            2 -> history()
            3 -> endGame()
        }

        if (userInput < 1 || userInput > 3) {
            println("올바른 숫자를 입력해주세요!")
            return getMenu()
        }
        return userInput
    }

    private fun randomInt(): MutableSet<Int> {
        val answer = mutableSetOf<Int>(0, 0, 0)
        while (answer.size < 3) {
            val randomInt = (0..9).random()
            answer.add(randomInt)
            if (answer.toList()[0] == 0) {
                answer.remove(randomInt)
            } else {
                continue
            }
        }
        return answer
    }

    private fun bullsAndCows(): Any {
        var i = 1
        val answer = randomInt()
        println()
        println("< 게임을 시작합니다 >")
        // println(answer) // 테스트용 정답코드 출력
        val correctAnswer = answer.toString().toList().filter { it.isDigit() }
        while (i <= 9) {
            i++
            println()
            println("숫자를 입력하세요")
            print(": ")
            try {
                val inputAnswer = readln().toList()
                inputAnswer.toList()
                    .map { it.digitToInt() }
                    .also { if (!this.checkInput(it)) throw RuntimeException() }

                if (inputAnswer.size > 3) {
                    println("올바르지 않은 입력값입니다")
                    i--
                    continue
                }

                val score = mutableListOf<Int>(0, 0, 0)

                if (inputAnswer == correctAnswer) {
                    i -= 1
                    println()
                    println("$answer 정답입니다. (${i}회 시도)")
                    array[a] = "$i"
                    a++
                    break
                } else {
                    if (i < 10) {
                        correctAnswer.forEachIndexed { index, count ->
                            val point = inputAnswer.indexOf(count)
                            when {
                                point == index -> ++score[0]
                                point != -1 -> ++score[1]
                                else -> ++score[2]
                            }
                        }
                        println("${score[0]} 스트라이크 | ${score[1]} 볼 | ${score[2]} Noting")
                        continue
                    } else {
                        i -= 1
                        println()
                        println("게임 오버!")
                        println("정답은? : $correctAnswer")
                        array[a] = "$i"
                        a++
                        break
                    }
                }
            } catch (e: RuntimeException) {
                println("잘못된 입력입니다. 다시 입력해주세요!")
                i--
                continue
            }
        }

        while (true) {
            println()
            println("게임을 계속 할까요? ('1' : Yes / '2' : No)")
            print(": ")
            val retry: String = readln()
            return if (retry == "1") {
                bullsAndCows()
            } else if (retry == "2") {
                getMenu()
            } else {
                println("올바른 숫자를 입력해주세요!")
                continue
            }
        }
    }

    protected open fun checkInput(input: List<Int>): Boolean {
        if (input.size != size) return false
        if (input.distinct().size != size) return false
        return true
    }


    private fun history() {

        println("\n< 게임 기록 보기 >")
        for (trying in 1..9) {
            val record: String = records[trying]
            if (record != " ") {
                println("$trying 번째 게임 , 시도 횟수 : $record")
            }
        }
    }

    private fun endGame() {
        println("< 숫자 야구 게임을 종료합니다 >")
        return
    }
}