package andres.rangel.androidtest.ui

import andres.rangel.androidtest.adapters.ImageAdapter
import andres.rangel.androidtest.adapters.ShoppingItemAdapter
import andres.rangel.androidtest.repositories.FakeShoppingRepositoryAndroidTest
import andres.rangel.androidtest.ui.fragments.AddShoppingItemFragment
import andres.rangel.androidtest.ui.fragments.ImagePickFragment
import andres.rangel.androidtest.ui.fragments.ShoppingFragment
import andres.rangel.androidtest.ui.viewmodels.ShoppingViewModel
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class TestShoppingFragmentFactory @Inject constructor(
    private val imageAdapter: ImageAdapter,
    private val glide: RequestManager,
    private val shoppingItemAdapter: ShoppingItemAdapter
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className) {
            ImagePickFragment::class.java.name -> ImagePickFragment(imageAdapter)
            AddShoppingItemFragment::class.java.name -> AddShoppingItemFragment(glide)
            ShoppingFragment::class.java.name -> ShoppingFragment(
                shoppingItemAdapter, ShoppingViewModel(FakeShoppingRepositoryAndroidTest())
            )
            else -> super.instantiate(classLoader, className)
        }
    }

}