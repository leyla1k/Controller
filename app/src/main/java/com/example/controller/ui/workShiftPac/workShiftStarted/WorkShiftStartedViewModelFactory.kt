package com.example.controller.ui.workShiftPac.workShiftStarted

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.controller.localbase.PathsRepository


class WorkShiftStartedViewModelFactory( private
                                val pathsRepository: PathsRepository
): ViewModelProvider.NewInstanceFactory() {
    override fun <T: ViewModel> create(modelClass:Class<T>): T {
        return WorkShiftStartedViewModel(pathsRepository) as T
    }
}