package dev.ronnie.imageloaderdagger2.presentation.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dev.ronnie.imageloaderdagger2.dataa.repository.Repository
import javax.inject.Inject


@Suppress("deprecation", "BlockingMethodInNonBlockingContext")
class PicturesViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    val images = repository.getImages().cachedIn(viewModelScope)
}