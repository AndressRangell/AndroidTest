package andres.rangel.androidtest.ui.fragments

import andres.rangel.androidtest.R
import andres.rangel.androidtest.databinding.FragmentShoppingBinding
import andres.rangel.androidtest.ui.viewmodels.ShoppingViewModel
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

class ShoppingFragment : Fragment(R.layout.fragment_shopping) {

    private lateinit var binding: FragmentShoppingBinding
    private lateinit var viewModel: ShoppingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentShoppingBinding.bind(view)

        viewModel = ViewModelProvider(requireActivity())[ShoppingViewModel::class.java]

        binding.fabAddShoppingItem.setOnClickListener {
            findNavController().navigate(
                ShoppingFragmentDirections.actionShoppingFragmentToAddShoppingItemFragment()
            )
        }
    }

}