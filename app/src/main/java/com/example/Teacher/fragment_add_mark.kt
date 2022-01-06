package com.example.Teacher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.Teacher.entities.Marks
import com.example.Teacher.viewModel.GroupsHandler
import com.example.Teacher.viewModel.LectureHandler
import com.example.Teacher.viewModel.MarksHandler
import com.example.Teacher.viewModel.StudentHandler
import com.example.Teacher.viewModel.viewModelFactories.GroupHandlerFactory
import com.example.Teacher.viewModel.viewModelFactories.LectureHandlerFactory
import com.example.Teacher.viewModel.viewModelFactories.MarksHandlerFactory
import com.example.Teacher.viewModel.viewModelFactories.StudentHandlerFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_add_mark.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_add_mark : Fragment() {
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
        return inflater.inflate(R.layout.fragment_add_mark, container, false)
    }

    private lateinit var viewModelStudents : StudentHandler
    private lateinit var viewModelLecture: LectureHandler
    private lateinit var viewModelGroups: GroupsHandler
    private lateinit var viewModelMarks: MarksHandler

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spinnerMarkType: Spinner = view.findViewById<Spinner>(R.id.markType)
        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.marksType,
            R.layout.custom_spinner_item
        ).also{adapter->
            //Layout of dropdown menu
            adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item)
            //Attach adapter to spinner
            spinnerMarkType.adapter = adapter
        }

        val spinnerMark: Spinner = view.findViewById<Spinner>(R.id.mark)
        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.marks,
            R.layout.custom_spinner_item
        ).also{adapter->
            //Layout of dropdown menu
            adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item)
            //Attach adapter to spinner
            spinnerMark.adapter = adapter
        }

        val spinnerMarkWeight: Spinner = view.findViewById<Spinner>(R.id.markWeight)
        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.marksWeight,
            R.layout.custom_spinner_item
        ).also{adapter->
            //Layout of dropdown menu
            adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item)
            //Attach adapter to spinner
            spinnerMarkWeight.adapter = adapter
        }

        val factoryStudent = StudentHandlerFactory((requireNotNull(this.activity).application))
        val factoryLecture = LectureHandlerFactory((requireNotNull(this.activity).application))
        val factoryGroup = GroupHandlerFactory((requireNotNull(this.activity).application))
        val factoryMarks = MarksHandlerFactory((requireNotNull(this.activity).application))
        viewModelStudents = ViewModelProvider(requireActivity(), factoryStudent).get(StudentHandler::class.java)
        viewModelGroups = ViewModelProvider(requireActivity(), factoryGroup).get(GroupsHandler::class.java)
        viewModelLecture = ViewModelProvider(requireActivity(), factoryLecture).get(LectureHandler::class.java)
        viewModelMarks = ViewModelProvider(requireActivity(), factoryMarks).get(MarksHandler::class.java)

        view.findViewById<Button>(R.id.buttonAddMark).setOnClickListener(){
            println(viewModelStudents.currentStudent.userID.toString())
            println(viewModelLecture.lecture.className)

            var markSpinner: Double = spinnerMark.selectedItem.toString().toDouble()
            var markType = spinnerMarkType.selectedItem.toString()
            var markWeight: Int = spinnerMarkWeight.selectedItem.toString().toInt()
            println(markSpinner.javaClass.kotlin)
            println(markWeight.javaClass.kotlin)
            val mark = Marks(0, viewModelLecture.lecture.classID, viewModelStudents.currentStudent.userID, markType, markSpinner, markWeight)
            viewModelMarks.AddMark(mark)
            view.findNavController().navigate(R.id.action_fragment_add_mark_to_fragment_one_student)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_add_mark.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_add_mark().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}