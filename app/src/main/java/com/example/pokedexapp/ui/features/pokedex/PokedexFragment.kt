package com.example.pokedexapp.ui.features.pokedex

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import com.example.pokedexapp.R
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
    private val adapter = PokemonListAdapter().apply {
        setOnPokedexItemClickListener {
            val id = getItemPosition(it)!!.id
            val action = PokedexFragmentDirections.actionPokedexFragmentToDetailFragment(id)
            navigate(action)
        }
    }
    override fun setupUi() {
        initListeners()
        observeLiveData()
    }

    private fun observeLiveData() {
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

        binding.fabSortType.setOnClickListener {
            val dialog = Dialog(requireContext())
            dialog.setContentView(R.layout.dialog_sort_xml)



            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

            val radioGroup = dialog.findViewById<RadioGroup>(R.id.radio_group_sort)
            val selectedRadioButtonId = radioGroup.checkedRadioButtonId

            if (selectedRadioButtonId != -1) {
                val selectedRadioButton = dialog.findViewById<RadioButton>(selectedRadioButtonId)
                viewModel.pokedexSortTypeLiveData.value = selectedRadioButton.text.toString()

            } else {
                // Hiçbir RadioButton seçili değil
            }



            dialog.show()
        }


        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO()
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                when(viewModel.pokedexSortTypeLiveData.value ){
                    "Number"->viewModel.pokedexLiveData.value.apply {
                        when(this){
                            is Resource.Success -> {
                                val result = this.body.filter {
                                    it.id.toString().startsWith(newText!!)
                                }
                                adapter.setItemList(result)
                            }

                            else -> {}
                        }
                    }
                    "Name"-> viewModel.pokedexLiveData.value.apply {
                        when(this){
                            is Resource.Success -> {
                                val result = this.body.filter {
                                    it.name.startsWith(newText!!)
                                }
                                adapter.setItemList(result)
                            }

                            else -> {}
                        }
                    }
                }


                return true
            }
        })

    }


}