package com.example.Teacher.viewModel.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.Teacher.R
import com.example.Teacher.entities.Lecture
import com.example.Teacher.entities.Student
import com.example.Teacher.viewModel.LectureHandler
import com.example.Teacher.viewModel.StudentHandler
import org.w3c.dom.Text

class StudentsListAdapter(private val students: LiveData<List<Student>>, private val viewModel: StudentHandler): RecyclerView.Adapter<StudentsListAdapter.StudentsListHolder>() {
    inner class StudentsListHolder(private val view: View): RecyclerView.ViewHolder(view)
    {
        var firstName = view.findViewById<TextView>(R.id.studentFirstName)
        var lastName = view.findViewById<TextView>(R.id.studentLastName)
        var studentID = view.findViewById<TextView>(R.id.studentID)
        val myView = view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentsListHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.one_student_row, parent, false)
        return StudentsListHolder(view)
    }

    override fun onBindViewHolder(holder: StudentsListAdapter.StudentsListHolder, position: Int) {
        holder.firstName.text = students.value?.get(position)?.userFirstName
        holder.lastName.text = students.value?.get(position)?.userLastName
        holder.studentID.text = students.value?.get(position)?.userID.toString()
    }

    override fun getItemCount()=students.value?.size?:0
}