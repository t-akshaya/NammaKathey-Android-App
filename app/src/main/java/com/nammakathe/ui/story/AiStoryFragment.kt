package com.nammakathe.ui.story

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.nammakathe.core.domain.model.AgeGroup
import com.nammakathe.core.domain.model.Hero
import com.nammakathe.databinding.FragmentAiStoryBinding
import com.nammakathe.ui.home.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AiStoryFragment : Fragment() {

    private var _binding: FragmentAiStoryBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()
    private var hero: Hero? = null

    companion object {
        fun newInstance(heroId: String) = AiStoryFragment().apply {
            arguments = Bundle().also { it.putString("heroId", heroId) }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAiStoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val heroId = arguments?.getString("heroId") ?: return
        hero = viewModel.getHeroById(heroId)
        setupUI()
        setupAgeGroupButtons()
    }

    private fun setupUI() {
        val h = hero ?: return
        lifecycleScope.launch {
            val isKn = viewModel.isKannada.first()
            binding.tvAiHeroName.text = if (isKn) h.nameKn else h.name
            binding.tvAiHeroEmoji.text = h.emoji
            binding.tvAiPromptLabel.text = if (isKn)
                "ವಯಸ್ಸಿನ ಗುಂಪು ಆರಿಸಿ — AI ಕಥೆ ರಚಿಸುತ್ತದೆ!"
            else
                "Choose your age group — AI generates the story!"
        }
    }

    private fun setupAgeGroupButtons() {
        binding.btnAge58.setOnClickListener { generateStory(AgeGroup.KIDS) }
        binding.btnAge912.setOnClickListener { generateStory(AgeGroup.TWEENS) }
        binding.btnAge1316.setOnClickListener { generateStory(AgeGroup.TEENS) }
    }

    private fun generateStory(ageGroup: AgeGroup) {
        val h = hero ?: return
        lifecycleScope.launch {
            val isKn = viewModel.isKannada.first()
            binding.progressAi.visibility = View.VISIBLE
            binding.tvAiStoryOutput.text = ""
            binding.cardAiOutput.visibility = View.GONE
            try {
                val story = viewModel.generateAiStory(h, ageGroup, isKn)
                binding.tvAiStoryOutput.text = story
                binding.cardAiOutput.visibility = View.VISIBLE
            } catch (e: Exception) {
                binding.tvAiStoryOutput.text = if (isKn)
                    "AI ಕಥೆ ರಚಿಸಲು ಸಾಧ್ಯವಾಗಲಿಲ್ಲ. ಅಂತರ್ಜಾಲ ಸಂಪರ್ಕ ಪರಿಶೀಲಿಸಿ."
                else
                    "Could not generate AI story. Please check your internet connection."
                binding.cardAiOutput.visibility = View.VISIBLE
            } finally {
                binding.progressAi.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
