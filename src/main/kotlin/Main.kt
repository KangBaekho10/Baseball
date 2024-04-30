package org.example

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
fun main() {
    var isValid = false
    var selectedMenu = 0

    while (!isValid) {
        try {
            selectedMenu = getMenu()
            isValid = true
        } catch (e: Exception) {
            isValid = false
        }
    }

    when (selectedMenu) {
        1 -> startGame()
        2 -> endGame()
        3 -> gameInfo()
    }
}

fun getMenu(): Int {
    println()
    println("환영합니다! 원하시는 번호를 입력해주세요!")
    println("1. 게임 시작하기 | 2. 게임 종료하기 | 3. 게임 기록 보기")
    print(": ")
    val userInput = readln().toInt()

    if (userInput < 1 || userInput > 3) {
        println("올바른 숫자를 입력해주세요!")
        return getMenu()
    }
    return userInput
}

fun startGame(): Any {
    val recordList: MutableList<Map<Int, Int>> = mutableListOf()
    val num1 = 0
    val keys = 0
    val values = 0
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
    println()
    println("< 게임을 시작합니다 >")
    // println(answer) 테스트용 정답
    val correctAnswer = answer.toString().toList().filter { it.isDigit() }
    for (i in 1..9) {
        println()
        println("숫자를 입력하세요")
        print(": ")
        val inputAnswer = readln().toList()

        val score = mutableListOf<Int>(0, 0, 0)

        if (inputAnswer == correctAnswer) {
            println("$answer 정답입니다. (${i}회 시도)")
            val record = mapOf(keys to num1, values to i)
            recordList.add(record)
            break
        } else {
            if (i < 9) {
                correctAnswer.forEachIndexed { index, count ->
                    val point = inputAnswer.indexOf(count)
                    when {
                        point == index -> ++score[0]
                        point != -1 -> ++score[1]
                        else -> ++score[2]
                    }
                }
                println("${score[0]} 스트라이크 | ${score[1]} 볼 | ${score[2]} 아웃")
                continue
            } else {
                println("게임 오버!")
                println("정답은? : $correctAnswer")
            }
        }
    }
    println("게임을 다시 할까요? ('1' : Yes / '2' : No)")
    print(": ")
    val retry: String = readln()
    return if (retry == "1") {
        startGame()
    } else {
        main()
    }
}


fun endGame() {
    println("< 숫자 야구 게임을 종료합니다 >")
    return
}


fun gameInfo() {
//        var trying = 0
//        val gamerecordList = StartGame.recordList()
//        println("< 게임 기록 보기 >")
//        println("")
//        for (recording in recordList) {
//            ++trying
//            recording.forEach { println("$trying 번째 게임 : 시도 횟수 - ${it.value}회") }
//        }
//    기록 저장 수정 예정
}


