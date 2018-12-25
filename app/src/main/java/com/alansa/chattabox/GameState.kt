package com.alansa.chattabox

import com.alansa.chattabox.game.GameManager
import com.alansa.chattabox.game.PlayerManager
import com.alansa.chattabox.game.ScoreManager
import com.alansa.chattabox.game.TimeManager

class GameState {
    companion object {
        lateinit var gameManager: GameManager

        init {

        }

        fun initialize() {
            gameManager = GameManager(PlayerManager(ScoreManager()), TimeManager())
        }
    }
}