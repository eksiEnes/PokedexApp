package com.example.pokedexapp.ui.features.pokedex

import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.pokedexapp.core.BaseFragment
import com.example.pokedexapp.data.model.uimodel.PokemonItem
import com.example.pokedexapp.databinding.FragmentPokedexBinding
import com.example.pokedexapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokedexFragment : BaseFragment<FragmentPokedexBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentPokedexBinding
        get() = FragmentPokedexBinding::inflate

    private val viewModel: PokedexViewModel by viewModels()
    private val adapter = PokemonListAdapter()
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
                    // TODO Create a loading animation for here
                    Toast.makeText(
                        requireContext(),
                        "Success",
                        Toast.LENGTH_LONG
                    ).show()
                    handleSuccessResponse(result)
                }
            }
        }
    }

    private fun handleSuccessResponse(result: Resource.Success<List<PokemonItem>>) {
        binding.recyclerViewPokedex.adapter = adapter
        adapter.setItemList(result.body)
    }


    private fun initListeners() {

    }


}