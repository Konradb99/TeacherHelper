package com.example.Teacher

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.Teacher.viewModel.SettingsHandler
import com.example.Teacher.viewModel.StudentHandler
import com.example.Teacher.viewModel.viewModelFactories.SettingsHandlerFactory
import com.example.Teacher.viewModel.viewModelFactories.StudentHandlerFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_settings.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_settings : Fragment() {
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
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    private lateinit var viewModelSettings: SettingsHandler

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = SettingsHandlerFactory((requireNotNull(this.activity).application))
        viewModelSettings = ViewModelProvider(requireActivity(), factory).get(SettingsHandler::class.java)

        //Clear lectures
        view.findViewById<TextView>(R.id.removeLectures).setOnLongClickListener(){ v->
            val builder: AlertDialog.Builder = AlertDialog.Builder(this.context, R.style.RemoveStudentTheme)
            builder.setMessage("Wyczyścić listę zajęć?")
            builder.setPositiveButton("Wyczyść", object: DialogInterface.OnClickListener{
                override fun onClick(dialogInterface: DialogInterface, i:Int){
                    viewModelSettings.clearLectures()
                    dialogInterface.dismiss()
                }
            })

            builder.setNegativeButton("Anuluj", object: DialogInterface.OnClickListener{
                override fun onClick(dialogInterface: DialogInterface, i:Int){
                    dialogInterface.dismiss()
                }
            })
            val dialog: AlertDialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(R.drawable.button)
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(context?.resources!!.getColor(R.color.white))
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(context?.resources!!.getColor(R.color.white))
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextSize(15.0F)
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextSize(15.0F)
            true
        }

        //Clear students
        view.findViewById<TextView>(R.id.removeStudents).setOnLongClickListener(){ v->
            val builder: AlertDialog.Builder = AlertDialog.Builder(this.context, R.style.RemoveStudentTheme)
            builder.setMessage("Wyczyścić listę studentów?")
            builder.setPositiveButton("Wyczyść", object: DialogInterface.OnClickListener{
                override fun onClick(dialogInterface: DialogInterface, i:Int){
                    viewModelSettings.clearStudents()
                    dialogInterface.dismiss()
                }
            })

            builder.setNegativeButton("Anuluj", object: DialogInterface.OnClickListener{
                override fun onClick(dialogInterface: DialogInterface, i:Int){
                    dialogInterface.dismiss()
                }
            })
            val dialog: AlertDialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(R.drawable.button)
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(context?.resources!!.getColor(R.color.white))
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(context?.resources!!.getColor(R.color.white))
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextSize(15.0F)
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextSize(15.0F)
            true
        }

        //Clear marks
        view.findViewById<TextView>(R.id.removeMarks).setOnLongClickListener(){ v->
            val builder: AlertDialog.Builder = AlertDialog.Builder(this.context, R.style.RemoveStudentTheme)
            builder.setMessage("Wyczyścić listę ocen?")
            builder.setPositiveButton("Wyczyść", object: DialogInterface.OnClickListener{
                override fun onClick(dialogInterface: DialogInterface, i:Int){
                    viewModelSettings.clearMarks()
                    dialogInterface.dismiss()
                }
            })

            builder.setNegativeButton("Anuluj", object: DialogInterface.OnClickListener{
                override fun onClick(dialogInterface: DialogInterface, i:Int){
                    dialogInterface.dismiss()
                }
            })
            val dialog: AlertDialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(R.drawable.button)
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(context?.resources!!.getColor(R.color.white))
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(context?.resources!!.getColor(R.color.white))
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextSize(15.0F)
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextSize(15.0F)
            true
        }

        //Clear database
        view.findViewById<TextView>(R.id.clearDatabase).setOnLongClickListener(){ v->
            val builder: AlertDialog.Builder = AlertDialog.Builder(this.context, R.style.RemoveStudentTheme)
            builder.setMessage("Wyczyścić bazę danych?")
            builder.setPositiveButton("Wyczyść", object: DialogInterface.OnClickListener{
                override fun onClick(dialogInterface: DialogInterface, i:Int){
                    viewModelSettings.clearDataBase()
                    dialogInterface.dismiss()
                }
            })

            builder.setNegativeButton("Anuluj", object: DialogInterface.OnClickListener{
                override fun onClick(dialogInterface: DialogInterface, i:Int){
                    dialogInterface.dismiss()
                }
            })
            val dialog: AlertDialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(R.drawable.button)
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(context?.resources!!.getColor(R.color.white))
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(context?.resources!!.getColor(R.color.white))
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextSize(15.0F)
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextSize(15.0F)
            true
        }

        view.findViewById<ImageView>(R.id.settingsLogo).setOnClickListener(){
            view.findNavController().navigate(R.id.action_fragment_settings_to_fragment_main_menu)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_settings.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_settings().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}