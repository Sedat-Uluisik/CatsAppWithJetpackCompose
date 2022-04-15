package com.sedat.catsappwithjetpackcompose.repo

import android.content.Context
import com.sedat.catsappwithjetpackcompose.api.CatApi
import com.sedat.catsappwithjetpackcompose.Util.Resource
import com.sedat.catsappwithjetpackcompose.model.CatItem
import com.sedat.catsappwithjetpackcompose.room.CatsDb
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RepositoryCat @Inject constructor(
    private val catApi: CatApi,
    @ApplicationContext private val context: Context
) {

    private val dao = CatsDb(context).dao()

    suspend fun getCats(): Resource<List<CatItem>>{
        return try {

            val response = catApi.getCats()
            if(response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error!", null)
            }else{
                Resource.error("Error!", null)
            }
        }catch (e: Exception){
            Resource.error("No data!", null)
        }
    }

    suspend fun search(query: String): Resource<List<CatItem>>{
        return try {

            val response = catApi.search(query)
            if(response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error!", null)
            }else{
                Resource.error("Error!", null)
            }
        }catch (e: Exception){
            Resource.error("No data!", null)
        }
    }

    // ----- room functions -----

    suspend fun saveCatFromRoom(catItem: CatItem){
        dao.saveCatFromRoom(catItem)
    }

    suspend fun getCatsFromRoom(callBack: (List<CatItem>) -> Unit){
        val data = dao.getCatsFromRoom()
        callBack(data)
    }

    suspend fun isFavorite(id: String): Boolean{
        return dao.isFavorite(id) != null
    }

    suspend fun deleteCatFromRoom(id: String) = dao.deleteCatFromRoomWithId(id)
}