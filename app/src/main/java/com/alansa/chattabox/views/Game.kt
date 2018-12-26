package com.alansa.chattabox.views

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alansa.chattabox.GameState
import com.alansa.chattabox.R
import com.alansa.chattabox.databinding.ActivityGameBinding
import com.alansa.chattabox.util.Timer
import com.alansa.chattabox.viewmodels.GameViewModel
import kotlinx.android.synthetic.main.activity_game.*

class Game : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    private lateinit var viewmodel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_game)
        viewmodel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        binding.viewmodel = viewmodel

        GameState.gameManager.startReadyCountdown()
        GameState.gameManager.setOnePlayerLeftListener { finishGame() }

        btnFailedToAnswer.setOnClickListener { GameState.gameManager.playNextTurn() }
        btnNextTurn.setOnClickListener { GameState.gameManager.playNextTurn(true) }
        btnFinishGame.setOnClickListener { finishGame() }
    }

    private fun finishGame() {
        GameState.gameManager.finishGame()
        startActivity(Intent(this, ScoreSheet::class.java))

        val timer = Timer(700)
        timer.setOnTimeElapsed {
            GameState.clear()
            finish()
        }
        timer.start()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val timer = Timer(700)
        timer.setOnTimeElapsed { GameState.clear() }
        timer.start()
    }
}
