package com.nammakathe.ui.quiz

import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.nammakathe.core.domain.model.Hero
import com.nammakathe.core.domain.model.QuizQuestion
import com.nammakathe.core.util.animateBounce
import com.nammakathe.core.util.animateShake
import com.nammakathe.core.util.hide
import com.nammakathe.core.util.show
import com.nammakathe.databinding.ActivityQuizBinding
import com.nammakathe.ui.badge.BadgeEarnedActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class QuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizBinding
    private val viewModel: QuizViewModel by viewModels()
    private var hero: Hero? = null
    private var isKn = false
    private var currentIndex = 0
    private var score = 0
    private var totalTimeSec = 0
    private var answered = false
    private var countDownTimer: CountDownTimer? = null
    private val TIME_PER_Q = 20L // seconds

    private val optionViews get() = listOf(binding.btnOpt1, binding.btnOpt2, binding.btnOpt3, binding.btnOpt4)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val heroId = intent.getStringExtra("heroId") ?: run { finish(); return }
        lifecycleScope.launch {
            isKn = viewModel.isKannada.first()
            hero = viewModel.getHero(heroId)
            hero?.let { startQuiz(it) } ?: finish()
        }
        binding.btnBack.setOnClickListener { finish() }
    }

    private fun startQuiz(h: Hero) {
        binding.tvHeroEmoji.text = h.emoji
        binding.tvHeroName.text = if (isKn) h.nameKn else h.name
        showQuestion(h.quiz[currentIndex])
    }

    private fun showQuestion(q: QuizQuestion) {
        answered = false
        val h = hero ?: return
        val options = if (isKn) q.optionsKn else q.options

        binding.tvProgress.text = "Question ${currentIndex + 1} of ${h.quiz.size}"
        binding.tvQuestion.text = if (isKn) q.questionKn else q.question
        binding.progressBar.progress = ((currentIndex * 100) / h.quiz.size)
        binding.tvScore.text = "⭐ $score"

        optionViews.forEachIndexed { i, btn ->
            btn.text = options.getOrElse(i) { "" }
            btn.setBackgroundColor(Color.parseColor("#FFFFFF"))
            btn.setTextColor(Color.parseColor("#212121"))
            btn.isEnabled = true
            btn.setOnClickListener { onOptionSelected(i, q.correctIndex) }
        }

        startTimer()

        // Animate question in
        binding.cardQuestion.alpha = 0f
        binding.cardQuestion.animate().alpha(1f).setDuration(300).start()
    }

    private fun startTimer() {

        countDownTimer?.cancel()

        binding.tvTimer.text =
            "${TIME_PER_Q}s"

        countDownTimer =
            object : CountDownTimer(
                TIME_PER_Q * 1000,
                1000
            ) {

                override fun onTick(
                    millisUntilFinished: Long
                ) {

                    val sec =
                        millisUntilFinished / 1000

                    binding.tvTimer.text =
                        "${sec}s"

                    binding.timerProgress.progress =
                        (
                                millisUntilFinished * 100
                                        / (TIME_PER_Q * 1000)
                                ).toInt()

                    binding.tvTimer.setTextColor(

                        if (sec <= 5)
                            Color.RED
                        else
                            Color.parseColor(
                                "#2D6A4F"
                            )
                    )
                }

                override fun onFinish() {

                    totalTimeSec +=
                        TIME_PER_Q.toInt()

                    if (!answered) {

                        onOptionSelected(
                            -1,
                            hero?.quiz
                                ?.get(
                                    currentIndex
                                )
                                ?.correctIndex
                                ?: 0
                        )
                    }
                }
            }

        countDownTimer?.start()
    }

    private fun onOptionSelected(selected: Int, correct: Int) {
        if (answered) return
        answered = true
        countDownTimer?.cancel()

        val isCorrect = selected == correct
        if (isCorrect) score++

        optionViews.forEachIndexed { i, btn ->
            btn.isEnabled = false
            when {
                i == correct -> {
                    btn.setBackgroundColor(Color.parseColor("#388E3C"))
                    btn.setTextColor(Color.WHITE)
                    if (i == selected) btn.animateBounce()
                }
                i == selected -> {
                    btn.setBackgroundColor(Color.parseColor("#D32F2F"))
                    btn.setTextColor(Color.WHITE)
                    btn.animateShake()
                }
            }
        }

        // Show explanation
        binding.tvExplanation.show()
        val q = hero?.quiz?.getOrNull(currentIndex)
        binding.tvExplanation.text = "💡 ${if (isKn) q?.explanationKn else q?.explanation}"

        // Show feedback lottie
        if (isCorrect) {
            binding.lottieCorrect.show(); binding.lottieCorrect.playAnimation()
        } else {
            binding.lottieWrong.show(); binding.lottieWrong.playAnimation()
        }

        lifecycleScope.launch {
            delay(1800)
            binding.lottieCorrect.hide(); binding.lottieWrong.hide()
            binding.tvExplanation.hide()
            val h = hero ?: return@launch
            currentIndex++
            if (currentIndex < h.quiz.size) {
                showQuestion(h.quiz[currentIndex])
            } else {
                finishQuiz(h)
            }
        }
    }

    private fun finishQuiz(h: Hero) {

        lifecycleScope.launch {

            val userId =
                viewModel.userId.first()

            viewModel.saveQuizResult(
                h.id,
                userId,
                score,
                h.quiz.size,
                totalTimeSec,
                h.badgeId
            )

            val intent =
                Intent(
                    this@QuizActivity,
                    BadgeEarnedActivity::class.java
                )

            intent.putExtra(
                "heroId",
                h.id
            )

            intent.putExtra(
                "score",
                score
            )

            intent.putExtra(
                "total",
                h.quiz.size
            )

            intent.putExtra(
                "timeSec",
                totalTimeSec
            )

            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(
                intent
            )
        }
    }

    override fun onDestroy() { countDownTimer?.cancel(); super.onDestroy() }
}
