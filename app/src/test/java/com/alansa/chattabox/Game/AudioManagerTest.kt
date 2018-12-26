package com.alansa.chattabox.Game

import android.media.MediaPlayer
import android.speech.tts.TextToSpeech
import com.alansa.chattabox.game.AudioManager
import com.nhaarman.mockitokotlin2.mock
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.Before
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*

class AudioManagerTest {
    private val ttsMock : TextToSpeech = mock()
    private val playerMock : MediaPlayer = mock()
    private lateinit var audioManager: AudioManager
    private val ttsSpeakCaptor = ArgumentCaptor.forClass(String::class.java)

    @Before
    fun setup(){
        audioManager = AudioManager(ttsMock, playerMock)
    }

    @Test
    fun itWillCorrectlySayWord() {
        audioManager.speak("Hello")
        verify(ttsMock, times(1)).speak(ttsSpeakCaptor.capture(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyObject(), ArgumentMatchers.anyString())
        assertEquals(ttsSpeakCaptor.value, "Hello")
    }

    @Test
    fun itWillCorrectlyPlayTick(){
        audioManager.playTick()
        verify(playerMock, times(1)).start()
    }
}
