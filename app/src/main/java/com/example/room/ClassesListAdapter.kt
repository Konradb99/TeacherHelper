package com.example.room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.room.entities.Lecture
import com.example.room.viewModels.ClassesListViewModel

class ClassesListAdapter(private val lectures: LiveData<List<Lecture>>, private val viewModel: ClassesListViewModel): RecyclerView.Adapter<ClassesListAdapter.ClassesListHolder>() {
    inner class ClassesListHolder(private val view: View):RecyclerView.ViewHolder(view)
    {
        val textViewID = view.findViewById<TextView>(R.id.lectureID)
        val textViewName = view.findViewById<TextView>(R.id.lectureName)
        val buttonRemove=view.findViewById<Button>(R.id.button_delete_user)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassesListHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.one_lecture_row, parent, false)
        return ClassesListHolder(view)
    }

    override fun onBindViewHolder(holder: ClassesListHolder, position: Int) {
        holder.textViewID.text = lectures.value?.get(position)?.classID.toString()
        holder.textViewName.text = lectures.value?.get(position)?.className

        holder.buttonRemove.setOnClickListener{
            lectures.value?.let{ existingLectures ->
                viewModel.deleteLecture(existingLectures.get(position))
            }
        }
    }

    override fun getItemCount()=lectures.value?.size?:0

}