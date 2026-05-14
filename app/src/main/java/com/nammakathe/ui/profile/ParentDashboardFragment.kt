package com.nammakathe.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.nammakathe.databinding.FragmentParentDashboardBinding
import com.nammakathe.ui.home.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ParentDashboardFragment : Fragment() {

    private var _binding: FragmentParentDashboardBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel
            by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding =
            FragmentParentDashboardBinding.inflate(
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

        loadDashboard()

        binding.btnBack
            .setOnClickListener {

                parentFragmentManager
                    .popBackStack()
            }
    }

    private fun loadDashboard() {

        lifecycleScope.launch {

            val userId =
                viewModel.userId.first()

            val name =
                viewModel.userName.first()

            binding.tvChildName.text =
                name

            binding.tvDashboardTitle.text =
                "📊 Parent Dashboard\n$name's Learning Progress"

            // Stories
            viewModel.getCompletedCount(userId).collect { count ->

                binding.tvStoriesValue.text =
                    count.toString()

                binding.progressStories.progress =
                    (count * 10)
                        .coerceAtMost(100)

                val streak =
                    count.coerceAtMost(7)

                binding.tvLearningStreak.text =
                    "🔥 $streak Day Streak"
            }
        }

        lifecycleScope.launch {

            val userId =
                viewModel.userId.first()

            viewModel.getBadgeCount(
                userId
            ).collect { count ->

                binding.tvBadgesValue.text =
                    count.toString()
            }
        }

        lifecycleScope.launch {

            val userId =
                viewModel.userId.first()

            viewModel.getAverageScore(
                userId
            ).collect { avg ->

                val score =
                    avg?.toInt() ?: 0

                binding.tvAvgScoreValue.text =
                    "$score%"

                binding.progressQuiz.progress =
                    score
            }
        }

        lifecycleScope.launch {

            val userId =
                viewModel.userId.first()

            viewModel.getQuizResults(
                userId
            ).collect { results ->

                binding.tvQuizzesTaken.text =
                    results.size.toString()

                if (results.isNotEmpty()) {

                    val recentScores =
                        results.take(5)
                            .joinToString(" | ") {

                                "${it.score}/${it.totalQuestions}"
                            }

                    binding.tvRecentScores.text =
                        "Recent: $recentScores"

                    setupChart(
                        results.map {
                            it.score.toFloat()
                        }
                    )
                }
            }
        }
    }

    private fun setupChart(
        scores: List<Float>
    ) {

        val entries =
            scores.mapIndexed { index, score ->

                BarEntry(
                    index.toFloat(),
                    score
                )
            }

        val dataSet =
            BarDataSet(
                entries,
                "Quiz Scores"
            )

        val barData =
            BarData(
                dataSet
            )

        binding.quizChart.data =
            barData

        binding.quizChart
            .description.isEnabled =
            false

        binding.quizChart
            .invalidate()
    }

    override fun onDestroyView() {

        super.onDestroyView()

        _binding = null
    }
}