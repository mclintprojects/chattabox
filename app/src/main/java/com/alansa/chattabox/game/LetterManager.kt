package com.alansa.chattabox.game

import android.databinding.ObservableField
import java.util.*

class LetterManager {
    private var currentCharCode = 65
    val currentLetter = ObservableField("")

    fun nextLetter() {
        currentLetter.set(getRandomLetter())
    }

    private fun getRandomLetter(): String {
        val randomCharCode = (Random().nextInt(90 + 1 - 65) + 65)
        if (randomCharCode != currentCharCode) {
            currentCharCode = randomCharCode
            return randomCharCode.toChar().toString()
        } else return getRandomLetter()
    }
}