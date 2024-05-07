package org.example

fun main() {
    var isValidMenu = false

    while (!isValidMenu) {
        try {
            BullsAndCows().getMenu()
            isValidMenu = true
        } catch (e: Exception) {
            isValidMenu = false
        }
    }
}