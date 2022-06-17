package andres.rangel.androidtest.ui.fragments

import andres.rangel.androidtest.R
import andres.rangel.androidtest.ui.viewmodels.ShoppingViewModel
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class ShoppingFragment : Fragment(R.layout.fragment_shopping) {

    lateinit var viewModel: ShoppingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[ShoppingViewModel::class.java]
    }

}