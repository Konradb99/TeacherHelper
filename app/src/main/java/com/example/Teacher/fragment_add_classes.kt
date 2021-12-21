package com.example.Teacher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.Teacher.entities.Lecture
import com.example.Teacher.viewModel.viewModelFactories.LectureHandlerFactory
import com.example.Teacher.viewModel.LectureHandler
import android.app.TimePickerDialog
import org.w3c.dom.Text
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_add_classes.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_add_classes : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var viewModelAdd: LectureHandler
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
        return inflater.inflate(R.layout.fragment_add_classes, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //DayOfWeek picker

        val spinner: Spinner = view.findViewById<Spinner>(R.id.dayOfWeekLecutre)
        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.daysOfWeek,
            R.layout.custom_spinner_item
        ).also{adapter->
            //Layout of dropdown menu
            adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item)
            //Attach adapter to spinner
            spinner.adapter = adapter
        }

        //TimePickerDialog Creation
        val myTimePickerStart: TimePickerDialog
        val currentTimeStart = Calendar.getInstance()
        val selectedHourStart = currentTimeStart.get(Calendar.HOUR_OF_DAY)
        val selectedMinuteStart = currentTimeStart.get(Calendar.MINUTE)
        myTimePickerStart = TimePickerDialog(this.requireContext(), object: TimePickerDialog.OnTimeSetListener{
            override fun onTimeSet(myView: TimePicker, hourOfDay: Int, minute: Int) {
                val myString = String.format("%02d : %02d", hourOfDay, minute)
                view.findViewById<TextView>(R.id.startHourLecutre).text = myString
            }
        }, selectedHourStart, selectedMinuteStart, true)
        view.findViewById<TextView>(R.id.startHourLecutre).setOnClickListener {
            myTimePickerStart.show()
        }

        val myTimePickerEnd: TimePickerDialog
        val currentTimeEnd = Calendar.getInstance()
        val selectedHourEnd = currentTimeEnd.get(Calendar.HOUR_OF_DAY)
        val selectedMinuteEnd = currentTimeEnd.get(Calendar.MINUTE)
        myTimePickerEnd = TimePickerDialog(this.requireContext(), object: TimePickerDialog.OnTimeSetListener{
            override fun onTimeSet(myView: TimePicker, hourOfDay: Int, minute: Int) {
                val myString = String.format("%02d : %02d", hourOfDay, minute)
                view.findViewById<TextView>(R.id.endHourLecutre).text = myString
            }
        }, selectedHourEnd, selectedMinuteEnd, true)
        view.findViewById<TextView>(R.id.endHourLecutre).setOnClickListener {
            myTimePickerEnd.show()
        }


        //ViewModel
        val factoryAdd = LectureHandlerFactory((requireNotNull(this.activity).application))
        viewModelAdd = ViewModelProvider(requireActivity(), factoryAdd).get(LectureHandler::class.java)
        view.findViewById<Button>(R.id.buttonAddLecture).apply{
            setOnClickListener{
                val lecture= Lecture(0,
                    view.findViewById<EditText>(R.id.lectureNameAdd).text.toString(),
                    view.findViewById<TextView>(R.id.startHourLecutre).text.toString(),
                    view.findViewById<TextView>(R.id.endHourLecutre).text.toString(),
                    spinner.selectedItem.toString()
                )
                viewModelAdd.AddClass(lecture)
                view.findNavController().navigate(R.id.action_fragment_add_classes_to_fragment_main_menu2)
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
         * @return A new instance of fragment fragment_add_classes.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_add_classes().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}