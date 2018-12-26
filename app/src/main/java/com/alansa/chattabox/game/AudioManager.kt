package com.alansa.chattabox.game

import android.content.Context
import android.media.MediaPlayer
import android.speech.tts.TextToSpeech
import com.alansa.chattabox.R
import java.util.*

class AudioManager(ctx: Context) : TextToSpeech.OnInitListener {
    private val tts = TextToSpeech(ctx, this)
    private val tickPlayer = MediaPlayer.create(ctx, R.raw.tick)
    private var ttsInitialized = false

    override fun onInit(result: Int) {
        if (TextToSpeech.SUCCESS == result) {
            ttsInitialized = true
            tts.language = Locale.US
        }
    }

    fun speak(text: String) {
        if (ttsInitialized) tts.speak(text, TextToSpeech.QUEUE_ADD, null, "")
    }

    fun playTick() = tickPlayer.start()
}