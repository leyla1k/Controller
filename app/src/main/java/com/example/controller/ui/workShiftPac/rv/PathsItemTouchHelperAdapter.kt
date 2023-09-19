package com.example.controller.ui.workShiftPac.rv



interface PathsItemTouchHelperAdapter {

    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean

    fun onItemDismiss(position: Int)

}