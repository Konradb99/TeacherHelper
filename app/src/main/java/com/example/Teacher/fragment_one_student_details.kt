package com.example.Teacher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.Teacher.entities.Lecture
import com.example.Teacher.viewModel.LectureHandler
import com.example.Teacher.viewModel.MarksHandler
import com.example.Teacher.viewModel.StudentHandler
import com.example.Teacher.viewModel.adapters.MarksAdapter
import com.example.Teacher.viewModel.viewModelFactories.LectureHandlerFactory
import com.example.Teacher.viewModel.viewModelFactories.MarksHandlerFactory
import com.example.Teacher.viewModel.viewModelFactories.StudentHandlerFactory
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*
import java.util.Locale.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_one_student_details.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_one_student_details : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one_student_details, container, false)
    }

    private lateinit var viewModelMarks: MarksHandler
    private lateinit var viewModelStudents: StudentHandler
    private lateinit var viewModelLecture: LectureHandler

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val factoryMarks = MarksHandlerFactory((requireNotNull(this.activity).application))
        val factoryStudent = StudentHandlerFactory((requireNotNull(this.activity).application))
        val factoryLecture = LectureHandlerFactory((requireNotNull(this.activity).application))
        viewModelMarks = ViewModelProvider(requireActivity(), factoryMarks).get(MarksHandler::class.java)
        viewModelStudents = ViewModelProvider(requireActivity(), factoryStudent).get(StudentHandler::class.java)
        viewModelLecture = ViewModelProvider(requireActivity(), factoryLecture).get(LectureHandler::class.java)

        view.findViewById<TextView>(R.id.idNumberStudent).text = viewModelStudents.currentStudent.userID.toString()
        view.findViewById<TextView>(R.id.nameStudent).text = viewModelStudents.currentStudent.userFirstName
        view.findViewById<TextView>(R.id.lastNameStudent).text = viewModelStudents.currentStudent.userLastName

        //Average

        var sum: Double = 0.0
        println(viewModelMarks.currentStudentMarks.value?.size)
        for(mark in viewModelMarks.currentStudentMarks.value!!){
            sum += mark.mark
        }
        val average = sum/viewModelMarks.currentStudentMarks.value!!.size
        val df = DecimalFormat("#.###", DecimalFormatSymbols(Locale.ENGLISH))
        df.roundingMode = RoundingMode.CEILING
        view.findViewById<TextView>(R.id.averageMark).text = df.format(average).toString()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_one_student_details.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_one_student_details().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}