package com.example.pokedexapp.core

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.example.pokedexapp.R

abstract class BaseFragment<VB : ViewBinding> : Fragment() {


    private companion object {
        val TAG_INTERNAL: String = BaseFragment::class.java.simpleName
    }

    // The local _binding parameter which is only available
    // within after onCreateView and before onDestroyView.
    private var _binding: VB? = null

    // The property to access within the fragments.
    protected val binding: VB
        get() = _binding!!

    // The TAG value to use in logs.
    @Suppress("PropertyName")
    protected val TAG: String = javaClass.simpleName

    // The inflater class for ViewBinding
    abstract val bindingInflater: (LayoutInflater) -> VB

    // The function to handle ui setup
    abstract fun setupUi()

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = bindingInflater.invoke(layoutInflater)
        return binding.root
    }

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected fun showToastMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
}