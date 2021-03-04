package com.example.wnews.views.root

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wnews.R
import com.example.wnews.providers.LogInSignUpProvider
import com.example.wnews.views.main.MainActivity
import com.example.wnews.views.signin.SignUpActivity

class RootActivity : AppCompatActivity() , LogInSignUpProvider{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)

        when(isLoggedIn()){
            false->startActivity(Intent(this,SignUpActivity::class.java))
            true-> startActivity(Intent(this, MainActivity::class.java))

        }
    }
}