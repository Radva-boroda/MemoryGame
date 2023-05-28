package com.example.imageloaderdagger2.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.imageloaderdagger2.presentation.viewmodels.RegisterViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.android.support.DaggerFragment
import dev.ronnie.imageloaderdagger2.R
import dev.ronnie.imageloaderdagger2.databinding.RegisterHomeFragmentBinding
import javax.inject.Inject

class RegisterFragment: DaggerFragment(R.layout.register_home_fragment) {
    private var binding: RegisterHomeFragmentBinding? = null
    private var edName: EditText? = null
    private var edSecName: EditText? = null
    private var edEmail: EditText? = null
    private var USER_KEY : String = "User"
    private var save : Bundle? = null

    var mDataBase : DatabaseReference? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: RegisterViewModel by viewModels {  Log.i("Inject", "viewModel")
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = RegisterHomeFragmentBinding.inflate(inflater, container, false)

        init()
        binding?.saveButton?.setOnClickListener {
            view?.let { it1 -> onClickSave(it1) }
        }

        binding?.readButton2?.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_registerFragment2_to_memoryGameFragment)
        }

        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun init() {
        edName = binding?.edName
        edSecName = binding?.secondName
        edEmail = binding?.email
        mDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY)
    }

    fun onClickSave(view : View) {

        var id : String? = mDataBase?.key
        var name : String? = edName?.text.toString()
        var sec_name : String? = edSecName?.text.toString()
        var email : String? = edEmail?.text.toString()

        val newUser = name?.let { id?.let { it1 -> sec_name?.let { it2 -> email?.let { it3 ->
            User(it1, it, it2,
                it3
            )
        } } } }
        mDataBase?.push()?.setValue(newUser)

    }
    fun onClickRead(view : View) {

    }
}
