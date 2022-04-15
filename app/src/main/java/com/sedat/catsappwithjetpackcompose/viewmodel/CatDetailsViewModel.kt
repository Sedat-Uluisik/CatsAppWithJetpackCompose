package com.sedat.catsappwithjetpackcompose.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sedat.catsappwithjetpackcompose.model.CatItem
import com.sedat.catsappwithjetpackcompose.repo.RepositoryCat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatDetailsViewModel @Inject constructor(
    private val repository: RepositoryCat
): ViewModel() {

    fun saveCatFromRoom(catItem: CatItem){
        viewModelScope.launch {
            repository.saveCatFromRoom(catItem)
        }
    }

    fun deleteCatFromRoomWithId(id: String) = viewModelScope.launch {
        repository.deleteCatFromRoom(id)
    }

    val isFavorite = mutableStateOf(false)
    fun isFavorite(id: String){
        viewModelScope.launch {
            val isFav = repository.isFavorite(id)
            isFavorite.value = isFav
        }
    }
}