package com.alansa.chattabox

import android.app.Application
import com.alansa.chattabox.game.*

class GameState {
    companion object {
        lateinit var gameManager: GameManager

        fun initialize(app: Application) {
            gameManager = GameManager(app, PlayerManager(ScoreManager()), TimeManager(), LetterManager(), AudioManager(app))
        }

        fun clear() = gameManager.resetGame()
    }
}