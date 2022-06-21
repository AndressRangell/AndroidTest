package andres.rangel.androidtest.ui.fragments

import andres.rangel.androidtest.R
import andres.rangel.androidtest.adapters.ImageAdapter
import andres.rangel.androidtest.databinding.FragmentImagePickBinding
import andres.rangel.androidtest.ui.viewmodels.ShoppingViewModel
import andres.rangel.androidtest.utils.Constants.GRID_SPAN_COUNT
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import javax.inject.Inject

class ImagePickFragment @Inject constructor(
    val imageAdapter: ImageAdapter
) : Fragment(R.layout.fragment_image_pick) {

    private lateinit var binding: FragmentImagePickBinding
    lateinit var viewModel: ShoppingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentImagePickBinding.bind(view)

        viewModel = ViewModelProvider(requireActivity())[ShoppingViewModel::class.java]
        initRecyclerView()

        imageAdapter.setOnItemClickListener {
            findNavController().popBackStack()
            viewModel.setCurrentImageUrl(it)
        }
    }

    private fun initRecyclerView() {
        binding.apply {
            rvImages.apply {
                adapter = imageAdapter
                layoutManager = GridLayoutManager(requireContext(), GRID_SPAN_COUNT)
            }
        }
    }

}