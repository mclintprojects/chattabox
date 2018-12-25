package com.alansa.chattabox.adapters

import android.databinding.BindingAdapter
import android.view.ViewGroup

@BindingAdapter("android:layout_marginTop")
fun ViewGroup.setMarginTopValue(marginValue: Float) =
        (layoutParams as ViewGroup.MarginLayoutParams).apply { topMargin = marginValue.toInt() }

@BindingAdapter("android:layout_marginBottom")
fun ViewGroup.setMarginBottomValue(marginValue: Float) =
        (layoutParams as ViewGroup.MarginLayoutParams).apply { bottomMargin = marginValue.toInt() }

@BindingAdapter("android:layout_marginStart")
fun ViewGroup.setMarginStartValue(marginValue: Float) =
        (layoutParams as ViewGroup.MarginLayoutParams).apply { leftMargin = marginValue.toInt() }

@BindingAdapter("android:layout_marginEnd")
fun ViewGroup.setMarginEndValue(marginValue: Float) =
        (layoutParams as ViewGroup.MarginLayoutParams).apply { rightMargin = marginValue.toInt() }