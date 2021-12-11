package com.example.room.viewModel.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.room.R
import com.example.room.entities.Lecture
import com.example.room.viewModel.LectureHandler

class ClassesListAdapter(private val lectures: LiveData<List<Lecture>>, private val viewModel: LectureHandler): RecyclerView.Adapter<ClassesListAdapter.ClassesListHolder>() {
    inner class ClassesListHolder(private val view: View):RecyclerView.ViewHolder(view)
    {
        val textViewID = view.findViewById<TextView>(R.id.lectureID)
        val textViewName = view.findViewById<TextView>(R.id.lectureName)
        val myView = view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassesListHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.one_lecture_row, parent, false)
        return ClassesListHolder(view)
    }

    override fun onBindViewHolder(holder: ClassesListHolder, position: Int) {
        holder.textViewID.text = lectures.value?.get(position)?.classID.toString()
        holder.textViewName.text = lectures.value?.get(position)?.className
        holder.myView.setOnClickListener(){
            viewModel.lectureName = lectures.value?.get(position)?.className
            viewModel.lecture = Lecture(lectures.value?.get(position)?.classID!!, lectures.value?.get(position)?.className!!)
            holder.myView.findNavController().navigate(R.id.action_fragment_classes_to_fragment_one_class2)
        }
    }

    override fun getItemCount()=lectures.value?.size?:0

}