package com.example.wnews.views.root

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import com.example.wnews.R
import com.example.wnews.views.auth.AuthPresenter
import com.example.wnews.views.auth.login.LoginActivity
import com.example.wnews.views.main.MainActivity

class RootActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)

        val  prefs = PreferenceManager.getDefaultSharedPreferences(this)

        val logInService = AuthPresenter(prefs,null)

        when(logInService.isLoggedIn()){

            false->startActivity(Intent(this, LoginActivity::class.java))
            true-> startActivity(Intent(this, MainActivity::class.java))

        }
    }
}