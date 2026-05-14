package com.nammakathe.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nammakathe.R
import com.nammakathe.core.util.animateBounce
import com.nammakathe.databinding.FragmentHomeBinding
import com.nammakathe.ui.adapter.HeroCardAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel
            by activityViewModels()

    private lateinit var heroAdapter: HeroCardAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding =
            FragmentHomeBinding.inflate(
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

        setupWelcome()
        setupDailyHero()
        setupRecentHeroes()
        setupStats()
        setupSearch()
    }

    private fun setupWelcome() {

        lifecycleScope.launch {

            val name =
                viewModel.userName.first()

            val isKn =
                viewModel.isKannada.first()

            binding.tvWelcome.text =
                if (isKn)
                    "ನಮಸ್ಕಾರ, $name! 👋"
                else
                    "Hello, $name! 👋"

            binding.tvSubtitle.text =
                if (isKn)
                    "ಇಂದು ಯಾರ ಕಥೆ ಓದೋಣ?"
                else
                    "Whose story shall we read today?"
        }
    }

    private fun setupDailyHero() {

        val hero =
            viewModel.dailyHero
                ?: return

        lifecycleScope.launch {

            val isKn =
                viewModel.isKannada.first()

            binding.tvDailyHeroName.text =
                if (isKn)
                    hero.nameKn
                else
                    hero.name

            binding.tvDailyHeroTitle.text =
                if (isKn)
                    hero.titleKn
                else
                    hero.title

            binding.tvDailyHeroEmoji.text =
                hero.emoji

            binding.cardDailyHero
                .setOnClickListener {

                    it.animateBounce()

                    findNavController()
                        .navigate(
                            R.id.action_home_to_story,
                            bundleOf(
                                "heroId" to hero.id
                            )
                        )
                }
        }
    }

    private fun setupRecentHeroes() {

        heroAdapter =
            HeroCardAdapter(

                onHeroClick = { hero ->

                    findNavController()
                        .navigate(
                            R.id.action_home_to_story,
                            bundleOf(
                                "heroId" to hero.id
                            )
                        )
                },

                onFavoriteClick = {
                        hero,
                        isFav ->

                    lifecycleScope.launch {

                        val userId =
                            viewModel.userId.first()

                        viewModel
                            .toggleFavorite(
                                hero.id,
                                userId,
                                isFav
                            )
                    }
                }
            )

        binding.rvHeroes.apply {

            layoutManager =
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.HORIZONTAL,
                    false
                )

            adapter =
                heroAdapter
        }

        lifecycleScope.launch {

            val isKn =
                viewModel.isKannada.first()

            val userId =
                viewModel.userId.first()

            val heroes =
                viewModel.getAllHeroes()

            val favoriteIds =
                viewModel
                    .getFavoriteHeroIds(
                        userId
                    )
                    .first()

            val updated =
                heroes.map {

                    it.copy(
                        isFavorite =
                            it.id in favoriteIds
                    )
                }

            heroAdapter.submitList(
                updated,
                isKn
            )
        }
    }

    private fun setupStats() {

        lifecycleScope.launch {

            viewLifecycleOwner
                .repeatOnLifecycle(
                    Lifecycle.State.STARTED
                ) {

                    val userId =
                        viewModel.userId.first()

                    launch {

                        viewModel
                            .getCompletedCount(
                                userId
                            )
                            .collect { count ->

                                binding.tvStoriesCount.text =
                                    "$count"
                            }
                    }

                    launch {

                        viewModel
                            .getBadgeCount(
                                userId
                            )
                            .collect { count ->

                                binding.tvBadgesCount.text =
                                    "$count"
                            }
                    }
                }
        }
    }

    private fun setupSearch() {

        binding.etSearch
            .setOnEditorActionListener {
                    v,
                    _,
                    _ ->

                val query =
                    v.text
                        .toString()
                        .trim()

                if (
                    query.isNotEmpty()
                ) {

                    lifecycleScope.launch {

                        val isKn =
                            viewModel
                                .isKannada
                                .first()

                        val results =
                            viewModel
                                .searchHeroes(
                                    query
                                )

                        heroAdapter
                            .submitList(
                                results,
                                isKn
                            )

                        binding.tvSectionHeroes.text =
                            "Search Results (${results.size})"
                    }
                }

                false
            }

        binding.btnClearSearch
            .setOnClickListener {

                binding.etSearch
                    .text
                    ?.clear()

                lifecycleScope.launch {

                    val isKn =
                        viewModel
                            .isKannada
                            .first()

                    heroAdapter
                        .submitList(
                            viewModel.getAllHeroes(),
                            isKn
                        )

                    binding.tvSectionHeroes.text =
                        "All Heroes"
                }
            }
    }

    override fun onDestroyView() {

        super.onDestroyView()

        _binding = null
    }
}