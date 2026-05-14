package com.nammakathe.ui.district

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.nammakathe.R
import com.nammakathe.databinding.FragmentDistrictBinding
import com.nammakathe.ui.adapter.DistrictAdapter
import com.nammakathe.ui.home.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DistrictFragment : Fragment() {

    private var _binding: FragmentDistrictBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel
            by activityViewModels()

    private lateinit var districtAdapter: DistrictAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding =
            FragmentDistrictBinding.inflate(
                inflater,
                container,
                false
            )

        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {

        super.onViewCreated(
            view,
            savedInstanceState
        )

        setupDistrictGrid()
    }

    private fun setupDistrictGrid() {

        districtAdapter =
            DistrictAdapter { district ->

                findNavController()
                    .navigate(
                        R.id.action_district_to_heroList,
                        bundleOf(
                            "districtId" to district.id
                        )
                    )
            }

        binding.rvDistricts.apply {

            layoutManager =
                GridLayoutManager(
                    requireContext(),
                    2
                )

            adapter =
                districtAdapter
        }

        lifecycleScope.launch {

            val isKn =
                viewModel.isKannada.first()

            val districts =
                viewModel.getAllDistricts()

            val heroes =
                viewModel.getAllHeroes()

            val updatedDistricts =
                districts.map { district ->

                    district.copy(

                        heroCount =
                            heroes.count {

                                it.districtId ==
                                        district.id
                            }
                    )
                }

            districtAdapter.submitList(
                updatedDistricts,
                isKn
            )
        }
    }

    override fun onDestroyView() {

        super.onDestroyView()

        _binding = null
    }
}