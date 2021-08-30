package com.udacity.shoestore.listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ListingViewModel : ViewModel() {
    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>> get() = _shoeList

    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: LiveData<Boolean> get() = _isEmpty

    init {
        _shoeList.value = mutableListOf()
        _isEmpty.value = true
    }

    fun addShoe(shoe: Shoe) {
        _shoeList.value?.add(shoe)
        _isEmpty.value = false
    }
}