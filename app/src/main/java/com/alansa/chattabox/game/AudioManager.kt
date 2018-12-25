package com.alansa.chattabox.game

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.*

class AudioManager(ctx: Context) : TextToSpeech.OnInitListener {
    private val tts = TextToSpeech(ctx, this)
    var ttsInitialized = false

    override fun onInit(result: Int) {
        if(TextToSpeech.SUCCESS == result) {
            ttsInitialized = true
            tts.setLanguage(Locale.US)
        }
    }

    fun speak(text: String){
        if (ttsInitialized) tts.speak(text, TextToSpeech.QUEUE_ADD, null, "")
    }

}