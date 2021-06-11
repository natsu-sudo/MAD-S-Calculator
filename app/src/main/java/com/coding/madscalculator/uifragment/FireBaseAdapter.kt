package com.coding.madscalculator.uifragment

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.coding.madscalculator.R
import com.coding.madscalculator.pojo.CalculatorHistory
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class FireBaseAdapter(options: FirebaseRecyclerOptions<CalculatorHistory>) : FirebaseRecyclerAdapter<CalculatorHistory, FireBaseAdapter.ViewHolder>(options) {
    class ViewHolder(private val view: View):RecyclerView.ViewHolder(view) {
        private val expression:TextView=view.findViewById(R.id.expression_1)
        private val result:TextView=view.findViewById(R.id.result_1)
        fun onBind(model: CalculatorHistory) {
            Log.d("TAG", "onBind: "+model.expression+" "+model.result)
            expression.text= view.context.getString(R.string.expression_1_s,model.expression)
            result.text=view.context.getString(R.string.result_1_s,model.result)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.history_layout,parent,false)
        return ViewHolder((view))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: CalculatorHistory) {
        holder.onBind(model)
    }

}