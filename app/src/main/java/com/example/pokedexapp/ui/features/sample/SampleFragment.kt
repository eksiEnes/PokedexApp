package com.example.pokedexapp.ui.features.sample

import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.example.pokedexapp.core.BaseFragment
import com.example.pokedexapp.data.model.request.SampleRequestModel
import com.example.pokedexapp.databinding.FragmentSampleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SampleFragment : BaseFragment<FragmentSampleBinding>() {

    private val viewModel: SampleViewModel by viewModels()

    override val bindingInflater: (inflater: LayoutInflater) -> FragmentSampleBinding
        get() = FragmentSampleBinding::inflate

    override fun setupUi() {
        initListeners()
        observeSampleLiveData()
    }

    private fun initListeners() {
        binding.btnClickMe.setOnClickListener {
            viewModel.sampleRequest(
                SampleRequestModel(
                    sample = "Sample request text"
                )
            )
        }
    }

    private fun observeSampleLiveData() {
        viewModel.sampleLiveData.observe(viewLifecycleOwner) { result ->
            result.onLoading {
                // TODO Create a loading animation for here
                Toast.makeText(
                    requireContext(),
                    "Loading animation should be here.",
                    Toast.LENGTH_LONG
                ).show()
            }.onSuccess { responseModel ->
                binding.tvSomeKindOfText.text = responseModel.sampleId.toString()
            }.onError { error ->
                // TODO create a dialog fragment for showing error messages
                Snackbar.make(binding.linearLayout, error.message, 1000).show()
            }
        }
    }


}