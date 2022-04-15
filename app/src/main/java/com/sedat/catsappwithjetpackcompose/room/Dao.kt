package com.sedat.catsappwithjetpackcompose.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sedat.catsappwithjetpackcompose.model.CatItem

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCatFromRoom(catItem: CatItem)

    @Query("SELECT * FROM favorites_cat")
    suspend fun getCatsFromRoom(): List<CatItem>

    @Query("SELECT * FROM favorites_cat WHERE id =:id")
    suspend fun isFavorite(id: String): CatItem

    @Query("DELETE FROM favorites_cat WHERE id =:id")
    suspend fun deleteCatFromRoomWithId(id: String)
}