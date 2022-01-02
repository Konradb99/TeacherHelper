package com.example.Teacher.viewModel.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.Teacher.R
import com.example.Teacher.entities.Lecture
import com.example.Teacher.entities.Student
import com.example.Teacher.viewModel.GroupsHandler
import com.example.Teacher.viewModel.LectureHandler
import com.example.Teacher.viewModel.StudentHandler

class GroupStudentsListAdapter(private val students: LiveData<List<Student>>, private val viewModel: GroupsHandler): RecyclerView.Adapter<GroupStudentsListAdapter.GroupStudentsListHolder>() {
    inner class GroupStudentsListHolder(private val view: View): RecyclerView.ViewHolder(view)
    {
        var firstName = view.findViewById<TextView>(R.id.studentFirstName)
        var lastName = view.findViewById<TextView>(R.id.studentLastName)
        var studentID = view.findViewById<TextView>(R.id.addStudentID)
        val check = view.findViewById<CheckBox>(R.id.addStudentCheckBox)
        val myView = view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupStudentsListHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.one_student_add_row, parent, false)
        return GroupStudentsListHolder(view)
    }

    override fun onBindViewHolder(holder: GroupStudentsListHolder, position: Int) {
        holder.firstName.text = students.value?.get(position)?.userFirstName
        holder.lastName.text = students.value?.get(position)?.userLastName
        holder.studentID.text = students.value?.get(position)?.userID.toString()

        holder.check.setOnCheckedChangeListener{ buttonView, isChecked ->
            //Add student to temp list of selected
            if(isChecked){
                viewModel.studentsSelected.add(students.value?.get(position)?.userID!!)
            }
            else{
                var ind = viewModel.studentsSelected.indexOf(students.value?.get(position)?.userID!!)
                viewModel.studentsSelected.removeAt(ind)
            }
        }

        holder.myView.setOnClickListener(){
            //viewModel.lectureName = lectures.value?.get(position)?.className
            //viewModel.lecture = Lecture(lectures.value?.get(position)?.classID!!, lectures.value?.get(position)?.className!!, lectures.value?.get(position)?.classStartHour!!, lectures.value?.get(position)?.classEndHour!!, lectures.value?.get(position)?.classDay!!)
            //holder.myView.findNavController().navigate(R.id.action_fragment_main_menu_to_fragment_one_class2)

            //Zaznaczenie studenta do dodania
        }

    }

    override fun getItemCount()=students.value?.size?:0

}