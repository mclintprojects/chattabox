package com.alansa.chattabox.game

import android.app.Application
import android.content.Context
import com.alansa.chattabox.viewmodels.ScoreViewModel

class ScoreManager {
    private val scores: MutableMap<String, Int> = mutableMapOf()
    private val name = "com.alansa.chattabox"

    fun setPlayers(playerNames: List<String>) {
        playerNames.forEach { it -> scores[it] = 0 }
    }

    fun awardPoint(playerName: String) {
        if (scores.containsKey(playerName)) scores[playerName] = scores[playerName]!! + 1
    }

    fun getPoint(playerName: String) = scores[playerName]

    fun saveScores(app: Application) {
        val editor = app.getSharedPreferences(name, Context.MODE_PRIVATE).edit()
        val scores = serializeScores()

        editor.putString("scores", scores)
        editor.apply()
    }

    fun getScores(app: Application): List<ScoreViewModel> {
        val data = deserializeScores(app)
        return data.map { (name, score) -> ScoreViewModel(name, score) }
    }

    private fun serializeScores(): String {
        val builder = StringBuilder()
        scores.forEach { entry -> builder.append("${entry.key}:${entry.value},") }
        return builder.toString()
    }

    fun deserializeScores(app: Application): Map<String, Int> {
        val prefs = app.getSharedPreferences(name, Context.MODE_PRIVATE)
        val scoresString = prefs.getString("scores", "")
        val scores = mutableMapOf<String, Int>()

        scoresString.split(",").forEach { score ->
            if (!score.isEmpty()) {
                val data = score.split(':')
                scores.put(data[0], data[1].toInt())
            }
        }

        return scores
    }
}