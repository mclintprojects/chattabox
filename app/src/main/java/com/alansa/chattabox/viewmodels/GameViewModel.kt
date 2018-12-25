package com.alansa.chattabox.viewmodels

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.alansa.chattabox.GameState

class GameViewModel : ViewModel() {
    val showReadyScreen = GameState.gameManager.showReadyScreen
    val readySecs = GameState.gameManager.readySecs
    val answerSecs = GameState.gameManager.answerSecs
    val currentPlayer = GameState.gameManager.currentPlayer
    val currentLetter = GameState.gameManager.currentLetter
    val showAnswerTimer = GameState.gameManager.showAnswerTimer
}