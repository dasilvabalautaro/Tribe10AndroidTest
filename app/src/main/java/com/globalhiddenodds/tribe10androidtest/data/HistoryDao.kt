package com.globalhiddenodds.tribe10androidtest.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {
    @Query("SELECT * from histories ORDER BY id ASC")
    fun getAllHistories(): Flow<List<History>>

    @Query("SELECT * from histories WHERE id = :id")
    fun getHistory(id: Int): Flow<History>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: History)
}