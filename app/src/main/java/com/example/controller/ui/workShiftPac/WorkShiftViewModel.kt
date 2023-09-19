package com.example.controller.ui.workShiftPac

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controller.entities.WorkShift
import com.example.controller.localbase.PathsRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.Date
import java.util.UUID

class WorkShiftViewModel(val pathsRepository: PathsRepository) : ViewModel() {

    fun createNewWorkShift() {
        viewModelScope.launch {
            pathsRepository.insertNewWorkShift(
                WorkShift(
                    id = 0,
                    Date(),
                    null
                )
            )
        }
    }


}