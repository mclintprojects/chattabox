package com.alansa.chattabox.views

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.alansa.chattabox.GameState
import com.alansa.chattabox.R
import com.alansa.chattabox.adapters.PlayersAdapter
import com.alansa.chattabox.viewmodels.PlayerViewModel
import kotlinx.android.synthetic.main.activity_add_players.*

class AddPlayers : AppCompatActivity() {

    private val players = mutableListOf<PlayerViewModel>(PlayerViewModel(), PlayerViewModel())
    private lateinit var adapter: PlayersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_players)

        setupPlayersList()
        btnAddPlayer.setOnClickListener {
            players.add(PlayerViewModel())
            adapter.notifyDataSetChanged()
        }
        btnStartGame.setOnClickListener {
            val playerList = adapter.generatePlayerList()
            if (playerList.size < 2) Toast.makeText(this, "You need at least 2 players to start the game.", Toast.LENGTH_LONG).show()
            else {
                GameState.gameManager.setPlayers(playerList)
                startActivity(Intent(this, Game::class.java))
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }

    private fun setupPlayersList() {
        players[0].name.set("Clinton")
        players[1].name.set("Odette")
        adapter = PlayersAdapter(players) { pos ->
            if (players.size > 2) {
                players.removeAt(pos)
                adapter.notifyDataSetChanged()
            }
        }
        playersRecyclerView.layoutManager = LinearLayoutManager(this)
        playersRecyclerView.adapter = adapter
        playersRecyclerView.itemAnimator = DefaultItemAnimator()
    }
}
