package com.alansa.chattabox.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.alansa.chattabox.R
import com.alansa.chattabox.adapters.PlayersAdapter
import com.alansa.chattabox.viewmodels.Player
import kotlinx.android.synthetic.main.activity_add_players.*

class AddPlayers : AppCompatActivity() {

    private val players = mutableListOf<Player>(Player(), Player())
    private lateinit var adapter: PlayersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_players)

        setupPlayersList()
        btnAddPlayer.setOnClickListener {
            players.add(Player())
            adapter.notifyDataSetChanged()
        }
        btnStartGame.setOnClickListener {
            val playerList = adapter.generatePlayerList()
            if (playerList.size < 2) Toast.makeText(this, "You need at least 2 players to start the game.", Toast.LENGTH_LONG).show()
        }
    }

    private fun setupPlayersList() {
        players[0].name.value = "You"
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
