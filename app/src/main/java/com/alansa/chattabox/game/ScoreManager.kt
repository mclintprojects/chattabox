package com.alansa.chattabox.game

import android.app.Application
import android.content.Context

class ScoreManager {
    private val scores: MutableMap<String, Int> = mutableMapOf()
    private val name = "com.alansa.chattabox"

    fun setPlayers(playerNames: List<String>) {
        playerNames.forEach { it -> scores[it] = 0 }
    }

    fun awardPoint(playerName: String){
        if(scores.containsKey(playerName)) scores[playerName] = scores[playerName]!! + 1
    }

    fun saveScores(app: Application) {
        val editor = app.getSharedPreferences(name, Context.MODE_PRIVATE).edit()
        val scores = serializeScores()

        editor.putString("scores", scores)
        editor.apply()
    }

    private fun serializeScores(): String {
        val builder = StringBuilder()
        scores.forEach { entry -> builder.append("${entry.key}:${entry.value},") }
        return builder.toString()
    }
}