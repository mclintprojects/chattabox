package com.alansa.chattabox.game

import android.databinding.ObservableField
import java.util.*

open class LetterManager {
    private var currentCharCode = 65
    private var prevChars = mutableListOf<Int>()
    val currentLetter = ObservableField("")

    fun chooseNextLetter() {
        currentLetter.set(getRandomLetter())
        if (prevChars.size > 5) prevChars.removeAt(prevChars.size - 1)
    }

    private fun getRandomLetter(): String {
        val randomCharCode = (Random().nextInt(90 + 1 - 65) + 65)
        if (randomCharCode != currentCharCode && !prevChars.contains(randomCharCode)) {
            currentCharCode = randomCharCode
            prevChars.add(randomCharCode)
            return randomCharCode.toChar().toString()
        } else return getRandomLetter()
    }
}