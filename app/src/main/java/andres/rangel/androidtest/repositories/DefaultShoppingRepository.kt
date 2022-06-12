package andres.rangel.androidtest.repositories

import andres.rangel.androidtest.data.local.ShoppingDao
import andres.rangel.androidtest.data.local.ShoppingItem
import andres.rangel.androidtest.data.remote.PixabayAPI
import andres.rangel.androidtest.data.remote.response.ImageResponse
import andres.rangel.androidtest.utils.Resource
import androidx.lifecycle.LiveData
import javax.inject.Inject

class DefaultShoppingRepository @Inject constructor(
    private val shoppingDao: ShoppingDao,
    private val pixabayAPI: PixabayAPI
) : ShoppingRepository {

    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.insertShoppingItem(shoppingItem)
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.deleteShoppingItem(shoppingItem)
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return try {
            val response = pixabayAPI.searchForImage(imageQuery)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                }
            }
            Resource.error("An unknown error ocurred", null)
        } catch (exception: Exception) {
            Resource.error("Couldn't reach the server. Check your internet connection", null)
        }
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return shoppingDao.observeTotalPrice()
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return shoppingDao.observeAllShoppingItems()
    }

}