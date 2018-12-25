package com.alansa.chattabox.viewmodels

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField

class PlayerViewModel : ViewModel() {
    val name = ObservableField<String>()
}