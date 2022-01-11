package com.example.Teacher

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.Teacher.entities.Student
import com.example.Teacher.viewModel.StudentHandler
import com.example.Teacher.viewModel.viewModelFactories.StudentHandlerFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_one_stud_details.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_one_stud_details : Fragment() {
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
        return inflater.inflate(R.layout.fragment_one_stud_details, container, false)
    }

    private lateinit var viewModelStudents: StudentHandler

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factoryStudent = StudentHandlerFactory((requireNotNull(this.activity).application))
        viewModelStudents = ViewModelProvider(requireActivity(), factoryStudent).get(StudentHandler::class.java)

        view.findViewById<TextView>(R.id.idNumberStudent).text = viewModelStudents.currentStudent.userID.toString()
        view.findViewById<TextView>(R.id.nameStudent).text = viewModelStudents.currentStudent.userFirstName
        view.findViewById<TextView>(R.id.lastNameStudent).text = viewModelStudents.currentStudent.userLastName

        //Edycja studenta

        view.findViewById<TextView>(R.id.nameStudent).setOnLongClickListener {
            val b: AlertDialog.Builder = AlertDialog.Builder(this.requireContext(), R.style.AlertDialogTheme)
            val myView: View = getLayoutInflater().inflate(R.layout.dialog_edit, null)
            val newText: TextView = myView.findViewById<TextView>(R.id.nameStud)
            myView.findViewById<TextView>(R.id.nameStudText).text="Nowe imię studenta: "

            b.setPositiveButton("Ok", object: DialogInterface.OnClickListener{
                override fun onClick(dialogInterface: DialogInterface, i:Int){
                    val myStr: String = newText.text.toString()
                    viewModelStudents.currentStudent.userFirstName = myStr
                    println(myStr)
                    viewModelStudents.updateStudent(viewModelStudents.currentStudent)
                    view.findViewById<TextView>(R.id.nameStudent).text = myStr
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

        view.findViewById<TextView>(R.id.lastNameStudent).setOnLongClickListener {
            val b: AlertDialog.Builder = AlertDialog.Builder(this.requireContext(), R.style.AlertDialogTheme)
            val myView: View = getLayoutInflater().inflate(R.layout.dialog_edit, null)
            val newText: TextView = myView.findViewById<TextView>(R.id.nameStud)
            myView.findViewById<TextView>(R.id.nameStudText).text="Nowe nazwisko studenta: "

            b.setPositiveButton("Ok", object: DialogInterface.OnClickListener{
                override fun onClick(dialogInterface: DialogInterface, i:Int){
                    val myStr: String = newText.text.toString()
                    viewModelStudents.currentStudent.userLastName = myStr
                    viewModelStudents.updateStudent(viewModelStudents.currentStudent)
                    view.findViewById<TextView>(R.id.lastNameStudent).text = myStr
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
         * @return A new instance of fragment fragment_one_stud_details.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_one_stud_details().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}