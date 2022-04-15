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
class FavoritesViewModel @Inject constructor(
    private val repository: RepositoryCat
): ViewModel() {

    val catList = mutableStateOf<List<CatItem>>(listOf())

    fun getCatsFromRoom(){
        viewModelScope.launch {
            repository.getCatsFromRoom {
                catList.value = it
            }
        }
    }

    fun deleteCatFromRoomWithId(id: String) = viewModelScope.launch {
        repository.deleteCatFromRoom(id)
    }
}