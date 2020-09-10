package com.example.sweetPlatinum.logic

class Controller {
    companion object {
        val gameChoice = listOf("rock", "paper", "scissor")
        var cpuChoice = ""
    }

    fun multiPlayer(playerOne: String?, playerTwo: String?): String {
        return if (playerOne == gameChoice[0] && playerTwo == gameChoice[2] || playerOne == gameChoice[2] && playerTwo == gameChoice[1] || playerOne == gameChoice[1] && playerTwo == gameChoice[0]) "Player Win"
        else if (playerTwo == gameChoice[0] && playerOne == gameChoice[2] || playerTwo == gameChoice[2] && playerOne == gameChoice[1] || playerTwo == gameChoice[1] && playerOne == gameChoice[0]) "Opponent Win"
        else "Draw"
    }

    fun singlePlayer(playerOne: String?): String {
        cpuChoice = gameChoice.random()
        return if (playerOne == gameChoice[0] && cpuChoice == gameChoice[2] || playerOne == gameChoice[2] && cpuChoice == gameChoice[1] || playerOne == gameChoice[1] && cpuChoice == gameChoice[0]) "Player Win"
        else if (cpuChoice == gameChoice[0] && playerOne == gameChoice[2] || cpuChoice == gameChoice[2] && playerOne == gameChoice[1] || cpuChoice == gameChoice[1] && playerOne == gameChoice[0]) "Opponent Win"
        else "Draw"
    }
}