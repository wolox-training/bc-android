package com.example.wnews

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import androidx.fragment.app.Fragment
import com.example.wnews.databinding.FragmentLoginBinding
import com.example.wnews.models.User


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class LoginFragment : Fragment(R.layout.fragment_login) {
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentLoginBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentLoginBinding.bind(view)
        _binding = binding

        val user = User()


        binding.btnLogIn.setOnClickListener{
            binding.tvMessageError.text = ""

            user.apply {
                emailUser = binding.etMail.text.toString()
                pwdUser = binding.etPassword.text.toString()
            }

            if(user.emailUser.isEmpty()){
                binding.etMail.error = getString(R.string.mail_missing)
                return@setOnClickListener
            }

            if(!isValidEmail(user.emailUser)){
                binding.etMail.error = getString(R.string.email_error)
                return@setOnClickListener
            }

            if(user.pwdUser.isEmpty()){
                binding.etPassword.error = getString(R.string.password_missing)
                return@setOnClickListener
            }


            if(user.emailUser == "training@wolox.com.ar" && user.pwdUser == "1234"){
                startActivity(Intent(context, MainActivity::class.java))
            }else{
                binding.tvMessageError.text = getString(R.string.fail_login)
                return@setOnClickListener
            }

        }

        binding.btnSignUp.setOnClickListener {
            startActivity(Intent(context, SignInActivity::class.java))
        }

        binding.btnTerms.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.wolox.com.ar"))
            startActivity(browserIntent)
        }
    }

    private fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target!!).matches()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}