package com.example.wnews.utils

import android.text.TextUtils
import android.util.Patterns

class FormatUtils {

    fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target!!).matches()
    }
}