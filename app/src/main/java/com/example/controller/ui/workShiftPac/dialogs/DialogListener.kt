package com.example.controller.ui.workShiftPac.dialogs

interface DialogListener {
    fun onFinishNewPathAlertDialog(dataGovNumber: String,dataLineNumber:String)

    fun onFinishWorkShiftClosureAlertDialog()
}