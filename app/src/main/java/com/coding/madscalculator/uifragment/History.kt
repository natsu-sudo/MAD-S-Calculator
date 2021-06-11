package com.coding.madscalculator.uifragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coding.madscalculator.databinding.FragmentHistoryBinding
import com.coding.madscalculator.pojo.CalculatorHistory
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import kotlinx.coroutines.launch
import kotlin.math.log

private const val TAG = "History"
class History : Fragment() {
    private var _binding: FragmentHistoryBinding?=null
    private val binding get() =_binding!!
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var adapterFireBase:FireBaseAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth= FirebaseAuth.getInstance()
        firebaseDatabase= FirebaseDatabase.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.historyRecyclerView.apply {
            layoutManager=LinearLayoutManager(context)
            val query:Query=firebaseDatabase.reference.child(HISTORY_BASE_PATH)
            val options:FirebaseRecyclerOptions<CalculatorHistory> =
                FirebaseRecyclerOptions.Builder<CalculatorHistory>()
                    .setQuery(query) {
                        Log.d(
                            TAG,
                            "setUpList: ${it.child("result").value.toString()} ${it.child("expression").value.toString()}"
                        )
                        CalculatorHistory(
                            it.child("result").value.toString(),
                            it.child("expression").value.toString()
                        )
                    }
                    .build()
            adapterFireBase=FireBaseAdapter(options)
            adapter = adapterFireBase
        }

    }

    override fun onStart() {
        super.onStart()
        adapterFireBase.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapterFireBase.stopListening()
    }

//    {
//        Log.d(TAG, "setUpList: ${it.child("result").value.toString()} ${it.child("expression").value.toString()}")
//        CalculatorHistory(it.child("result").value.toString(),
//            it.child("expression").value.toString())
//    }

}