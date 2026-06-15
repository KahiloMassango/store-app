package com.example.store.core.database

import com.example.store.core.database.datasources.GenderLocalDataSource
import com.example.store.core.database.dao.GenderCategoryDao
import com.example.store.core.database.dao.GenderDao
import com.example.store.core.database.model.GenderCategory
import com.example.store.core.database.model.GenderEntity
import com.example.store.core.database.model.GenderWithCategories
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class GenderLocalDataSourceImpl(
    private val genderDao: GenderDao,
    private val genderCategoryDao: GenderCategoryDao
): GenderLocalDataSource {

    override fun getGendersFlow(): Flow<List<GenderEntity>> {
        return genderDao.getGenderFlow()
    }

    override suspend fun upsertGenders(genders: List<GenderEntity>) {
       withContext(Dispatchers.IO) {
           val newGenderIds = genders.map { it.genderId }
           genderDao.insertGenders(genders)
           genderDao.deleteGendersNotIn(newGenderIds)
       }
    }

    override suspend fun getGenderWithCategory(): List<GenderWithCategories> {
        return genderDao.getGenderWithCategory()
    }

    override suspend fun saveGenderCategories(genderCategories: List<GenderCategory>) {
        withContext(Dispatchers.IO) {
            genderCategoryDao.deleteGenderCategories()
             genderCategoryDao.upsertGenderCategories(genderCategories)
        }
    }

    override fun getGenderWithCategoryFlow(): Flow<List<GenderWithCategories>> {
        return genderDao.getGenderWithCategoryFlow()
    }


}