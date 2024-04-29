package org.example
//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
fun main() {
    var isValid = false
    var selectedMenu = 0


    while (isValid == false) {
        try {
            selectedMenu = getMenu()
            isValid = true
        } catch (e: Exception) {
            isValid = false
        }
    }

    when (selectedMenu) {
        1 -> startGame()
        2 -> gameInfo()
        3 -> endGame()
    }

}

fun getMenu(): Int {
    println()
    println("환영합니다! 원하시는 번호를 입력해주세요!")
    println("1. 게임 시작하기 | 2. 게임 기록 보기 | 3. 종료하기")
    print(": ")
    val userInput = readln().toInt()

    if (userInput < 1 || userInput > 3) {
        println("입력이 잘못되었습니다.")
        return getMenu()
    }
    return userInput
}

fun startGame() {
    val answer = mutableSetOf<Int>(0, 0, 0)
    while (answer.size < 3) {
        val randomInt = (0..9).random()
        answer.add(randomInt)
        if(answer.toList()[0] == 0) {
            answer.remove(randomInt)
        } else{
            continue
        }
    }
    println(answer)
    val correctAnswer = answer.toString().toList().filter { it.isDigit() }
    for (i in 1..9) {
        println()
        println("숫자를 입력하세요")
        print(":")
        val InputAnswer = readln().toList()

        val score = mutableListOf<Int>(0, 0, 0)

        if (InputAnswer == correctAnswer) {
            println("$answer 정답입니다. (${i}회 시도)")
            break
        } else {
            correctAnswer.forEachIndexed { index, count ->
                val point = InputAnswer.indexOf(count)
                when {
                    point == index -> ++score[0]
                    point != -1 -> ++score[1]
                    else -> ++score[2]
                }

            }
            println("${score[0]} strike / ${score[1]} ball / ${score[2]} out")
            continue
        }
    }
        println("게임 오버!")
        println("정답은? : $correctAnswer")
}

fun gameInfo() {


}

fun endGame() {


}