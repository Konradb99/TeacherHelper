package com.example.Teacher.viewModel.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
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

class ClassesListAdapter(private val lectures: LiveData<List<Lecture>>, private val viewModel: LectureHandler, context: Context): RecyclerView.Adapter<ClassesListAdapter.ClassesListHolder>() {

    private val context: Context? = context

    inner class ClassesListHolder(private val view: View):RecyclerView.ViewHolder(view)
    {

        val textViewName = view.findViewById<TextView>(R.id.lectureName)
        val myView = view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassesListHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.one_lecture_row, parent, false)
        return ClassesListHolder(view)
    }

    override fun onBindViewHolder(holder: ClassesListHolder, position: Int) {
        holder.textViewName.text = lectures.value?.get(position)?.className
        holder.myView.setOnClickListener(){
            viewModel.lectureName = lectures.value?.get(position)?.className    
            viewModel.lecture = Lecture(lectures.value?.get(position)?.classID!!, lectures.value?.get(position)?.className!!, lectures.value?.get(position)?.classStartHour!!, lectures.value?.get(position)?.classEndHour!!, lectures.value?.get(position)?.classDay!!)
            holder.myView.findNavController().navigate(R.id.action_fragment_main_menu_to_fragment_one_class2)
        }

        holder.myView.setOnLongClickListener()
        { v->
            val builder: AlertDialog.Builder = AlertDialog.Builder(this.context, R.style.RemoveStudentTheme)
            builder.setMessage("Usunąć przedmiot?")
            builder.setPositiveButton("Usuń", object: DialogInterface.OnClickListener{
                override fun onClick(dialogInterface: DialogInterface, i:Int){
                    viewModel.DeleteLecture(viewModel.lecture)
                    dialogInterface.dismiss()
                }
            })

            builder.setNegativeButton("Anuluj", object: DialogInterface.OnClickListener{
                override fun onClick(dialogInterface: DialogInterface, i:Int){
                    dialogInterface.dismiss()
                }
            })
            val dialog: AlertDialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(R.drawable.button)
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(context?.resources!!.getColor(R.color.white))
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(context?.resources!!.getColor(R.color.white))
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextSize(15.0F)
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextSize(15.0F)
            true
        }
    }

    override fun getItemCount()=lectures.value?.size?:0

}