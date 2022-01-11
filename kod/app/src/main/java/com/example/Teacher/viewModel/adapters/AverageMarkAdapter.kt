package com.example.Teacher.viewModel.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.Teacher.R
import com.example.Teacher.entities.Averages
import com.example.Teacher.entities.Lecture
import com.example.Teacher.entities.LectureMarks
import com.example.Teacher.viewModel.LectureHandler
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class AverageMarkAdapter(private val lectures: LiveData<List<Averages>>, private val viewModel: LectureHandler, context: Context): RecyclerView.Adapter<AverageMarkAdapter.AverageMarkHolder>() {

    private val context: Context? = context

    inner class AverageMarkHolder(private val view: View): RecyclerView.ViewHolder(view)
    {

        val textViewName = view.findViewById<TextView>(R.id.lectureName)
        val textViewAverage = view.findViewById<TextView>(R.id.lectureAverage)
        val myView = view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AverageMarkHolder {
        val view=
            LayoutInflater.from(parent.context).inflate(R.layout.one_lecture_average_row, parent, false)
        return AverageMarkHolder(view)
    }
    override fun onBindViewHolder(holder: AverageMarkAdapter.AverageMarkHolder, position: Int) {
        holder.textViewName.text = lectures.value?.get(position)?.lectureName
        holder.textViewAverage.text = lectures.value?.get(position)?.avg!!.toString()
    }

    override fun getItemCount()=lectures.value?.size?:0

}