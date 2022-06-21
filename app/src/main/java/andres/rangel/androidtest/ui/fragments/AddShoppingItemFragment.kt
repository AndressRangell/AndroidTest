package andres.rangel.androidtest.ui.fragments

import andres.rangel.androidtest.R
import andres.rangel.androidtest.databinding.FragmentAddShoppingItemBinding
import andres.rangel.androidtest.ui.viewmodels.ShoppingViewModel
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

class AddShoppingItemFragment : Fragment(R.layout.fragment_add_shopping_item) {

    private lateinit var binding: FragmentAddShoppingItemBinding
    private lateinit var viewModel: ShoppingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddShoppingItemBinding.bind(view)

        viewModel = ViewModelProvider(requireActivity())[ShoppingViewModel::class.java]

        binding.ivShoppingImage.setOnClickListener {
            findNavController().navigate(
                AddShoppingItemFragmentDirections.actionAddShoppingItemFragmentToImagePickFragment()
            )
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.setCurrentImageUrl("")
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

}