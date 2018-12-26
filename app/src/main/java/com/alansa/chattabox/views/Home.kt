package com.alansa.chattabox.views

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alansa.chattabox.GameState
import com.alansa.chattabox.R
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        GameState.initialize(application)
        btnViewFaq.setOnClickListener { startActivity(Intent(this, HowToPlay::class.java)) }
        btnNewGame.setOnClickListener {
            val dialog = GameSetupDialog(this)
            dialog.setOnStartListener { turns ->
                GameState.gameManager.setTurns(turns)
                startActivity(Intent(this, AddPlayers::class.java))
            }
            dialog.show(supportFragmentManager, "")
        }
        btnScoreSheet.setOnClickListener { startActivity(Intent(this, ScoreSheet::class.java)) }
    }
}
