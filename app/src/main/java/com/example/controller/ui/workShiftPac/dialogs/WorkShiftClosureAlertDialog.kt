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

class WorkShiftClosureAlertDialog: DialogFragment() {

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
            builder.setTitle("Вы уверены, что хотите закрыть смену?")

            val customLayout: View =
                layoutInflater.inflate(R.layout.alert_dialog_work_shift_closure, null)
            builder.setView(customLayout)

            builder.setNegativeButton("Отмена") { dialog, id ->
                dialog.cancel()
            }

            builder.setPositiveButton("Закрыть") { dialog, id ->
                dialog.cancel()
                    listener?.onFinishWorkShiftClosureAlertDialog()
            }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")

    }
}