package com.alansa.chattabox.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class Player : ViewModel() {
    val name = MutableLiveData<String>()
}