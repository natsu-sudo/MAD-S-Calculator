package com.coding.madscalculator.uifragment

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.coding.madscalculator.R
import com.coding.madscalculator.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth


class RegisterFragment : Fragment() {
    private lateinit var auth:FirebaseAuth
    private var _binding:FragmentRegisterBinding?=null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth= FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginRegister.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToCalculator())
        }

        binding.register.setOnClickListener {
            registerUsers()
        }
    }

    private fun registerUsers() {
        binding.userEmailLayout.error=null
        binding.userPasswordLayout.error=null
        val email=binding.userNameEmail.text.toString()
        val pass=binding.userPassword.text.toString()
        if (validateInput(email,pass)){
            binding.progressBar.visibility=View.VISIBLE
            auth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(requireActivity()){task->
                    binding.progressBar.visibility=View.GONE
                    if (task.isSuccessful){
                        findNavController().navigate(
                            RegisterFragmentDirections.actionRegisterFragmentToLogin()
                        )
                    }else{
                        val toast= Toast.makeText(context,
                            getString(R.string.auth_fail,task.exception?.message), Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER_VERTICAL,0,0)
                        toast.show()
                    }
                }

        }
    }

    private fun validateInput(email: String, pass: String): Boolean {
        var valid=true
        if (email.isBlank()){
            binding.userEmailLayout.error=getString(R.string.please_email_add)
            valid=false
        }
        if (pass.isBlank()){
            binding.userPasswordLayout.error=getString(R.string.please_enter_password)
            valid=false
        }else if (pass.length<8){
            binding.userPasswordLayout.error=getString(R.string.password_less)
            valid=false
        }
        return valid
    }

}