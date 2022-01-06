package com.example.Teacher.viewModel.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
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

class StudentsDetailsAdapter(private val students: LiveData<List<Student>>, private val viewModel: StudentHandler, context: Context): RecyclerView.Adapter<StudentsDetailsAdapter.StudentsDetailsHolder>() {
    private val context: Context? = context

    inner class StudentsDetailsHolder(private val view: View): RecyclerView.ViewHolder(view)
    {
        var firstName = view.findViewById<TextView>(R.id.studentFirstName)
        var lastName = view.findViewById<TextView>(R.id.studentLastName)
        var studentID = view.findViewById<TextView>(R.id.studentID)
        val myView = view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentsDetailsHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.one_student_row, parent, false)
        return StudentsDetailsHolder(view)
    }

    override fun onBindViewHolder(holder: StudentsDetailsAdapter.StudentsDetailsHolder, position: Int) {
        holder.firstName.text = students.value?.get(position)?.userFirstName
        holder.lastName.text = students.value?.get(position)?.userLastName
        holder.studentID.text = students.value?.get(position)?.userID.toString()

        holder.myView.setOnClickListener() {
            viewModel.currentStudent = Student(students.value?.get(position)?.userID!!, students.value?.get(position)?.userFirstName!! ,students.value?.get(position)?.userLastName!!)
            holder.myView.findNavController().navigate(R.id.action_fragment_one_class2_to_fragment_one_student)
        }
    }
    override fun getItemCount()=students.value?.size?:0
}