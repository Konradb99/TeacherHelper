package com.example.Teacher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Teacher.entities.LectureMarks
import com.example.Teacher.entities.Marks
import com.example.Teacher.viewModel.GroupsHandler
import com.example.Teacher.viewModel.LectureHandler
import com.example.Teacher.viewModel.MarksHandler
import com.example.Teacher.viewModel.StudentHandler
import com.example.Teacher.viewModel.adapters.AverageMarkAdapter
import com.example.Teacher.viewModel.adapters.ClassesListAdapter
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
 * Use the [fragment_student_marks.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_student_marks : Fragment() {
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
        return inflater.inflate(R.layout.fragment_student_marks, container, false)
    }

    private lateinit var viewModelStudents: StudentHandler
    private lateinit var viewModelLecture: LectureHandler
    private lateinit var viewModelMarks: MarksHandler
    private lateinit var viewModelGroup: GroupsHandler

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //helperdao.getLecturesForStudent

        val factoryMarks = MarksHandlerFactory((requireNotNull(this.activity).application))
        val factoryStudent = StudentHandlerFactory((requireNotNull(this.activity).application))
        val factoryLecture = LectureHandlerFactory((requireNotNull(this.activity).application))
        val factoryGroup = GroupHandlerFactory((requireNotNull(this.activity).application))
        viewModelMarks = ViewModelProvider(requireActivity(), factoryMarks).get(MarksHandler::class.java)
        viewModelStudents = ViewModelProvider(requireActivity(), factoryStudent).get(StudentHandler::class.java)
        viewModelLecture = ViewModelProvider(requireActivity(), factoryLecture).get(LectureHandler::class.java)
        viewModelGroup = ViewModelProvider(requireActivity(), factoryGroup).get(GroupsHandler::class.java)


        val averageMarkAdapter = AverageMarkAdapter(viewModelStudents.getAverages(viewModelStudents.currentStudent.userID, viewModelLecture.lecture.classID), viewModelLecture, this.requireContext())
        viewModelStudents.currentStudentLectures.observe(viewLifecycleOwner, {averageMarkAdapter.notifyDataSetChanged()})
        val layoutManager= LinearLayoutManager(view.context)

        view.findViewById<RecyclerView>(R.id.averageRecyclerView).let{
            it.adapter=averageMarkAdapter
            it.layoutManager=layoutManager
        }

        //Lista przedmiotow ->

        var LecturesAndAverages : LiveData<List<LectureMarks>>

        //Lista ocen w przedmiocie dla studenta ->



    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_student_marks.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_student_marks().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}