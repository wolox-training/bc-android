package com.example.wnews.views.auth.login


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.example.wnews.R
import com.example.wnews.databinding.FragmentLoginBinding
import com.example.wnews.models.User
import com.example.wnews.views.auth.AuthPresenter
import com.example.wnews.views.auth.AuthView
import com.example.wnews.views.auth.signup.SignUpActivity
import com.example.wnews.views.home.HomeActivity

class LoginFragment : Fragment(R.layout.fragment_login), AuthView {

    private var binding: FragmentLoginBinding? = null
    lateinit var authPresenter: AuthPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)
        initPresenter()
        setListener()

    }

    private fun initPresenter() {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        authPresenter = AuthPresenter(sharedPreferences, this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setListener() {

        binding!!.buttonLogIn.setOnClickListener {
            binding!!.textviewMessageError.text = ""


            val user = User().apply {
                email = binding!!.editTextMail.text.toString()
                password = binding!!.editTextPassword.text.toString()
            }

            if (authPresenter.isUserMailEmpty(user.email)) {
                binding!!.editTextMail.error = getString(R.string.login_mail_missing)
                return@setOnClickListener
            }

            if (!authPresenter.validateUserMail(user.email)) {
                binding!!.editTextMail.error = getString(R.string.login_email_error)
                return@setOnClickListener
            }

            if (authPresenter.isUserPasswordEmpty(user.password)) {
                binding!!.editTextPassword.error = getString(R.string.login_password_missing)
                return@setOnClickListener
            }

            binding!!.progressBarLogin.visibility = View.VISIBLE

            statusFields(false)

            authPresenter.onResponseLogIn(user)

        }

        binding!!.buttonSignUp.setOnClickListener {
            startActivity(Intent(context, SignUpActivity::class.java))
        }

        binding!!.buttonTerms.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.wolox.com.ar"))
            startActivity(browserIntent)
        }
    }

    private fun statusFields(status: Boolean) {
        binding!!.editTextMail.isEnabled = status
        binding!!.editTextPassword.isEnabled = status
        binding!!.buttonSignUp.isEnabled = status
        binding!!.buttonLogIn.isEnabled = status
        binding!!.buttonTerms.isEnabled = status
    }

    override fun onResponseSuccess() {
        startActivity(Intent(context, HomeActivity::class.java))
    }

    override fun onResponseFailure(message: String) {
        binding!!.progressBarLogin.visibility = View.GONE
        statusFields(true)
        binding!!.textviewMessageError.text = message
    }

    override fun onRequestFailure() {
        binding!!.progressBarLogin.visibility = View.GONE
        statusFields(true)
        binding!!.textviewMessageError.text = getString(R.string.server_error)
    }


}