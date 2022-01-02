package com.example.Teacher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Teacher.entities.Groups
import com.example.Teacher.viewModel.GroupsHandler
import com.example.Teacher.viewModel.LectureHandler
import com.example.Teacher.viewModel.StudentHandler
import com.example.Teacher.viewModel.adapters.ClassesListAdapter
import com.example.Teacher.viewModel.adapters.GroupStudentsListAdapter
import com.example.Teacher.viewModel.viewModelFactories.GroupHandlerFactory
import com.example.Teacher.viewModel.viewModelFactories.LectureHandlerFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_add_student_to_lecture.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_add_student_to_lecture : Fragment() {
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

    private lateinit var viewModelStudents: StudentHandler
    private  lateinit var viewModelGroups: GroupsHandler

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_student_to_lecture, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factoryList = LectureHandlerFactory((requireNotNull(this.activity).application))
        val factoryGroup = GroupHandlerFactory((requireNotNull(this.activity).application))
        viewModelStudents = ViewModelProvider(requireActivity(), factoryList).get(StudentHandler::class.java)
        viewModelGroups = ViewModelProvider(requireActivity(), factoryGroup).get(GroupsHandler::class.java)
        val groupAdapter = GroupStudentsListAdapter(viewModelStudents.students, viewModelGroups)
        viewModelStudents.students.observe(viewLifecycleOwner, {groupAdapter.notifyDataSetChanged()})


        val layoutManager= LinearLayoutManager(view.context)

        view.findViewById<RecyclerView>(R.id.groupStudentRecyclerView).let{
            it.adapter=groupAdapter
            it.layoutManager=layoutManager
        }

        view.findViewById<Button>(R.id.buttonAddStudentToGroup).setOnClickListener(){
            println("-============================-")
            println("Crrent lecture:" + viewModelGroups.currentLecture.className)
            println(viewModelGroups.studentsSelected.size)
            for (l in viewModelGroups.studentsSelected) {
                //To do:
                //Check if student exists in group
                    //Get list of students in current group -> list in viewmodel
                        // Get trough every element of list
                            // if exists -> break
                //If not -> Add
                val groupStudent = Groups(0, viewModelGroups.currentLecture.classID, l)
                println(viewModelGroups.studentsInGroup.value?.size)
                var exists = false
                if(viewModelGroups.studentsInGroup.value?.size != 0){
                    for(s in viewModelGroups.studentsInGroup.value!!){
                        if(s.userID == groupStudent.studentID && viewModelGroups.currentLecture.classID == groupStudent.classID){
                            exists = true
                            break
                        }
                        else{
                            exists = false
                        }
                    }
                }
                if(!exists){
                    viewModelGroups.AddStudent(groupStudent)
                }
            }
            view.findNavController().navigate(R.id.action_fragment_add_student_to_lecture_to_fragment_one_class2)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_add_student_to_lecture.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_add_student_to_lecture().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}