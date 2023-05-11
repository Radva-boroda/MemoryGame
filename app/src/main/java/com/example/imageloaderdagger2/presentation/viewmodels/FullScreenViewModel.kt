package dev.ronnie.imageloaderdagger2.presentation.viewmodels


import androidx.lifecycle.ViewModel
import dev.ronnie.imageloaderdagger2.dataa.repository.Repository
import javax.inject.Inject


@Suppress("deprecation", "BlockingMethodInNonBlockingContext")
class FullScreenViewModel @Inject constructor(private val repository: Repository): ViewModel() {
}