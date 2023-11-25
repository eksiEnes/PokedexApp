package com.example.pokedexapp.ui.features.detail

import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.pokedexapp.core.BaseFragment
import com.example.pokedexapp.data.model.response.Pokemon
import com.example.pokedexapp.databinding.FragmentDetailBinding
import com.example.pokedexapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentDetailBinding
        get() = FragmentDetailBinding::inflate

    private val viewModel: DetailViewModel by viewModels()

    private val args: DetailFragmentArgs by navArgs()
    override fun setupUi() {
        val pokemonId = args.pokemonId
        viewModel.getPokemonInfo(pokemonId)
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.pokemonLiveData.observe(viewLifecycleOwner) { result ->
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
                    handleSuccessResponse(result)
                }
            }
        }
    }

    private fun handleSuccessResponse(result: Resource.Success<Pokemon>) {
        Toast.makeText(requireContext(), result.body.name, Toast.LENGTH_LONG).show()
    }


}