package com.example.wnews

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.wnews.views.auth.login.LoginActivity
import com.example.wnews.views.home.HomeActivity

class RootActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        when (UserProvider.isLoggedIn()) {

            false -> startActivity(Intent(this, LoginActivity::class.java))
            true -> startActivity(Intent(this, HomeActivity::class.java))

        }
    }
}