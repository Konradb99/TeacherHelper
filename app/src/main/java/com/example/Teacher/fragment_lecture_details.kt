package com.example.Teacher

import android.app.AlertDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.Teacher.viewModel.LectureHandler
import com.example.Teacher.viewModel.viewModelFactories.LectureHandlerFactory
import java.util.*
import android.content.DialogInterface
import android.text.Html
import android.widget.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_lecture_details.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_lecture_details : Fragment() {
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
        return inflater.inflate(R.layout.fragment_lecture_details, container, false)
    }

    private lateinit var viewModelLecture: LectureHandler

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factoryLecture = LectureHandlerFactory((requireNotNull(this.activity).application))
        viewModelLecture = ViewModelProvider(requireActivity(), factoryLecture).get(LectureHandler::class.java)

        view.findViewById<TextView>(R.id.startHourDetails).text = viewModelLecture.lecture.classStartHour
        view.findViewById<TextView>(R.id.endHourDetails).text = viewModelLecture.lecture.classEndHour
        view.findViewById<TextView>(R.id.dayOfWeekDetails).text = viewModelLecture.lecture.classDay

        view.findViewById<Button>(R.id.buttonRemoveLecture).apply{
            setOnClickListener {
                viewModelLecture.DeleteLecture(viewModelLecture.lecture)
                view.findNavController().navigate(R.id.action_fragment_one_class2_to_fragment_main_menu)
            }
        }

        //TimePickerDialog Editor
        val myTimePickerStart: TimePickerDialog
        val currentTimeStart = Calendar.getInstance()
        val selectedHourStart = currentTimeStart.get(Calendar.HOUR_OF_DAY)
        val selectedMinuteStart = currentTimeStart.get(Calendar.MINUTE)
        myTimePickerStart = TimePickerDialog(this.requireContext(), object: TimePickerDialog.OnTimeSetListener{
            override fun onTimeSet(myView: TimePicker, hourOfDay: Int, minute: Int) {
                val myString = String.format("%02d:%02d", hourOfDay, minute)
                //Update current lecture start hour
                //Check if hour is correct to ending hour
                if(!viewModelLecture.check_hour_start(myString, viewModelLecture.lecture.classEndHour)){
                    viewModelLecture.lecture.classStartHour = myString
                    viewModelLecture.UpdateLecture(viewModelLecture.lecture)
                    view.findViewById<TextView>(R.id.startHourDetails).text = myString
                }
            }
        }, selectedHourStart, selectedMinuteStart, true)
        view.findViewById<TextView>(R.id.startHourDetails).setOnLongClickListener{
            myTimePickerStart.show()
            true
        }

        val myTimePickerEnd: TimePickerDialog
        val currentTimeEnd = Calendar.getInstance()
        val selectedHourEnd = currentTimeEnd.get(Calendar.HOUR_OF_DAY)
        val selectedMinuteEnd = currentTimeEnd.get(Calendar.MINUTE)
        myTimePickerEnd = TimePickerDialog(this.requireContext(), object: TimePickerDialog.OnTimeSetListener{
            override fun onTimeSet(myView: TimePicker, hourOfDay: Int, minute: Int) {
                val myString = String.format("%02d:%02d", hourOfDay, minute)
                if (!viewModelLecture.check_hour_end(myString, viewModelLecture.lecture.classStartHour)){
                    viewModelLecture.lecture.classEndHour = myString
                    viewModelLecture.UpdateLecture(viewModelLecture.lecture)
                    view.findViewById<TextView>(R.id.endHourDetails).text = myString
                }
            }
        }, selectedHourEnd, selectedMinuteEnd, true)
        view.findViewById<TextView>(R.id.endHourDetails).setOnLongClickListener {
            myTimePickerEnd.show()
            true
        }




        //Edit day of week
        view.findViewById<TextView>(R.id.dayOfWeekDetails).setOnLongClickListener {
            val b: AlertDialog.Builder = AlertDialog.Builder(this.requireContext(), R.style.AlertDialogTheme)
            val myView: View = getLayoutInflater().inflate(R.layout.dialog_spinner, null)
            val mySpinner: Spinner = myView.findViewById<Spinner>(R.id.spinnerEditor)
            ArrayAdapter.createFromResource(
                this.requireContext(),
                R.array.daysOfWeek,
                R.layout.custom_spinner_item
            ).also{adapter->
                //Layout of dropdown menu
                adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item)
                //Attach adapter to spinner
                mySpinner.adapter = adapter
            }

            b.setPositiveButton("Ok", object: DialogInterface.OnClickListener{
                override fun onClick(dialogInterface: DialogInterface, i:Int){
                    val myStr: String = mySpinner.selectedItem.toString()
                    viewModelLecture.lecture.classDay = myStr
                    view.findViewById<TextView>(R.id.dayOfWeekDetails).text = myStr
                    viewModelLecture.UpdateLecture(viewModelLecture.lecture)
                    dialogInterface.dismiss()
                }
            })

            b.setNegativeButton("Anuluj", object: DialogInterface.OnClickListener{
                override fun onClick(dialogInterface: DialogInterface, i:Int){
                    dialogInterface.dismiss()
                }
            })

            b.setView(myView)
            val dialog: AlertDialog = b.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(R.drawable.button)
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(resources.getColor(R.color.white))
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(resources.getColor(R.color.white))
            true
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_lecture_details.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_lecture_details().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}