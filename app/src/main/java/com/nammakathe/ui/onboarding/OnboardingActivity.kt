package com.nammakathe.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.nammakathe.databinding.ActivityOnboardingBinding
import com.nammakathe.ui.auth.AuthActivity
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    private val viewModel: OnboardingViewModel by viewModels()

    private val pages = listOf(
        OnboardingPage(
            emoji = "🗺️",
            title = "Discover Local Heroes",
            titleKn = "ಸ್ಥಳೀಯ ವೀರರನ್ನು ಅನ್ವೇಷಿಸಿ",
            description = "Explore the brave warriors, poets, and reformers from every district of Karnataka — your own land's legends!",
            descriptionKn = "ಕರ್ನಾಟಕದ ಪ್ರತಿ ಜಿಲ್ಲೆಯ ಧೈರ್ಯಶಾಲಿ ಯೋಧರು, ಕವಿಗಳು ಮತ್ತು ಸುಧಾರಕರನ್ನು ಅನ್ವೇಷಿಸಿ!",
            bgStart = "#FF6B35",
            bgEnd = "#F7C59F"
        ),
        OnboardingPage(
            emoji = "📖",
            title = "Read Swipe Stories",
            titleKn = "ಸ್ವೈಪ್ ಕಥೆಗಳನ್ನು ಓದಿ",
            description = "Beautiful illustrated stories in Kannada and English. Swipe through pages, listen with audio narration!",
            descriptionKn = "ಕನ್ನಡ ಮತ್ತು ಇಂಗ್ಲಿಷ್‌ನಲ್ಲಿ ಸುಂದರ ಕಥೆಗಳು. ಪುಟಗಳನ್ನು ಸ್ವೈಪ್ ಮಾಡಿ, ಧ್ವನಿ ನಿರೂಪಣೆ ಕೇಳಿ!",
            bgStart = "#2D6A4F",
            bgEnd = "#52B788"
        ),
        OnboardingPage(
            emoji = "🏅",
            title = "Earn Heritage Badges",
            titleKn = "ಪಾರಂಪರಿಕ ಬ್ಯಾಡ್ಜ್ ಗಳಿಸಿ",
            description = "Take quizzes, earn Heritage Badges, collect them all! Become a Karnataka Heritage Scholar!",
            descriptionKn = "ಪ್ರಶ್ನೋತ್ತರ ಆಡಿ, ಪಾರಂಪರಿಕ ಬ್ಯಾಡ್ಜ್ ಗಳಿಸಿ! ಕರ್ನಾಟಕ ಪರಂಪರೆ ವಿದ್ವಾಂಸ ಆಗಿ!",
            bgStart = "#7B2FBE",
            bgEnd = "#C77DFF"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewPager()
        setupClickListeners()
    }

    private fun setupViewPager() {
        val adapter = OnboardingAdapter(pages)
        binding.viewPager.adapter = adapter
        binding.dotsIndicator.attachTo(binding.viewPager)

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val isLast = position == pages.size - 1
                binding.btnNext.text = if (isLast) "Get Started!" else "Next →"
                binding.tvSkip.visibility = if (isLast) android.view.View.INVISIBLE else android.view.View.VISIBLE
            }
        })
    }

    private fun setupClickListeners() {
        binding.btnNext.setOnClickListener {
            val current = binding.viewPager.currentItem
            if (current < pages.size - 1) {
                binding.viewPager.currentItem = current + 1
            } else {
                finishOnboarding()
            }
        }
        binding.tvSkip.setOnClickListener { finishOnboarding() }
    }

    private fun finishOnboarding() {
        lifecycleScope.launch {
            viewModel.setOnboardingDone()
            startActivity(Intent(this@OnboardingActivity, AuthActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}
