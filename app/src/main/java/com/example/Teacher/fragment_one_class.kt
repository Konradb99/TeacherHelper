package com.example.Teacher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Teacher.viewModel.LectureHandler
import com.example.Teacher.viewModel.StudentHandler
import com.example.Teacher.viewModel.adapters.StudentsListAdapter
import com.example.Teacher.viewModel.viewModelFactories.LectureHandlerFactory
import com.example.Teacher.viewModel.viewModelFactories.StudentHandlerFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_one_class.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_one_class : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var viewModelStudents: StudentHandler
    private lateinit var viewModelLecture: LectureHandler
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
        return inflater.inflate(R.layout.fragment_one_class, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val factoryStudent = StudentHandlerFactory((requireNotNull(this.activity).application))
        val factoryLecture = LectureHandlerFactory((requireNotNull(this.activity).application))
        viewModelStudents = ViewModelProvider(requireActivity(), factoryStudent).get(StudentHandler::class.java)
        viewModelLecture = ViewModelProvider(requireActivity(), factoryLecture).get(LectureHandler::class.java)
        view.findViewById<TextView>(R.id.lectureNameSelected).text = viewModelLecture.lectureName
        view.findViewById<Button>(R.id.buttonAddStudent).apply{
            setOnClickListener{
                view.findNavController().navigate(R.id.action_fragment_one_class2_to_fragment_add_student2)
            }
        }
        view.findViewById<Button>(R.id.buttonRemoveLecture).apply{
            setOnClickListener {
                viewModelLecture.deleteLecture(viewModelLecture.lecture)
                view.findNavController().navigate(R.id.action_fragment_one_class2_to_fragment_classes)
            }
        }
        val studentsAdapter = StudentsListAdapter(viewModelStudents.students, viewModelStudents)
        viewModelStudents.students.observe(viewLifecycleOwner, {studentsAdapter.notifyDataSetChanged()})
        val layoutManager= LinearLayoutManager(view.context)
        view.findViewById<RecyclerView>(R.id.studentsRecyclerView).let{
            it.adapter = studentsAdapter
            it.layoutManager=layoutManager
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_one_class.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_one_class().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}