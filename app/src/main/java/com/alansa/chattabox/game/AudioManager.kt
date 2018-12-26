package com.alansa.chattabox.game

import android.media.MediaPlayer
import android.speech.tts.TextToSpeech
import java.util.*

class AudioManager(private val tts: TextToSpeech, private val tickPlayer: MediaPlayer) {
    init {
        tts.language = Locale.US
    }

    fun speak(text: String) {
        try {
            tts.speak(text, TextToSpeech.QUEUE_ADD, null, "")
        } catch (e: Exception) {
        }
    }

    fun playTick() = tickPlayer.start()
}