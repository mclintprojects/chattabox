package com.alansa.chattabox.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.alansa.chattabox.databinding.RowScoreBinding
import com.alansa.chattabox.viewmodels.ScoreViewModel

class ScoresAdapter(val scores: List<ScoreViewModel>) : RecyclerView.Adapter<ScoresViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoresViewHolder {
        val binding = RowScoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ScoresViewHolder(binding)
    }

    override fun getItemCount() = scores.size

    override fun onBindViewHolder(holder: ScoresViewHolder, position: Int) {
        holder.bind(scores[position], position)
    }

}

class ScoresViewHolder(val binding: RowScoreBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(score: ScoreViewModel, position: Int) {
        binding.lblPosition.text = (position + 1).toString()
        binding.score = score
    }
}