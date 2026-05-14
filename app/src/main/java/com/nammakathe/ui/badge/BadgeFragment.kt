package com.nammakathe.ui.badge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.nammakathe.databinding.FragmentBadgeBinding
import com.nammakathe.ui.adapter.BadgeAdapter
import com.nammakathe.ui.home.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import com.nammakathe.core.domain.model.HeroBadge
import com.nammakathe.core.domain.model.BadgeRarity

@AndroidEntryPoint
class BadgeFragment : Fragment() {

    private var _binding: FragmentBadgeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var badgeAdapter: BadgeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBadgeBinding.inflate(
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

        loadBadges()
    }

    private fun loadBadges() {

        lifecycleScope.launch {

            val userId =
                viewModel.userId.first()

            val isKn =
                viewModel.isKannada.first()

            val allBadges = listOf(
                HeroBadge(
                    id = "badge_kempe",
                    name = "City Builder Badge",
                    nameKn = "ನಗರ ನಿರ್ಮಾತೃ ಬ್ಯಾಡ್ಜ್",
                    description = "You learned about Kempe Gowda",
                    descriptionKn = "ಕೆಂಪೇಗೌಡರ ಬಗ್ಗೆ ಕಲಿತಿರಿ",
                    icon = "🏙️",
                    rarity = BadgeRarity.EPIC
                ),

                HeroBadge(
                    id = "badge_cariappa",
                    name = "Army Hero Badge",
                    nameKn = "ಸೇನೆ ವೀರ ಬ್ಯಾಡ್ಜ್",
                    description = "You learned about Cariappa",
                    descriptionKn = "ಕಾರಿಯಪ್ಪರ ಬಗ್ಗೆ ಕಲಿತಿರಿ",
                    icon = "🎖️",
                    rarity = BadgeRarity.LEGENDARY
                ),

                HeroBadge(
                    id = "badge_bhimsen",
                    name = "Music Badge",
                    nameKn = "ಸಂಗೀತ ಬ್ಯಾಡ್ಜ್",
                    description = "You learned about Bhimsen Joshi",
                    descriptionKn = "ಭೀಮಸೇನ್ ಜೋಶಿ ಬಗ್ಗೆ ಕಲಿತಿರಿ",
                    icon = "🎵",
                    BadgeRarity.EPIC
                ),

                HeroBadge(
                    id = "badge_tipu",
                    name = "Warrior Badge",
                    nameKn = "ವೀರ ಬ್ಯಾಡ್ಜ್",
                    description = "You learned about Tipu Sultan",
                    descriptionKn = "ಟಿಪ್ಪು ಸುಲ್ತಾನರ ಬಗ್ಗೆ ಕಲಿತಿರಿ",
                    icon = "⚔️",
                    BadgeRarity.EPIC
                ),

                HeroBadge(
                    id = "badge_chennamma",
                    name = "Queen Badge",
                    nameKn = "ರಾಣಿ ಬ್ಯಾಡ್ಜ್",
                    description = "You learned about Kittur Chennamma",
                    descriptionKn = "ಕಿತ್ತೂರು ಚೆನ್ನಮ್ಮರ ಬಗ್ಗೆ ಕಲಿತಿರಿ",
                    icon = "👑",
                    rarity = BadgeRarity.LEGENDARY
                ),

                HeroBadge(
                    id = "badge_rayanna",
                    name = "Lion Badge",
                    nameKn = "ಸಿಂಹ ಬ್ಯಾಡ್ಜ್",
                    description = "You learned about Sangolli Rayanna",
                    descriptionKn = "ಸಂಗೊಳ್ಳಿ ರಾಯಣ್ಣರ ಬಗ್ಗೆ ಕಲಿತಿರಿ",
                    icon = "🦁",
                    rarity = BadgeRarity.LEGENDARY
                ),

                HeroBadge(
                    id = "badge_kuvempu",
                    name = "Poet Badge",
                    nameKn = "ಕವಿ ಬ್ಯಾಡ್ಜ್",
                    description = "You learned about Kuvempu",
                    descriptionKn = "ಕುವೆಂಪುರ ಬಗ್ಗೆ ಕಲಿತಿರಿ",
                    icon = "📜",
                    rarity = BadgeRarity.EPIC
                ),

                HeroBadge(
                    id = "badge_visvesvaraya",
                    name = "Engineer Badge",
                    nameKn = "ಇಂಜಿನಿಯರ್ ಬ್ಯಾಡ್ಜ್",
                    description = "You learned about Visvesvaraya",
                    descriptionKn = "ವಿಶ್ವೇಶ್ವರಯ್ಯರ ಬಗ್ಗೆ ಕಲಿತಿರಿ",
                    icon = "🔧",
                    rarity = BadgeRarity.LEGENDARY
                ),

                HeroBadge(
                    id = "badge_kanaka",
                    name = "Bhakti Badge",
                    nameKn = "ಭಕ್ತಿ ಬ್ಯಾಡ್ಜ್",
                    description = "You learned about Kanaka Dasa",
                    descriptionKn = "ಕನಕ ದಾಸರ ಬಗ್ಗೆ ಕಲಿತಿರಿ",
                    icon = "🎶",
                    rarity = BadgeRarity.EPIC
                ),

                HeroBadge(
                    id = "badge_madhva",
                    name = "Wisdom Badge",
                    nameKn = "ಜ್ಞಾನ ಬ್ಯಾಡ್ಜ್",
                    description = "You learned about Madhvacharya",
                    descriptionKn = "ಮಧ್ವಾಚಾರ್ಯರ ಬಗ್ಗೆ ಕಲಿತಿರಿ",
                    icon = "📖",
                    rarity = BadgeRarity.EPIC
                ),

                HeroBadge(
                    id = "badge_abbakka",
                    name = "Sea Warrior Badge",
                    nameKn = "ಸಮುದ್ರ ವೀರ ಬ್ಯಾಡ್ಜ್",
                    description = "You learned about Rani Abbakka",
                    descriptionKn = "ರಾಣಿ ಅಬ್ಬಕ್ಕರ ಬಗ್ಗೆ ಕಲಿತಿರಿ",
                    icon = "⚓",
                    rarity = BadgeRarity.LEGENDARY
                ),

                HeroBadge(
                    id = "badge_krishnadevaraya",
                    name = "Empire Badge",
                    nameKn = "ಸಾಮ್ರಾಜ್ಯ ಬ್ಯಾಡ್ಜ್",
                    description = "You learned about Krishnadevaraya",
                    descriptionKn = "ಕೃಷ್ಣದೇವರಾಯರ ಬಗ್ಗೆ ಕಲಿತಿರಿ",
                    icon = "🏰",
                    rarity = BadgeRarity.LEGENDARY
                ),

                HeroBadge(
                    id = "badge_allama",
                    name = "Saint Badge",
                    nameKn = "ಸಂತ ಬ್ಯಾಡ್ಜ್",
                    description = "You learned about Allama Prabhu",
                    descriptionKn = "ಅಲ್ಲಮ ಪ್ರಭುಗಳ ಬಗ್ಗೆ ಕಲಿತಿರಿ",
                    icon = "✨",
                    rarity = BadgeRarity.EPIC
                )
            )

            val earned =
                viewModel.getEarnedBadges(
                    userId
                ).first()

            val earnedIds =
                earned.map {
                    it.badgeId
                }.toSet()

            binding.tvBadgeCount.text =
                "${earnedIds.size}/${allBadges.size} Earned"

            badgeAdapter =
                BadgeAdapter(
                    allBadges,
                    earnedIds,
                    isKn
                )

            binding.rvBadges.layoutManager =
                GridLayoutManager(
                    requireContext(),
                    2
                )

            binding.rvBadges.adapter =
                badgeAdapter
        }
    }
}