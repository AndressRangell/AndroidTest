package andres.rangel.androidtest.data.local

import andres.rangel.androidtest.getOrAwaitValue
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class ShoppingDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: ShoppingItemDatabase

    private lateinit var dao: ShoppingDao

    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.shoppingDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertShoppingItem() = runTest {
        //Given
        val shoppingItem = ShoppingItem("name", 1,1f, "url", id = 1)
        //When
        dao.insertShoppingItem(shoppingItem)
        //Then
        val allShoppingItems = dao.observeAllShoppingItems().getOrAwaitValue()
        assertThat(allShoppingItems).contains(shoppingItem)
    }

    @Test
    fun deleteShoppingItem() = runTest {
        //Given
        val shoppingItem = ShoppingItem("name", 1,1f, "url", id = 1)
        dao.insertShoppingItem(shoppingItem)
        //When
        dao.deleteShoppingItem(shoppingItem)
        //Then
        val allShoppingItem = dao.observeAllShoppingItems().getOrAwaitValue()
        assertThat(allShoppingItem).doesNotContain(shoppingItem)
    }

    @Test
    fun observeTotalPriceSum() = runTest {
        //Given
        val shoppingItem1 = ShoppingItem("name", 1,1f, "url", id = 1)
        val shoppingItem2 = ShoppingItem("name", 2,2.5f, "url", id = 2)
        val shoppingItem3 = ShoppingItem("name", 1,3f, "url", id = 3)
        dao.insertShoppingItem(shoppingItem1)
        dao.insertShoppingItem(shoppingItem2)
        dao.insertShoppingItem(shoppingItem3)
        //When
        val totalPrice = dao.observeTotalPrice().getOrAwaitValue()
        //Then -> (1 * 1) + (2 * 2.5) + (1 * 3) = 9
        assertThat(totalPrice).isEqualTo(9)
    }

}