package com.globalhiddenodds.tribe10androidtest.data

import kotlinx.coroutines.flow.Flow

class OfflineHistoriesRepository(private val historyDao: HistoryDao): HistoryRepository {
    override fun getAllHistoriesStream(): Flow<List<History>> = historyDao.getAllHistories()

    override fun getHistoryStream(id: Int): Flow<History?> = historyDao.getHistory(id)

    override suspend fun insertHistory(item: History) = historyDao.insert(item)
}