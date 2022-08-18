package com.appinionbd.gallery.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.appinionbd.gallery.data.network.response.GalleryResponseItem
import com.appinionbd.gallery.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    repository: Repository
) : ViewModel() {



    val photoResult = repository.fetchRepos()
        .cachedIn(viewModelScope)
}