package dev.ronnie.imageloaderdagger2.presentation.fragments



import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment
import dev.ronnie.imageloaderdagger2.R
import dev.ronnie.imageloaderdagger2.databinding.FragmentPicturesBinding
import dev.ronnie.imageloaderdagger2.presentation.adapters.ImagesAdapter
import dev.ronnie.imageloaderdagger2.presentation.viewmodels.PicturesViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


class FragmentPictures : DaggerFragment(R.layout.fragment_pictures) {
    private var binding: FragmentPicturesBinding? = null
    private val adapter = ImagesAdapter()


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: PicturesViewModel by viewModels {  Log.i("Inject", "viewModel")
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPicturesBinding.inflate(inflater, container, false)

        adapter.itemClickListener = {
            val bundle = Bundle()
            bundle.putString("link", it.urls.regular)
            view?.findNavController()?.navigate(R.id.action_fragment_pictures_to_fragmentFullScreen, bundle)
            Log.i("Click", "pictures_to_fragmentFullScreen")
        }

        viewLifecycleOwner.lifecycleScope.launch{
            viewModel.images.collectLatest{ adapter.submitData(it)}
        }

        Log.i("image", "transfer")
        binding?.recyclerV?.adapter = adapter
        binding?.recyclerV?.layoutManager =
            GridLayoutManager(requireContext(),1, LinearLayoutManager.VERTICAL, false)

        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}





