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
class HomeScreenViewModel @Inject constructor(
    private val repository: RepositoryCat
): ViewModel() {

    val catList = mutableStateOf<List<CatItem>>(listOf())

    fun getCatsFromApi(){
        viewModelScope.launch {
            val results = repository.getCats()
            if(results.data != null && results.data.isNotEmpty())
                catList.value = results.data
        }
    }

    fun search(query: String){
        if(!query.isNullOrEmpty()){
            viewModelScope.launch {
                val results = repository.search(query)
                if(results.data != null && results.data.isNotEmpty())
                    catList.value = results.data
            }
        }
    }
}