package org.example

fun main() {
    var isValid = false

    while (!isValid) {
        try {
            StartGame().getMenu()
            isValid = true
        } catch (e: Exception) {
            isValid = false
        }
    }
}

fun endGame() {
    println("< 숫자 야구 게임을 종료합니다 >")
    return
}