package com.alansa.chattabox.viewmodels

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField

class ScoreViewModel(name: String, score: Int) : ViewModel() {
    val name = ObservableField(name)
    val points = ObservableField(score)
}