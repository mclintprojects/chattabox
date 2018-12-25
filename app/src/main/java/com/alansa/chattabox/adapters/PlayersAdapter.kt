package com.alansa.chattabox.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alansa.chattabox.databinding.RowPlayerBinding
import com.alansa.chattabox.viewmodels.Player

class PlayersAdapter(private val players: List<Player>, private val onDelete: (Int) -> Unit) : RecyclerView.Adapter<PlayersViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersViewHolder {
        val binding = RowPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayersViewHolder(binding, onDelete)
    }

    override fun getItemCount(): Int = players.size

    override fun onBindViewHolder(holder: PlayersViewHolder, position: Int) {
        holder.bind(players[position], players.size > 2)
    }

    fun generatePlayerList() : List<String>{
        val playerList = mutableListOf<String>()
        players.forEach { player -> if (!player.name.value.isNullOrEmpty()) playerList.add(player.name.value!!) }
        return playerList
    }

}

class PlayersViewHolder(private val binding: RowPlayerBinding, private val onDelete: (Int) -> Unit): RecyclerView.ViewHolder(binding.root){
    fun bind(player: Player, showRemove: Boolean){
        binding.player = player
        binding.btnRemovePlayer.visibility = if (showRemove) View.VISIBLE else View.GONE
        binding.btnRemovePlayer.setOnClickListener { onDelete(adapterPosition) }
    }
}