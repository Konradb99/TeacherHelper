package com.example.Teacher.viewModel.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.Teacher.R
import com.example.Teacher.entities.Marks
import com.example.Teacher.entities.Student
import com.example.Teacher.viewModel.MarksHandler
import com.example.Teacher.viewModel.StudentHandler

class MarksAdapter(private val marks: LiveData<List<Marks>>, private val viewModel: MarksHandler, context: Context): RecyclerView.Adapter<MarksAdapter.MarksHolder>() {
    private val context: Context? = context

    inner class MarksHolder(private val view: View): RecyclerView.ViewHolder(view)
    {
        var mark = view.findViewById<TextView>(R.id.mark)
        var markType = view.findViewById<TextView>(R.id.markType)
        var markWeight = view.findViewById<TextView>(R.id.markWeight)
        val myView = view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarksHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.one_mark_row, parent, false)
        return MarksHolder(view)
    }

    override fun onBindViewHolder(holder: MarksAdapter.MarksHolder, position: Int) {
        holder.mark.text = marks.value?.get(position)?.mark.toString()
        holder.markType.text = marks.value?.get(position)?.markType
        val str: String = "Waga " + marks.value?.get(position)?.weight.toString()
        holder.markWeight.text = str

    }
    override fun getItemCount()=marks.value?.size?:0
}