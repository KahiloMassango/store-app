package com.example.store.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.store.core.database.dao.AddressesDao
import com.example.store.core.database.dao.CartDao
import com.example.store.core.database.dao.CategoryDao
import com.example.store.core.database.dao.FavoritesDao
import com.example.store.core.database.dao.GenderCategoryDao
import com.example.store.core.database.dao.GenderDao
import com.example.store.core.database.dao.RecentSearchDao
import com.example.store.core.database.model.AddressEntity
import com.example.store.core.database.model.CartProductItemEntity
import com.example.store.core.database.model.CategoryEntity
import com.example.store.core.database.model.FavoriteProductEntity
import com.example.store.core.database.model.GenderCategory
import com.example.store.core.database.model.GenderEntity
import com.example.store.core.database.model.RecentSearchEntity

@Database(
    version = 1,
    entities = [
        GenderCategory::class,
        GenderEntity::class,
        CategoryEntity::class,
        FavoriteProductEntity::class,
        CartProductItemEntity::class,
        AddressEntity::class,
        RecentSearchEntity::class,
    ],
    exportSchema = false
)
abstract class StoreDatabase : RoomDatabase() {
    abstract fun genderDao(): GenderDao
    abstract fun categoryDao(): CategoryDao
    abstract fun genderCategoryDao(): GenderCategoryDao
    abstract fun favoritesDao(): FavoritesDao
    abstract fun cartDao(): CartDao
    abstract fun addressesDao(): AddressesDao
    abstract fun recentSearchDao(): RecentSearchDao


    companion object {
        @Volatile
        private var instance: StoreDatabase? = null

        fun getDatabase(context: Context): StoreDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(context, StoreDatabase::class.java, "store_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        instance = it
                    }
            }
        }
    }
}
