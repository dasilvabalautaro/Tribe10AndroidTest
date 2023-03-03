package com.globalhiddenodds.tribe10androidtest.data

import android.content.Context

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val historiesRepository: HistoryRepository
    val personRepository: PersonRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineHistoriesRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [HistoryRepository]
     */
    override val historiesRepository: HistoryRepository by lazy {
        OfflineHistoriesRepository(HistoryDatabase.getDatabase(context).historyDao())
    }

    override val personRepository: PersonRepository by lazy {
        OfflinePersonsRepository(HistoryDatabase.getDatabase(context).personDao())
    }

}