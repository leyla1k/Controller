package com.example.controller.ui.workShiftPac.workShiftStarted

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controller.entities.Path
import com.example.controller.entities.WorkShift
import com.example.controller.localbase.PathsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date

class WorkShiftStartedViewModel(val pathsRepository: PathsRepository) : ViewModel() {

    private val _pathListFlow = MutableStateFlow<MutableList<Path>>(arrayListOf())
    val pathListFlow: StateFlow<MutableList<Path>> = _pathListFlow.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            pathsRepository.getAllPathsFlow().collect { uit ->
                _pathListFlow.update {
                    mutableListOf<Path>().apply {
                        addAll(
                         uit.filter{ it.workId==getLastWork()?.id }
                        )
                    }
                }
            }
        }
    }

    suspend fun closeWorkShift() {
        withContext(Dispatchers.IO) {
            val work=getLastWork()
            work?.endDate= Date()
            pathsRepository.closeWorkShift(work)
        }
    }

    suspend fun getLastWork(): WorkShift? {

        return pathsRepository.getLastWork()
    }

    suspend fun addNewPath(path: Path) {
        pathsRepository.addNewPath(path)
    }

}