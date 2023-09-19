package com.example.controller.ui.workShiftPac

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.controller.localbase.PathsRepository
import com.example.controller.ui.workShiftPac.workShiftStarted.WorkShiftStartedViewModel


class WorkShiftViewModelFactory( private
                                        val pathsRepository: PathsRepository
): ViewModelProvider.NewInstanceFactory() {
    override fun <T: ViewModel> create(modelClass:Class<T>): T {
        return WorkShiftViewModel(pathsRepository) as T
    }
}