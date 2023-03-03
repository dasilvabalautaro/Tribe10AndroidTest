package com.globalhiddenodds.tribe10androidtest.data

import kotlinx.coroutines.flow.Flow

interface HistoryRepository {
    /**
     * Retrieve all the histories from the the given data source.
     */
    fun getAllHistoriesStream(): Flow<List<History>>

    /**
     * Retrieve a history from the given data source that matches with the [id].
     */
    fun getHistoryStream(id: Int): Flow<History?>

    /**
     * Insert history in the data source
     */
    suspend fun insertHistory(item: History)
}