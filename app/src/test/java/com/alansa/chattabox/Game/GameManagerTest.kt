package com.alansa.chattabox.Game

import com.alansa.chattabox.game.*
import com.nhaarman.mockitokotlin2.mock
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class GameManagerTest {
    private lateinit var gameManager: GameManager
    private val timeManager: TimeManager = mock()
    private lateinit var playerManager: PlayerManager
    private val letterManager: LetterManager = mock()
    private val audioManager: AudioManager = mock()

    @Before
    fun setup() {
        playerManager = Mockito.spy(PlayerManager(ScoreManagerTest()))
        gameManager = GameManager(mock(), playerManager, timeManager, letterManager, audioManager)
    }

    @Test
    fun itWillCorrectlyInitialize() {
        assertEquals(gameManager.showAnswerTimer.get()!!, true)
        assertEquals(gameManager.showAnswerTimer.get()!!, true)
    }
}