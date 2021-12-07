package com.example.room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.room.entities.Lecture

class ClassesListAdapter(private val lectures: LiveData<List<Lecture>>, private val viewModel: ClassesListViewModel): RecyclerView.Adapter<ClassesListAdapter.ClassesListHolder>() {
    inner class ClassesListHolder(private val view: View):RecyclerView.ViewHolder(view)
    {
        val textViewID = view.findViewById<TextView>(R.id.lectureID)
        val textViewName = view.findViewById<TextView>(R.id.lectureName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassesListHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.one_lecture_row, parent, false)
        return ClassesListHolder(view)
    }

    override fun onBindViewHolder(holder: ClassesListHolder, position: Int) {
        holder.textViewID.text = lectures.value?.get(position)?.classID.toString()
        holder.textViewName.text = lectures.value?.get(position)?.className
    }

    override fun getItemCount()=lectures.value?.size?:0

}