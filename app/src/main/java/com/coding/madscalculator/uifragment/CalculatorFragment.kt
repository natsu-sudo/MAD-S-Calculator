package com.coding.madscalculator.uifragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.coding.madscalculator.R
import com.coding.madscalculator.databinding.FragmentCalculatorBinding
import com.coding.madscalculator.pojo.CalculatorHistory
import com.coding.madscalculator.viewmodel.CalculatorViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class CalculatorFragment : Fragment() {
    private var _binding: FragmentCalculatorBinding?=null
    private val binding get() =_binding!!
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var calculatorViewModel: CalculatorViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        calculatorViewModel=activity?.run {
            ViewModelProvider(this)[CalculatorViewModel::class.java]
        }!!
        auth= FirebaseAuth.getInstance()
        firebaseDatabase= FirebaseDatabase.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentCalculatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNavigationListener()
        setMenuListener()
        setListener()
        calculatorViewModel.exp.observe(viewLifecycleOwner, {
            binding.expression.text = it
        })

        calculatorViewModel.answer.observe(viewLifecycleOwner, {
            binding.result.text=it
        })

    }



    private fun setMenuListener() {
        binding.calculatorTopAppBar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.history ->{
                    findNavController().navigate(CalculatorFragmentDirections.actionCalculatorToHistory())
                    true
                }
                R.id.sign_out ->{
                    signOutFromAccount()
                    true
                }
                else -> true
            }
        }
    }

    private fun sendHistoryToFireBase(){
        val expression=binding.expression.text.toString()
        val result=binding.result.text.toString()
        if (expression.isBlank() || result.isBlank()){
            Toast.makeText(context,getString(R.string.expression_or_result),Toast.LENGTH_SHORT).show()
        }else{
            val reference=firebaseDatabase.getReference(HISTORY_BASE_PATH).push()
            val calculationHistory=CalculatorHistory(result,expression)
            reference.setValue(calculationHistory).addOnSuccessListener {

            }.addOnFailureListener {ex:Exception->
                Toast.makeText(requireActivity(),getString(R.string.failed,ex.toString()),Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun signOutFromAccount() {
        val auth=FirebaseAuth.getInstance()
        auth.signOut()
        auth.addAuthStateListener {
            if (auth.currentUser==null){
                //listener is called multiple time so check if we are in correct fragment
                val currId= findNavController().currentDestination?.id
                //if we are in CalculatorFragment Fragment then only we should Logout
                if (currId== R.id.calculator){
                    findNavController().navigate(CalculatorFragmentDirections.actionCalculatorToLogin())
                }
            }
        }
    }

    private fun setNavigationListener() {
        binding.calculatorTopAppBar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun setListener() {
        binding.n0.setOnClickListener { clickedN(0) }
        binding.n1.setOnClickListener { clickedN(1) }
        binding.n2.setOnClickListener { clickedN(2) }
        binding.n3.setOnClickListener { clickedN(3) }
        binding.n4.setOnClickListener { clickedN(4) }
        binding.n5.setOnClickListener { clickedN(5) }
        binding.n6.setOnClickListener { clickedN(6) }
        binding.n7.setOnClickListener { clickedN(7) }
        binding.n8.setOnClickListener { clickedN(8) }
        binding.n9.setOnClickListener { clickedN(9) }
        binding.dot.setOnClickListener { clickedN(-1) }

        binding.plus.setOnClickListener { clickedO('+') }
        binding.minus.setOnClickListener { clickedO('-') }
        binding.divide.setOnClickListener { clickedO('/') }
        binding.multiply.setOnClickListener { clickedO('*') }

        binding.delete.setOnClickListener {
            clickedA(it) }
        binding.clear.setOnClickListener {
            sendHistoryToFireBase()
            clickedA(it) }
        binding.equal.setOnClickListener {
            sendHistoryToFireBase()
            clickedA(it) }
    }

    private fun clickedO(o: Char) {
        calculatorViewModel.addOperator(o)
        binding.scrollView.postDelayed({
            kotlin.run {
                binding.scrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT)
            }
        }, 100L)
    }

    private fun clickedA(it: View) {
        when (it.id) {
            R.id.delete -> calculatorViewModel.delete()
            R.id.equal -> calculatorViewModel.equate()
            R.id.clear -> calculatorViewModel.reset()
        }
        binding.scrollView.postDelayed({
            kotlin.run {
                binding.scrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT)
            }
        }, 100L)
    }

    private fun clickedN(n: Int) {
        if (n != -1)
            calculatorViewModel.addNumber(n.toString())
        else
            calculatorViewModel.addNumber(".")
        binding.scrollView.postDelayed({
            kotlin.run {
                binding.scrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT)
            }
        }, 100L)
    }


}