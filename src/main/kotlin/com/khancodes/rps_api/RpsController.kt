package com.khancodes.rps_api

// Importing RestController so that browser will know that I'll handle web requests.
// GetMapping to get specific function to run in the browser.
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

// importing random number generator from kotlin's library to use it for computer's move in the game
import kotlin.random.Random

@RestController // beginning the class with RestController, so Spring knows that we'll handle web requests with the class
class RpsGameController {
    @GetMapping("/play/{playerMove}") // we need a path for GetMapping, {move} part is dynamic to use for different type of inputs
    fun playGame(@PathVariable playerMove: String): String { // by @PathVariable annotation, we're giving value to the GetMapping path
        val computerMovesList = listOf("rock \uD83E\uDEA8", "paper \uD83D\uDCC4", "scissors ✂\uFE0F")
        val randomNum = Random.nextInt(3)
        val computerMoves = computerMovesList[randomNum] // made the computer choose a random number by index from the list.

        val gameResult = when { // Actual game logic where we compare computer's move and player's move and display an outcome!
            playerMove == computerMoves -> "It's a draw! 😒"
            computerMoves == "rock \uD83E\uDEA8" && playerMove == "scissors ✂\uFE0F" -> "You lose! Lmaooo 🤣🤣"
            computerMoves == "paper \uD83D\uDCC4" && playerMove == "rock \uD83E\uDEA8" -> "You lose! Lmaooo 🤣🤣"
            computerMoves == "scissors ✂\uFE0F" && playerMove == "paper \uD83D\uDCC4" -> "You lose! Lmaooo 🤣🤣"
            playerMove !in computerMovesList -> "Invalid move ! Please write among rock, paper, scissors."
            else -> "Omg! You beat me!😮 You win! 😭"
        }
        return "Your move: $playerMove. \nComputer move: $computerMoves. \nResult: $gameResult"
    }
}

