package dev.ronnie.imageloaderdagger2.presentation.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import dagger.android.support.DaggerFragment
import dev.ronnie.imageloaderdagger2.R
import dev.ronnie.imageloaderdagger2.databinding.FragmentFullScreenBinding
import dev.ronnie.imageloaderdagger2.presentation.viewmodels.FullScreenViewModel
import javax.inject.Inject


class FragmentFullScreen : DaggerFragment(R.layout.fragment_full_screen) {

    private var binding: FragmentFullScreenBinding? = null

    private val link by lazy {
        arguments?.getString("link")
    }
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: FullScreenViewModel by viewModels {  Log.i("Inject", "viewModel")
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFullScreenBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(requireContext())
            .load(link)
            .into(binding!!.imageView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}





