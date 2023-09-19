package com.example.controller.ui.workShiftPac.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.controller.R


class NewPathAlertDialog : DialogFragment() {

    private var listener: DialogListener?=null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as DialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement DialogListener")
        }
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Введите ваш новый маршрут!")

            val customLayout: View =
                layoutInflater.inflate(R.layout.alert_dialog_new_path, null)
            builder.setView(customLayout)
            val editTextGovNumber =
                customLayout.findViewById<EditText>(R.id.govNumber)
            val editTextPathNumber =
                customLayout.findViewById<EditText>(R.id.pathNumber)

            builder.setPositiveButton("Сохранить") { dialog, id ->
                dialog.cancel()

                if (editTextGovNumber.text.isEmpty() || editTextPathNumber.text.isEmpty()) {
                    Toast.makeText(context, "Заполните все необходимые поля", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val dataGovNumber = editTextGovNumber.text.toString()
                    val dataPathNumber = editTextPathNumber.text.toString()
                    listener?.onFinishNewPathAlertDialog(dataGovNumber,dataPathNumber)

                }


            }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")

    }
}