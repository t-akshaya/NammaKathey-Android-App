package com.nammakathe.ui.district

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.nammakathe.R
import com.nammakathe.databinding.FragmentHeroListBinding
import com.nammakathe.ui.adapter.HeroCardAdapter
import com.nammakathe.ui.home.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HeroListFragment : Fragment() {

    private var _binding: FragmentHeroListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var heroAdapter: HeroCardAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHeroListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val districtId = arguments?.getString("districtId") ?: return
        setupHeroList(districtId)
        binding.btnBack.setOnClickListener { findNavController().popBackStack() }
    }

    private fun setupHeroList(districtId: String) {
        heroAdapter = HeroCardAdapter(
            onHeroClick = { hero ->
                findNavController().navigate(R.id.action_heroList_to_story, bundleOf("heroId" to hero.id))
            },
            onFavoriteClick = { hero, isFav ->
                lifecycleScope.launch {
                    val userId = viewModel.userId.first()
                    viewModel.toggleFavorite(hero.id, userId, isFav)
                }
            }
        )
        binding.rvHeroes.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = heroAdapter
        }
        lifecycleScope.launch {
            val isKn = viewModel.isKannada.first()
            val district = viewModel.getAllDistricts().find { it.id == districtId }
            binding.tvDistrictTitle.text = if (isKn) district?.nameKn ?: "" else district?.name ?: ""
            val heroes = viewModel.getHeroesByDistrict(districtId)
            val userId = viewModel.userId.first()
            val favoriteIds = viewModel.getFavoriteHeroIds(userId).first()
            heroAdapter.submitList(heroes.map { it.copy(isFavorite = it.id in favoriteIds) }, isKn)
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
