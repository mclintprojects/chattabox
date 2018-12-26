package com.alansa.chattabox.views

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.widget.Toast
import com.alansa.chattabox.R
import kotlinx.android.synthetic.main.dialog_game_setup.view.*

class GameSetupDialog(private val ctx: Context) : DialogFragment() {
    private lateinit var listener: (Int) -> Unit

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(ctx).inflate(R.layout.dialog_game_setup, null)
        view.btnStart.setOnClickListener {
            if (!view.tbTurns.text.isNullOrEmpty()) {
                val turnsCount = view.tbTurns.text.toString().toInt()
                if (turnsCount > 0) {
                    listener(view.tbTurns.text.toString().toInt())
                    dismiss()
                } else Toast.makeText(activity, "Turns cannot be less than zero.", Toast.LENGTH_LONG).show()
            } else Toast.makeText(activity, "Turns cannot be empty.", Toast.LENGTH_LONG).show()
        }

        return AlertDialog.Builder(ctx)
                .setView(view)
                .create()
    }


    fun setOnStartListener(listener: (Int) -> Unit) {
        this.listener = listener
    }
}