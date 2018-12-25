package com.alansa.chattabox.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.alansa.chattabox.GameState
import com.alansa.chattabox.R
import com.alansa.chattabox.adapters.ScoresAdapter
import kotlinx.android.synthetic.main.activity_score_sheet.*

class ScoreSheet : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_sheet)
        setupScoresList()
    }

    private fun setupScoresList() {
        val scores = GameState.gameManager.getScores().sortedByDescending { it -> it.points.get()!!  }
        scoreRecyclerView.layoutManager = LinearLayoutManager(this)
        scoreRecyclerView.adapter = ScoresAdapter(scores)
    }
}
