package com.example.controller.localbase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomWarnings
import androidx.room.Update
import com.example.controller.entities.CardDataItem
import com.example.controller.entities.PathDataItem
import com.example.controller.entities.WorkShiftDataItem
import kotlinx.coroutines.flow.Flow

@Dao
interface MainDao {

    @Query("SELECT * FROM cards WHERE tagId = :tagId")
    fun getCardByTagId(tagId: String): CardDataItem?

/*    @Query("SELECT * FROM cards WHERE tagid = :tagid")
    fun getCardByTag(id: Int): CardDataItem*/

    @Query("SELECT * FROM paths ")
    fun getAllPathsFlow(): Flow<List<PathDataItem>>

    @Insert
    fun addNewPath(pathDataItem: PathDataItem)
    /*@Query("SELECT * from revision WHERE id = :id")
    fun getRevision(id: Int = 1): DbRevision*/

    @Insert
     fun insertNewWorkShift(workShiftDataItem: WorkShiftDataItem)



    @Query("SELECT * FROM workshifts WHERE startDate = (SELECT MAX(startDate) FROM workshifts)")
      fun getLastWork():WorkShiftDataItem?

    @Update
     fun closeWorkShift(workShiftDataItem: WorkShiftDataItem?)
   /* @Query("SELECT * from revision WHERE id = :id")
    suspend fun getRevisionForCreating(id: Int = 1): DbRevision
    @Update
    suspend fun updateRevision(revision: DbRevision)*/


}