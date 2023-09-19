package com.example.controller.localbase

import com.example.controller.entities.Path
import com.example.controller.entities.PathDataItem
import com.example.controller.entities.WorkShift
import com.example.controller.entities.WorkShiftDataItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class PathsRepository(val dao: MainDao) {


    fun getAllPathsFlow(): Flow<List<Path>> {

        val list = dao.getAllPathsFlow()
        return list.map {
            it.map { uit ->
                fromPathDataItemToPath(uit)
            }
        }
    }


    //////
    suspend fun insertNewWorkShift(work: WorkShift) {
        withContext(Dispatchers.IO) {
            dao.insertNewWorkShift(fromWorkShiftToWorkShiftDataItem(work))
        }
    }

    suspend fun closeWorkShift(workShift: WorkShift?){
        withContext(Dispatchers.IO) {
            dao.closeWorkShift(workShift?.let { fromWorkShiftToWorkShiftDataItem(it) })
        }
    }

    suspend fun getLastWork():WorkShift? {

           return fromWorkShiftDataItemToWorkShift(dao.getLastWork())
    }

    suspend fun addNewPath(path:Path){
        withContext(Dispatchers.IO) {
            dao.addNewPath(fromPathToPathDataItem(path))
        }
    }

}