package com.nammakathe.ui.badge

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.nammakathe.core.util.animateBounce
import com.nammakathe.core.util.toTimeString
import com.nammakathe.databinding.ActivityBadgeEarnedBinding
import com.nammakathe.ui.home.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BadgeEarnedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBadgeEarnedBinding
    private val viewModel: BadgeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBadgeEarnedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Disable lottie completely
        binding.lottieResult.cancelAnimation()
        binding.lottieResult.visibility = View.GONE

        val heroId = intent.getStringExtra("heroId") ?: ""
        val score = intent.getIntExtra("score", 0)
        val total = intent.getIntExtra("total", 3)
        val timeSec = intent.getIntExtra("timeSec", 0)

        val passed =
            score >= (total * 0.6f).toInt()

        lifecycleScope.launch {

            val isKn =
                viewModel.isKannada.first()

            val hero =
                viewModel.getHero(heroId)

            binding.tvHeroEmoji.text =
                hero?.emoji ?: "🏅"

            binding.tvHeroName.text =
                if (isKn)
                    hero?.nameKn
                else
                    hero?.name

            binding.tvScoreText.text =
                "$score / $total"

            binding.tvTimeText.text =
                timeSec.toTimeString()

            binding.tvStars.text =
                "⭐".repeat(score) +
                        "☆".repeat(total - score)

            if (passed) {

                binding.tvResultTitle.text =
                    if (isKn)
                        "🎉 ಶಾಭಾಷ್! ಬ್ಯಾಡ್ಜ್ ಗಳಿಸಿದ್ದೀರಿ!"
                    else
                        "🎉 Congratulations! Badge Earned!"

                binding.tvResultTitle.setTextColor(
                    Color.parseColor("#2D6A4F")
                )

                binding.tvBadgeEmoji.text =
                    "🏅"

                binding.tvBadgeEmoji.animateBounce()

                binding.tvBadgeName.text =
                    if (isKn)
                        "ಪಾರಂಪರಿಕ ಬ್ಯಾಡ್ಜ್"
                    else
                        "Heritage Badge"

            } else {

                binding.tvResultTitle.text =
                    if (isKn)
                        "📚 ಮತ್ತೊಮ್ಮೆ ಓದಿ ಪ್ರಯತ್ನಿಸಿ!"
                    else
                        "📚 Read again and try once more!"

                binding.tvResultTitle.setTextColor(
                    Color.parseColor("#E64A19")
                )

                binding.tvBadgeEmoji.text =
                    "📖"

                binding.tvBadgeName.text =
                    if (isKn)
                        "60% ಅಂಕ ಗಳಿಸಿ ಬ್ಯಾಡ್ಜ್ ಪಡೆಯಿರಿ"
                    else
                        "Score 60%+ to earn badge"
            }
        }

        binding.btnGoHome.setOnClickListener {

            val intent =
                Intent(
                    this,
                    MainActivity::class.java
                )

            intent.flags =
                Intent.FLAG_ACTIVITY_CLEAR_TOP

            startActivity(intent)

            finish()
        }

        binding.btnShareResult.setOnClickListener {

            shareResult(
                score,
                total
            )
        }
    }

    private fun shareResult(
        score: Int,
        total: Int
    ) {

        val text =
            "I scored $score/$total in Namma Kathey Hero Quiz! 🏅"

        val intent =
            Intent(
                Intent.ACTION_SEND
            )

        intent.type =
            "text/plain"

        intent.putExtra(
            Intent.EXTRA_TEXT,
            text
        )

        startActivity(
            Intent.createChooser(
                intent,
                "Share"
            )
        )
    }
}