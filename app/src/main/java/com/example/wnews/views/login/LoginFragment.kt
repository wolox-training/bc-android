package com.example.wnews.views.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.wnews.views.main.MainActivity
import com.example.wnews.R
import com.example.wnews.views.signin.SignUpActivity
import com.example.wnews.databinding.FragmentLoginBinding
import com.example.wnews.models.User
import com.example.wnews.utils.FormatUtils

class LoginFragment : Fragment(R.layout.fragment_login) {

    private var binding: FragmentLoginBinding? = null
    private val user = User()
    private val utils = FormatUtils()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)

        setListener()

    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setListener(){

        binding!!.buttonLogIn.setOnClickListener{
            binding!!.textviewMessageError.text = ""

            user.apply {
                emailUser = binding!!.editTextMail.text.toString()
                passwordUser = binding!!.editTextPassword.text.toString()
            }

            if(user.emailUser.isEmpty()){
                binding!!.editTextMail.error = getString(R.string.mail_missing)
                return@setOnClickListener
            }

            if(!utils.isValidEmail(user.emailUser)){
                binding!!.editTextMail.error = getString(R.string.email_error)
                return@setOnClickListener
            }

            if(user.passwordUser.isEmpty()){
                binding!!.editTextPassword.error = getString(R.string.password_missing)
                return@setOnClickListener
            }


            if(user.emailUser == "training@wolox.com.ar" && user.passwordUser == "1234"){

                startActivity(Intent(context, MainActivity::class.java))

            }else{

                binding!!.textviewMessageError.text = getString(R.string.fail_login)
                return@setOnClickListener

            }

        }

        binding!!.buttonSignUp.setOnClickListener {
            startActivity(Intent(context, SignUpActivity::class.java))
        }

        binding!!.buttonTerms.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.wolox.com.ar"))
            startActivity(browserIntent)
        }
    }

}