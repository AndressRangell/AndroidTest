package andres.rangel.androidtest.repositories

import andres.rangel.androidtest.data.local.ShoppingItem
import andres.rangel.androidtest.data.remote.response.ImageResponse
import andres.rangel.androidtest.utils.Resource
import androidx.lifecycle.LiveData

interface ShoppingRepository {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>

    fun observeTotalPrice(): LiveData<Float>

    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

}