package com.nammakathe.ui.story

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.nammakathe.R
import com.nammakathe.core.domain.model.Hero
import com.nammakathe.core.util.animatePulse
import com.nammakathe.core.util.hide
import com.nammakathe.core.util.show
import com.nammakathe.databinding.ActivityStoryBinding
import com.nammakathe.ui.adapter.StoryPagerAdapter
import com.nammakathe.ui.quiz.QuizActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.Locale

@AndroidEntryPoint
class StoryActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var binding: ActivityStoryBinding
    private val viewModel: StoryViewModel by viewModels()
    private lateinit var storyAdapter: StoryPagerAdapter
    private var tts: TextToSpeech? = null
    private var isTtsReady = false
    private var isSpeaking = false
    private var hero: Hero? = null
    private var isKn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val heroId = intent.getStringExtra("heroId") ?: run { finish(); return }
        tts = TextToSpeech(this, this)

        lifecycleScope.launch {
            isKn = viewModel.isKannada.first()
            hero = viewModel.getHero(heroId)
            hero?.let { setupStory(it) } ?: finish()
        }
    }

    private fun setupStory(h: Hero) {
        // Header
        binding.tvHeroName.text = if (isKn) h.nameKn else h.name
        binding.tvHeroTitle.text = if (isKn) h.titleKn else h.title
        binding.tvHeroEmoji.text = h.emoji
        binding.tvCategoryChip.text = if (isKn) h.category.labelKn else h.category.label

        // ViewPager
        storyAdapter = StoryPagerAdapter(h.pages, isKn)
        binding.storyViewPager.adapter = storyAdapter
        binding.dotsIndicator.attachTo(binding.storyViewPager)

        binding.storyViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updateNavigationButtons(position, h.pages.size)
                updatePageProgress(position, h.pages.size)
                stopSpeaking()
                lifecycleScope.launch {
                    val uid = viewModel.userId.first()
                    viewModel.saveProgress(h.id, uid, position, position == h.pages.size - 1)
                }
            }
        })

        // Navigation buttons
        binding.btnPrevPage.setOnClickListener {
            val cur = binding.storyViewPager.currentItem
            if (cur > 0) binding.storyViewPager.currentItem = cur - 1
        }
        binding.btnNextPage.setOnClickListener {
            val cur = binding.storyViewPager.currentItem
            if (cur < h.pages.size - 1) {
                binding.storyViewPager.currentItem = cur + 1
            } else {
                launchQuiz()
            }
        }

        // TTS button
        binding.btnListen.setOnClickListener {
            if (isSpeaking) stopSpeaking() else speakCurrentPage()
        }

        // Language toggle
        binding.btnToggleLang.text = if (isKn) "EN" else "ಕನ್ನಡ"
        binding.btnToggleLang.setOnClickListener {
            isKn = !isKn
            storyAdapter.setLanguage(isKn)
            binding.tvHeroName.text = if (isKn) h.nameKn else h.name
            binding.btnToggleLang.text = if (isKn) "EN" else "ಕನ್ನಡ"
            lifecycleScope.launch { viewModel.setKannada(isKn) }
        }

        // Quiz FAB
        binding.fabQuiz.setOnClickListener { launchQuiz() }

        // Back
        binding.btnBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }

        // Favorite
        binding.btnFavorite.setOnClickListener {
            binding.btnFavorite.animatePulse()
            lifecycleScope.launch {
                val uid = viewModel.userId.first()
                viewModel.toggleFavorite(h.id, uid)
            }
        }

        updateNavigationButtons(0, h.pages.size)
        updatePageProgress(0, h.pages.size)
    }

    private fun updateNavigationButtons(pos: Int, total: Int) {
        binding.btnPrevPage.isEnabled = pos > 0
        binding.btnPrevPage.alpha = if (pos > 0) 1f else 0.4f
        binding.btnNextPage.text = if (pos == total - 1) "🎯 Quiz!" else "Next →"
    }

    private fun updatePageProgress(pos: Int, total: Int) {
        binding.tvPageCounter.text = "${pos + 1} / $total"
        binding.progressStory.progress = ((pos + 1) * 100 / total)
    }

    private fun speakCurrentPage() {
        if (!isTtsReady) return
        val pos = binding.storyViewPager.currentItem
        val page = hero?.pages?.getOrNull(pos) ?: return
        val text = if (isKn) page.contentKn else page.content
        val locale = if (isKn) Locale("kn", "IN") else Locale.ENGLISH
        tts?.language = locale
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "story_$pos")
        isSpeaking = true
        binding.btnListen.text = "⏸ Stop"
    }

    private fun stopSpeaking() {
        tts?.stop()
        isSpeaking = false
        binding.btnListen.text = "🔊 Listen"
    }

    private fun launchQuiz() {
        val h = hero ?: return
        Intent(this, QuizActivity::class.java).apply {
            putExtra("heroId", h.id)
            startActivity(this)
        }
    }

    override fun onInit(status: Int) { isTtsReady = status == TextToSpeech.SUCCESS }

    override fun onDestroy() { tts?.shutdown(); super.onDestroy() }
}
