package com.globalhiddenodds.tribe10androidtest

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.globalhiddenodds.tribe10androidtest.data.History
import com.globalhiddenodds.tribe10androidtest.data.HistoryDao
import com.globalhiddenodds.tribe10androidtest.data.HistoryDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class HistoryDaoTest {
    private lateinit var historyDao: HistoryDao
    private lateinit var historyDatabase: HistoryDatabase
    private val history1 = History(1, "Bridge accident.", null)
    private val history2 = History(2, "Fight on the avenue", null)

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        historyDatabase = Room.inMemoryDatabaseBuilder(context, HistoryDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        historyDao = historyDatabase.historyDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        historyDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertsHistoryIntoDB() = runBlocking {
        addOneHistoryToDb()
        val allItems = historyDao.getAllHistories().first()
        assertEquals(allItems[0], history1)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllHistories_returnsAllHistoriesFromDB() = runBlocking {
        addTwoHistoriesToDb()
        val allItems = historyDao.getAllHistories().first()
        assertEquals(allItems[0], history1)
        assertEquals(allItems[1], history2)
    }


    @Test
    @Throws(Exception::class)
    fun daoGetHistory_returnsHistoryFromDB() = runBlocking {
        addOneHistoryToDb()
        val item = historyDao.getHistory(1)
        assertEquals(item.first(), history1)
    }

    private suspend fun addOneHistoryToDb() {
        historyDao.insert(history1)
    }

    private suspend fun addTwoHistoriesToDb() {
        historyDao.insert(history1)
        historyDao.insert(history2)
    }
}