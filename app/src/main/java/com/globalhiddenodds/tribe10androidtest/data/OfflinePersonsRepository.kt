package com.globalhiddenodds.tribe10androidtest.data

class OfflinePersonsRepository(private val personDao: PersonDao): PersonRepository {
    override suspend fun getCount(): Int = personDao.getCount()
    override suspend fun insertPerson(item: Person) = personDao.insert(item)
}