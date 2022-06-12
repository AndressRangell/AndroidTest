package andres.rangel.androidtest.repositories

import andres.rangel.androidtest.data.local.ShoppingItem
import andres.rangel.androidtest.data.remote.response.ImageResponse
import andres.rangel.androidtest.utils.Resource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FakeShoppingRepository: ShoppingRepository {

    private val shoppingItems = mutableListOf<ShoppingItem>()

    private val observableAllShoppingItems = MutableLiveData<List<ShoppingItem>>(shoppingItems)
    private val observableTotalPrice = MutableLiveData<Float>()

    private var shouldReturnNetworkSuccess = false

    fun setShouldReturnNetworkSuccess(value: Boolean) {
        shouldReturnNetworkSuccess = value
    }

    private fun refreshLiveData() {
        observableAllShoppingItems.postValue(shoppingItems)
        observableTotalPrice.postValue(getTotalPrice())
    }

    private fun getTotalPrice(): Float {
        return shoppingItems.sumOf { it.price.toDouble() }.toFloat()
    }

    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItems.add(shoppingItem)
        refreshLiveData()
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItems.remove(shoppingItem)
        refreshLiveData()
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return if(shouldReturnNetworkSuccess) {
            Resource.success(ImageResponse(listOf(), 0, 0))
        }else {
            Resource.error("Error", null)
        }
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return observableTotalPrice
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return observableAllShoppingItems
    }

}