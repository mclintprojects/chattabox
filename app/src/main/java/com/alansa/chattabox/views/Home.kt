package com.alansa.chattabox.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.alansa.chattabox.R
import com.alansa.chattabox.game.GameManager
import com.alansa.chattabox.game.PlayerManager
import com.alansa.chattabox.game.ScoreManager
import com.alansa.chattabox.game.TimeManager
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupGame()

        btnViewFaq.setOnClickListener {startActivity(Intent(this, HowToPlay::class.java)) }
        btnNewGame.setOnClickListener { startActivity(Intent(this, AddPlayers::class.java)) }
    }

    fun setupGame(){
        val gameManager = GameManager(PlayerManager(), ScoreManager(), TimeManager())
    }
}
