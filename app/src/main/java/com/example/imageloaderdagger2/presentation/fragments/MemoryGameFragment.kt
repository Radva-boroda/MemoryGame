package com.example.imageloaderdagger2.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.imageloaderdagger2.presentation.viewmodels.MemoryGameViewModel
import dagger.android.support.DaggerFragment
import dev.ronnie.imageloaderdagger2.R
import dev.ronnie.imageloaderdagger2.databinding.MemoryGameBinding
import javax.inject.Inject


class MemoryGameFragment : DaggerFragment(R.layout.memory_game) {

    private var binding: MemoryGameBinding? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MemoryGameViewModel by viewModels { viewModelFactory }

    private val listImageOchiqYopiq = arrayOf(false, false, false, false, false, false)
    private val imageIndex = arrayOfNulls<Int>(2)
    private val rasmId = arrayOfNulls<Int>(2)
    private var ochiqRasm = 0
    private var animationDoing = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MemoryGameBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setupClickListeners() {
        binding?.image1?.setOnClickListener {
            imageClick(binding?.image1, R.drawable.uzbek_flag2, 0)
        }
        binding?.image2?.setOnClickListener {
            imageClick(binding?.image2, R.drawable.usa_flag, 1)
        }
        binding?.image3?.setOnClickListener {
            imageClick(binding?.image3, R.drawable.brazilium_flag, 2)
        }
        binding?.image4?.setOnClickListener {
            imageClick(binding?.image4, R.drawable.uzbek_flag2, 3)
        }
        binding?.image5?.setOnClickListener {
            imageClick(binding?.image5, R.drawable.usa_flag, 4)
        }
        binding?.image6?.setOnClickListener {
            imageClick(binding?.image6, R.drawable.brazilium_flag, 5)
        }
    }

    private fun imageClick(imageView: ImageView?, rasm: Int, index: Int) {
        if (!animationDoing) {
            if (!listImageOchiqYopiq[index]) {
                animationOchilishi(imageView, rasm, index)
            } else {
                animationYopilishi(imageView, index)
            }
        }
    }

    private fun animationOchilishi(imageView: ImageView?, rasm: Int, index: Int) {
        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.anim_1)
        imageView?.startAnimation(animation)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                animationDoing = true
            }

            override fun onAnimationEnd(animation: Animation?) {
                val animation2 = AnimationUtils.loadAnimation(requireContext(), R.anim.anim_2)
                imageView?.startAnimation(animation2)
                imageView?.setImageResource(rasm)
                animation2.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {

                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        listImageOchiqYopiq[index] = true
                        imageIndex[ochiqRasm] = index
                        rasmId[ochiqRasm] = rasm
                        ochiqRasm++

                        if (ochiqRasm == 2) {
                            if (rasmId[0] == rasmId[1]) {
                                imageViewAniqla(imageIndex[0])?.visibility = View.INVISIBLE
                                ochiqRasm--
                                imageViewAniqla(imageIndex[1])?.visibility = View.INVISIBLE
                                ochiqRasm--
                            } else {
                                animationYopilishi(imageViewAniqla(imageIndex[0]), imageIndex[0])
                                animationYopilishi(imageViewAniqla(imageIndex[1]), imageIndex[1])
                            }
                        }
                        animationDoing = false
                    }

                    override fun onAnimationRepeat(animation: Animation?) {
                    }
                })
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }
        })
    }

    private fun animationYopilishi(imageView: ImageView?, index: Int?) {
        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.anim_1)
        imageView?.startAnimation(animation)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                animationDoing = true
            }

            override fun onAnimationEnd(animation: Animation?) {
                val animation2 = AnimationUtils.loadAnimation(requireContext(), R.anim.anim_2)
                imageView?.startAnimation(animation2)
                imageView?.setImageResource(R.drawable.android_logo)
                animation2.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {

                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        animationDoing = false
                    }

                    override fun onAnimationRepeat(animation: Animation?) {

                    }
                })
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }
        })
        listImageOchiqYopiq[index!!] = false
        ochiqRasm--
    }

    private fun imageViewAniqla(index: Int?): ImageView? {
        return when (index) {
            0 -> binding?.image1
            1 -> binding?.image2
            2 -> binding?.image3
            3 -> binding?.image4
            4 -> binding?.image5
            5 -> binding?.image6
            else -> null
        }
    }
}