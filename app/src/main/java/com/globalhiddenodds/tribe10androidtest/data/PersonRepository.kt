package com.globalhiddenodds.tribe10androidtest.data

interface PersonRepository {
    suspend fun getCount(): Int
    suspend fun insertPerson(item: Person)
}