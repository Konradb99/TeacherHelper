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
import com.example.Teacher.viewModel.LectureHandler
import com.example.Teacher.viewModel.adapters.ClassesListAdapter
import com.example.Teacher.viewModel.viewModelFactories.LectureHandlerFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_lectures.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_lectures : Fragment() {
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
        return inflater.inflate(R.layout.fragment_lectures, container, false)
    }


    private lateinit var viewModelList: LectureHandler


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factoryList = LectureHandlerFactory((requireNotNull(this.activity).application))
        viewModelList = ViewModelProvider(requireActivity(), factoryList).get(LectureHandler::class.java)
        val classListAdapter = ClassesListAdapter(viewModelList.lectures, viewModelList, this.requireContext())
        viewModelList.lectures.observe(viewLifecycleOwner, {classListAdapter.notifyDataSetChanged()})

        val layoutManager= LinearLayoutManager(view.context)

        view.findViewById<RecyclerView>(R.id.lecturesRecyclerView).let{
            it.adapter=classListAdapter
            it.layoutManager=layoutManager
        }

        view.findViewById<Button>(R.id.buttonAddGroup).apply{
            setOnClickListener{
                view.findNavController().navigate(R.id.action_fragment_main_menu_to_fragment_add_classes)
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
         * @return A new instance of fragment fragment_lectures.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_lectures().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}