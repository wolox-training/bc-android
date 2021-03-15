package com.example.wnews.views.auth.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.wnews.R

class LoginActivity : AppCompatActivity(R.layout.activity_login) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<LoginFragment>(R.id.fragment_container_login_view)
            }
        }
    }
}