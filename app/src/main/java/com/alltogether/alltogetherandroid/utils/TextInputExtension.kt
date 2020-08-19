package com.alltogether.alltogetherandroid.utils

import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

fun Fragment.setTextInputErrorAction(list: List<Pair<TextInputEditText, TextInputLayout>>){
    list.forEach {
        it.first.setOnKeyListener { _, _, _ ->
            it.second.clearError()
            false
        }
        it.first.setOnTouchListener{ _, _->
            it.second.clearError()
            false
        }
    }
}

fun TextInputLayout.clearError(){
    this.error = ""
}

fun TextInputLayout.setErrorMessage(message:String){
    this.error = message
}