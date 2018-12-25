package com.alansa.chattabox.game

import android.app.Application
import android.content.Context
import java.lang.StringBuilder

class ScoreManager {
    private val scores : MutableMap<String, Int> = mutableMapOf()
    private val name = "com.alansa.chattabox"

    init{
    }

    fun setPlayers(playerNames: List<String>){
        playerNames.forEach { it -> scores[it] = 0 }
    }

    fun saveScore(app: Application){
        val editor = app.getSharedPreferences(name, Context.MODE_PRIVATE).edit()
        val score = serializeScores()

        editor.putString("score", score)
        editor.apply()
    }

    private fun serializeScores(): String{
        val builder = StringBuilder()
        scores.forEach{entry -> builder.append("${entry.key}:${entry.value},") }
        return builder.toString()
    }
}