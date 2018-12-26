package com.alansa.chattabox

import android.app.Application
import android.media.MediaPlayer
import android.speech.tts.TextToSpeech
import com.alansa.chattabox.game.*

class GameState {
    companion object {
        lateinit var gameManager: GameManager

        fun initialize(app: Application) {
            gameManager = GameManager(app, PlayerManager(ScoreManager()), TimeManager(), LetterManager(), AudioManager(TextToSpeech(app, null), MediaPlayer.create(app, R.raw.tick)))
        }

        fun clear() = gameManager.resetGame()
    }
}