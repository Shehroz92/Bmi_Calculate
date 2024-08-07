package eu.tutorials.a7_minutesworkoutapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {
    @Insert
    suspend fun insert(historyEntity: HistoryEntity)

    @Query("SELECT * FROM `history_table` ")
    fun fetchAllDate():Flow<List<HistoryEntity>>

}