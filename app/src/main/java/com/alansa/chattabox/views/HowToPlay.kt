package com.alansa.chattabox.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alansa.chattabox.R
import kotlinx.android.synthetic.main.activity_how_to_play.*

class HowToPlay : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_how_to_play)
        btnCloseActivity.setOnClickListener { finish() }
    }
}
