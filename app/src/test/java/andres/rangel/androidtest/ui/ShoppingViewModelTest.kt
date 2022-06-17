package andres.rangel.androidtest.ui

import andres.rangel.androidtest.MainCoroutineRule
import andres.rangel.androidtest.getOrAwaitValueTest
import andres.rangel.androidtest.repositories.FakeShoppingRepository
import andres.rangel.androidtest.ui.viewmodels.ShoppingViewModel
import andres.rangel.androidtest.utils.Constants
import andres.rangel.androidtest.utils.Status
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ShoppingViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: ShoppingViewModel

    @Before
    fun setup() {
        viewModel = ShoppingViewModel(FakeShoppingRepository())
    }

    @Test
    fun `insert shopping item with an empty field then returns error`() {
        //Given
        viewModel.insertShoppingItem("name", "", "3.0")
        //When
        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()
        //Then
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with too long name then returns error`() {
        //Given
        val name = buildString {
            for(i in 1..Constants.MAX_NAME_LENGTH + 1) {
                append(1)
            }
        }
        viewModel.insertShoppingItem(name, "5", "3.0")
        //When
        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()
        //Then
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with too high amount then returns error`() {
        //Given
        viewModel.insertShoppingItem("name", "99999999999999999", "3.0")
        //When
        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()
        //Then
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with valid input then returns success`() {
        //Given
        viewModel.insertShoppingItem("name", "5", "3.0")
        //When
        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()
        //Then
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }

}