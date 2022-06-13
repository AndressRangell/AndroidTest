package andres.rangel.androidtest.ui

import andres.rangel.androidtest.data.local.ShoppingItem
import andres.rangel.androidtest.data.remote.response.ImageResponse
import andres.rangel.androidtest.repositories.ShoppingRepository
import andres.rangel.androidtest.utils.Event
import andres.rangel.androidtest.utils.Resource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingViewModel @Inject constructor(
    private val repository: ShoppingRepository
) : ViewModel() {

    val shoppingItems = repository.observeAllShoppingItems()

    val totalPrice = repository.observeTotalPrice()

    private val images = MutableLiveData<Event<Resource<ImageResponse>>>()

    private val currentImageUrl = MutableLiveData<String>()

    private val insertShoppingItemStatus = MutableLiveData<Event<Resource<ShoppingItem>>>()

    fun setCurrentImageUrl(url: String) {
        currentImageUrl.postValue(url)
    }

    fun deleteShoppingItem(shoppingItem: ShoppingItem) = viewModelScope.launch {
        repository.deleteShoppingItem(shoppingItem)
    }

    fun insertShoppingItemIntoDb(shoppingItem: ShoppingItem) = viewModelScope.launch {
        repository.insertShoppingItem(shoppingItem)
    }

    fun insertShoppingItem(name: String, amountString: String, priceString: String) {

    }

    fun searchForImage(imageQuery: String) {

    }

}