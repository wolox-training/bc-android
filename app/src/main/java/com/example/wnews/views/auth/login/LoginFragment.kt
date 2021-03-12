package com.example.wnews.views.auth.login


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.example.wnews.R
import com.example.wnews.views.auth.signup.SignUpActivity
import com.example.wnews.databinding.FragmentLoginBinding
import com.example.wnews.models.User
import com.example.wnews.views.auth.AuthPresenter
import com.example.wnews.utils.FormatUtils
import com.example.wnews.views.auth.AuthView
import com.example.wnews.views.home.HomeActivity

class LoginFragment : Fragment(R.layout.fragment_login), AuthView {

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
                email = binding!!.editTextMail.text.toString()
                password = binding!!.editTextPassword.text.toString()
            }

            if(user.email.isEmpty()){
                binding!!.editTextMail.error = getString(R.string.login_mail_missing)
                return@setOnClickListener
            }

            if(!utils.isValidEmail(user.email)){
                binding!!.editTextMail.error = getString(R.string.login_email_error)
                return@setOnClickListener
            }

            if(user.password.isEmpty()){
                binding!!.editTextPassword.error = getString(R.string.login_password_missing)
                return@setOnClickListener
            }

            binding!!.progressBarLogin.visibility = View.VISIBLE

            statusFields(false)

            val  prefs = PreferenceManager.getDefaultSharedPreferences(context)

            AuthPresenter(prefs,this).onResponseLogIn(user)

        }

        binding!!.buttonSignUp.setOnClickListener {
            startActivity(Intent(context, SignUpActivity::class.java))
        }

        binding!!.buttonTerms.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.wolox.com.ar"))
            startActivity(browserIntent)
        }
    }

    override fun onAuthResponse(statusCode:Int) {
        binding!!.progressBarLogin.visibility = View.GONE
        statusFields(true)
        when(statusCode){

            200->startActivity(Intent(context, HomeActivity::class.java))
            401->binding!!.textviewMessageError.text = getString(R.string.login_fail_login)
            0  ->binding!!.textviewMessageError.text = getString(R.string.server_error)

        }
    }

    private fun statusFields(status : Boolean){
        binding!!.editTextMail.isEnabled = status
        binding!!.editTextPassword.isEnabled = status
        binding!!.buttonSignUp.isEnabled = status
        binding!!.buttonLogIn.isEnabled = status
        binding!!.buttonTerms.isEnabled = status

    }


}