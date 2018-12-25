package com.alansa.chattabox.viewmodels

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.alansa.chattabox.GameState

class GameViewModel : ViewModel() {
    val showReadyScreen = ObservableField<Boolean>(true)
    val readySecs = GameState.gameManager.readySecs
    val readyElapsed = GameState.gameManager.readyElapsed
    val answerSecs = GameState.gameManager.answerSecs
}