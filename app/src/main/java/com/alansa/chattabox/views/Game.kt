package com.alansa.chattabox.views

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.alansa.chattabox.GameState
import com.alansa.chattabox.R
import com.alansa.chattabox.databinding.ActivityGameBinding
import com.alansa.chattabox.viewmodels.GameViewModel

class Game : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    private lateinit var viewmodel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_game)
        viewmodel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        binding.viewmodel = viewmodel
        GameState.gameManager.startReadyCountdown()
    }
}
