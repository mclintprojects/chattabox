package com.alansa.chattabox.Game

import com.alansa.chattabox.game.LetterManager
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotSame
import org.junit.Test

class LetterManagerTest {
    private val letterManager = LetterManager()

    @Test
    fun itWillCorrectlyInitialize(){
        assertEquals(letterManager.currentLetter.get()!!, "")
    }

    @Test
    fun itWillCorrectlySetNextLetter(){
        letterManager.chooseNextLetter()
        assertNotSame(letterManager.currentLetter.get()!!, "")
    }
}