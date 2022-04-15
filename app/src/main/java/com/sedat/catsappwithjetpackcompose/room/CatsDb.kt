package com.sedat.catsappwithjetpackcompose.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sedat.catsappwithjetpackcompose.Util.Converter
import com.sedat.catsappwithjetpackcompose.model.CatItem

@Database(entities = [CatItem::class], exportSchema = false, version = 1)
@TypeConverters(Converter::class)
abstract class CatsDb: RoomDatabase() {
    abstract fun dao(): Dao

    companion object{
        @Volatile
        private var instance: CatsDb ?= null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: Room.databaseBuilder(
                context,
                CatsDb::class.java,
                "compose_cats_db"
            ).build().also {
                instance = it
            }
        }
    }
}