package com.example.Teacher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.Teacher.viewModel.StudentHandler
import com.example.Teacher.viewModel.viewModelFactories.StudentHandlerFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_student_details.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_student_details : Fragment() {
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
        return inflater.inflate(R.layout.fragment_student_details, container, false)
    }

    private lateinit var viewModelStudents: StudentHandler

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var fr = parentFragmentManager?.beginTransaction()
        fr?.add(R.id.frameLayoutLecture, fragment_one_stud_details())
        fr?.commit()



        view.findViewById<TextView>(R.id.details_lecture).setOnClickListener{
            var fr = parentFragmentManager?.beginTransaction()
            fr?.replace(R.id.frameLayoutLecture, fragment_one_stud_details())
            fr?.commit()
        }
        view.findViewById<TextView>(R.id.students_lecture).setOnClickListener{
            var fr = parentFragmentManager?.beginTransaction()
            fr?.replace(R.id.frameLayoutLecture, fragment_student_marks())
            fr?.commit()
        }

        val factoryStudent = StudentHandlerFactory((requireNotNull(this.activity).application))
        viewModelStudents = ViewModelProvider(requireActivity(), factoryStudent).get(StudentHandler::class.java)

        var myStr = viewModelStudents.currentStudent.userFirstName + " " + viewModelStudents.currentStudent.userLastName

        view.findViewById<TextView>(R.id.studentSelected).text = myStr

        view.findViewById<ImageView>(R.id.studentDetailsLogo).setOnClickListener(){
            view.findNavController().navigate(R.id.action_fragment_student_details_to_fragment_main_menu)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_student_details.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_student_details().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}