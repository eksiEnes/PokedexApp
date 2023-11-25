package com.example.pokedexapp.ui.features.pokedex

import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.pokedexapp.core.BaseFragment
import com.example.pokedexapp.databinding.FragmentPokedexBinding
import com.example.pokedexapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokedexFragment : BaseFragment<FragmentPokedexBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentPokedexBinding
        get() = FragmentPokedexBinding::inflate

    private val viewModel: PokedexViewModel by viewModels()
    override fun setupUi() {
        initListeners()

        observeSampleLiveData()


    }

    private fun observeSampleLiveData() {
        viewModel.pokedexLiveData.observe(viewLifecycleOwner) { result ->

            when(result){
                is Resource.Loading -> {
                    // TODO Create a loading animation for here
                    Toast.makeText(
                        requireContext(),
                        "Loading animation should be here.",
                        Toast.LENGTH_LONG
                    ).show()
                }

                is Resource.Error -> {
                    TODO()
                }
                is Resource.Success -> {
                    binding.deneme.text = result.body.results.size.toString()
                }
            }
        }
    }

    private fun initListeners() {

    }


}