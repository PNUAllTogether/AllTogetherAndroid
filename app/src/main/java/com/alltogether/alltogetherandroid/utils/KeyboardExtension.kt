/*
 * Created by Oh Seung-hoon on 2019. 1. 31
 */

package com.alltogether.alltogetherandroid.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

fun View.hideKeyboard(){
    (this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)
            ?.hideSoftInputFromWindow(this.windowToken, 0)
}

fun View.showKeyboard(){
    (this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)
            ?.showSoftInput(this, 0)
}

fun Fragment.setKeyboardAction(viewList:List<View>){
    viewList.forEach {
        it.setOnFocusChangeListener { v, hasFocus ->
            if(!hasFocus)
                v.hideKeyboard()
            else
                v.showKeyboard()
        }
    }
}