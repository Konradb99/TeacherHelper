package com.example.Teacher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
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
 * Use the [fragment_main_menu.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_main_menu : Fragment() {
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
        return inflater.inflate(R.layout.fragment_main_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var fr = parentFragmentManager?.beginTransaction()
        fr?.add(R.id.frame_classes_students, fragment_lectures())
        fr?.commit()

        view.findViewById<TextView>(R.id.main_menu_lectures).setOnClickListener{
            var fr = parentFragmentManager?.beginTransaction()
            fr?.replace(R.id.frame_classes_students, fragment_lectures())
            fr?.commit()
        }
        view.findViewById<TextView>(R.id.main_menu_students).setOnClickListener{
            var fr = parentFragmentManager?.beginTransaction()
            fr?.replace(R.id.frame_classes_students, fragment_students())
            fr?.commit()
        }

        view.findViewById<ImageView>(R.id.settingIcon).setOnClickListener(){
            view.findNavController().navigate(R.id.action_fragment_main_menu_to_fragment_settings)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_main_menu.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_main_menu().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}