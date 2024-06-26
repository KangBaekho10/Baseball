# ⚾ 숫자 야구 게임

프로젝트 심화 과제로 숫자 야구 게임을 만들었습니다. <br/>

사용자로부터 숫자를 입력받아 랜덤으로 생성된 숫자를 맞추는 게임입니다. <br/>

해당 게임은 여러 번 시도할 수 있고 게임의 종료 의사를 밝히면 게임 기록을 보여주도록 구현하였습니다.

## 필수 요구 사항
   **게임**
  > - 정답을 1에서 9까지의 임의의 수 3자리로 만듭니다.
  > - 정답을 맞히기 위해서 3자릿수를 입력하고 힌트를 받습니다.
  > - 힌트는 다음과 같습니다.
>   - 같은 자리에 같은 숫자가 있는 경우 '스트라이크 (Strike)'
>   - 다른 자리에 같은 숫자가 있는 경우 '볼 (Ball)'
>   - 같은 숫자가 전혀 없는 경우 'Noting (Noting)'
  > - 올바르지 않은 입력값에 대해 오류 문구를 보여줍니다.
  > - 3자리 숫자가 정답과 같은 경우 게임이 종료됩니다.

## 선택 요구 사항
   **1번 - 메인메뉴**
  > - 프로그램을 시작할 때 안내 문구를 보여줍니다.
  > - 1번 게임 시작하기를 선택하는 경우 게임이 진행됩니다.
>   - 정답을 맞히고 게임을 다시 하지 않는다면 프로그램 시작과 같은 안내 문구를 보여줍니다.
  > - 2번 게임 기록 보기를 선택하는 경우 게임의 진행 기록으로 보여줍니다.
  > - 3번 게임 종료하기를 선택하는 경우 게임이 종료됩니다.
  > - 이외의 입력값에 대해서는 오류 메시지를 보여줍니다. 

   **2번 - 임의의 숫자**
  > - 게임이 시작되면 자동으로 정답이 되는 0에서 9까지 3자리의 숫자를 생성합니다.
  > - 임의의 3자리 숫자의 제한사항은 다음과 같습니다.
>   - 맨 앞자리 숫자로 0이 오는 것은 불가능합니다.
>   - 3자리의 숫자는 서로 중복될 수 없습니다.

   **3번 - 게임 기록**
  > - 게임 기록 보기를 하는 경우 완료한 게임들에 대해 시도 횟수를 보여줍니다.

   **4번 - 게임 종료**
  > - 게임 종료를 원하는 경우 프로그램이 종료됩니다.

## 코드 구조

main.kt의 함수는 `main()`, `endGame()`으로 이루어져 있습니다.

BullsAndCows.kt의 BullsAndCows()클래스 내에 함수는 `getMenu()`, `randomInt()`, `bullsAndCows()`, `checkInput()`, `history()`, `endGame()`으로 이루어져 있습니다. <br/>

그중 게임에 주요한 역할을 맡고 있는 `getMenu()`, `randomInt()`, `bullsAndCows()`에 대한 설명입니다. <br/>

- `getMenu()` : 프로그램이 시작되면 처음으로 만날 수 있는 게임 시작화면 코드를 담고 있습니다.

``` Kotlin
        println("환영합니다! 원하시는 번호를 입력해주세요!")
        println("1. 게임 시작하기 | 2. 게임 기록 보기 | 3. 게임 종료하기")
        print(": ")
        val userInput = readln().toInt()

        when (userInput) {
            1 -> bullsAndCows()
            2 -> history()
            3 -> endGame()
        }
```

- `randomInt()` : 게임이 시작되면 0에서 9까지의 정수 안에서 임의로 3자리 숫자를 생성합니다. mutableSetof를 통해 중복 값을 가지지 않도록 하였습니다.
  조건문을 이용하여 첫 번째 숫자에는 0이 삽입되지 않도록 하였습니다.

``` Kotlin
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
```

- `startGame()` : 실제 게임이 동작하는 함수입니다.

```Kotlin
// 1번
..
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

// 2번
..
                if (inputAnswer == correctAnswer) {
                    i -= 1
                    println()
                    println("$answer 정답입니다. (${i}회 시도)")
                    array[a] = "$i"
                    a++
                    break
                }
// 3번
..
                  else {
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
```

## 구현 화면

1. 프로그램 시작 시 화면
    - 프로그램이 시작되면 게임이 시작된다는 문장과 함께 메뉴가 출력됩니다.
    - '1'을 입력하면 게임 시작, '2'를 입력하면 게임 기록보기, '3'을 입력하면 게임 종료를 합니다.

``` Kotlin
환영합니다! 원하시는 번호를 입력해주세요!
1. 게임 시작하기 | 2. 게임 기록 보기 | 3. 게임 종료하기
:
// '1' 입력 시 게임시작, '2' 입력 시 게임 기록 확인, '3' 입력 시 게임 종료
```

2. 게임 플레이 화면
    - 게임 플레이가 시작되면 프로그램은 자동으로 3자리 숫자를 생성합니다.
    - 사용자는 정답일 것 같은 숫자를 입력합니다.
    - 9회 동안 '스트라이크|볼|아웃'에 해당하는 힌트를 받으며 정답을 찾습니다.
    - 정답을 맞히게 되면 정답 숫자와 사용자의 시도 횟수를 출력합니다
    - 9번의 시도에도 정답을 맞히지 못하면 게임 오버와 함께 정답 숫자를 출력합니다.
    - 사용자는 게임 재시작 또는 종료를 선택할 수 있습니다.
    - 게임 종료를 선택하면 메인 메뉴로 넘어가게 됩니다.

``` Kotlin
< 게임을 시작합니다 >

숫자를 입력하세요
:
// 3자리의 숫자를 입력합니다.
0 스트라이크 | 0 볼 | 0 아웃
// 자리와 숫자가 같으면 스트라이크 + 1, 숫자만 같으면 볼 + 1, 숫자가 없다면 아웃 + 1

[0, 0, 0] 정답입니다. (0회 시도)
// 정답을 맞췄을 때

게임 오버!
정답은? : [0, 0, 0]
// 끝까지 오답을 입력했을 때

게임을 계속 할까요? ('1' : Yes / '2' : No - 기록 보기)
// '1' 입력 시 게임 재시작, '2' 입력시 게임 종료 및 기록보기
```

3. 게임 기록 화면
    - 게임의 한 횟수와 한 게임당 시도했던 횟수를 보여줍니다.
``` Kotlin
< 게임 기록 보기 >
0 번째 게임 , 시도 횟수 : 0
0 번째 게임 , 시도 횟수 : 0
```

4. 게임 종료 화면
    - 게임의 종료 문구와 함께 프로그램이 종료됩니다.
``` Kotlin
< 숫자 야구 게임을 종료합니다 >
```
   
## 환경 설정<br>
Language : Kotlin<br/>
IDLE : IntelliJ IDEA Ultimate<br/>
JDK : 18.0.2 <br/>
