package com.example.Teacher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Teacher.database.HelperDatabase
import com.example.Teacher.entities.Student
import com.example.Teacher.viewModel.GroupsHandler
import com.example.Teacher.viewModel.StudentHandler
import com.example.Teacher.viewModel.adapters.StudentsListAdapter
import com.example.Teacher.viewModel.viewModelFactories.GroupHandlerFactory
import com.example.Teacher.viewModel.viewModelFactories.StudentHandlerFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_students_lecture.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_lecture_students : Fragment() {
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
        return inflater.inflate(R.layout.fragment_lecture_students, container, false)
    }

    private lateinit var viewModelStudents: StudentHandler
    private  lateinit var viewModelGroups: GroupsHandler

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factoryStudent = StudentHandlerFactory((requireNotNull(this.activity).application))
        val factoryGroup = GroupHandlerFactory((requireNotNull(this.activity).application))
        viewModelStudents = ViewModelProvider(requireActivity(), factoryStudent).get(StudentHandler::class.java)
        viewModelGroups = ViewModelProvider(requireActivity(), factoryGroup).get(GroupsHandler::class.java)
        val studentsAdapter = StudentsListAdapter(viewModelGroups.getStudentsInGroup(viewModelGroups.currentLecture.classID), viewModelStudents, this.requireContext())
        viewModelGroups.studentsInGroup.observe(
            viewLifecycleOwner,
            { studentsAdapter.notifyDataSetChanged() }  )
        val layoutManager = LinearLayoutManager(view.context)
        view.findViewById<RecyclerView>(R.id.studentsRecyclerViewLecture).let {
            it.adapter = studentsAdapter
            it.layoutManager = layoutManager
        }

        view.findViewById<Button>(R.id.buttonAddStudent).apply{
            setOnClickListener{
                println("-============================-")
                println(viewModelGroups.studentsSelected.size)
                viewModelGroups.studentsSelected = ArrayList<Long>()
                view.findNavController().navigate(R.id.action_fragment_one_class2_to_fragment_add_student_to_lecture)
            }
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_students_lecture.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_lecture_students().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}