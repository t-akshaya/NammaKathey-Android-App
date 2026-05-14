package com.nammakathe.ui.profile

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.nammakathe.R
import com.nammakathe.databinding.FragmentProfileBinding
import com.nammakathe.ui.auth.AuthActivity
import com.nammakathe.ui.home.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    private val avatarEmojis =
        listOf(
            "🦁",   // avatar1
            "🦄",   // avatar2
            "🐘",   // avatar3
            "🌍"    // avatar4
        )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding =
            FragmentProfileBinding.inflate(
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

        loadProfile()
        setupButtons()
    }

    private fun loadProfile() {

        lifecycleScope.launch {

            val userId =
                viewModel.userId.first()

            val name =
                viewModel.userName.first()

            val isKn =
                viewModel.isKannada.first()

            binding.tvProfileName.text = name

            binding.tvProfileTitle.text =
                if (isKn)
                    "ಪರಂಪರೆ ಅನ್ವೇಷಕ"
                else
                    "Heritage Explorer"

            val avatar =
                viewModel.avatarIndex.first()

            binding.tvAvatarEmoji.text =
                avatarEmojis[avatar]

            viewModel.getCompletedCount(
                userId
            ).collect { count ->

                binding.tvStoriesCompleted.text =
                    count.toString()
            }
        }

        lifecycleScope.launch {

            val userId =
                viewModel.userId.first()

            viewModel.getBadgeCount(
                userId
            ).collect { count ->

                binding.tvBadgesEarned.text =
                    count.toString()
            }
        }

        lifecycleScope.launch {

            val userId =
                viewModel.userId.first()

            viewModel.getAverageScore(
                userId
            ).collect { avg ->

                binding.tvAvgScore.text =
                    "${avg?.toInt() ?: 0}%"
            }
        }
    }

    private fun setupButtons() {

        binding.btnParentDashboard
            .setOnClickListener {

                lifecycleScope.launch {

                    val savedPin =
                        viewModel.getSavedParentPin()

                    if (savedPin == "1234") {

                        showCreatePinDialog()

                    } else {

                        showVerifyPinDialog()
                    }
                }
            }

        binding.switchDarkMode
            .setOnCheckedChangeListener { _, checked ->

                lifecycleScope.launch {
                    viewModel.setDarkMode(
                        checked
                    )
                }
            }

        binding.switchSound
            .setOnCheckedChangeListener { _, checked ->

                lifecycleScope.launch {
                    viewModel.setSoundEnabled(
                        checked
                    )
                }
            }

        binding.btnLogout
            .setOnClickListener {

                lifecycleScope.launch {

                    viewModel.logout()

                    startActivity(
                        Intent(
                            requireContext(),
                            AuthActivity::class.java
                        )
                    )

                    requireActivity().finish()
                }
            }
    }

    private fun showCreatePinDialog() {

        val input =
            EditText(requireContext())

        input.inputType =
            InputType.TYPE_CLASS_NUMBER

        input.hint =
            "Create 4-digit PIN"

        AlertDialog.Builder(
            requireContext()
        )
            .setTitle(
                "Set Parent PIN"
            )
            .setView(
                input
            )

            .setPositiveButton(
                "Save"
            ) { _, _ ->

                val pin =
                    input.text.toString()

                if (pin.length != 4) {

                    Toast.makeText(
                        requireContext(),
                        "PIN must be 4 digits",
                        Toast.LENGTH_SHORT
                    ).show()

                    return@setPositiveButton
                }

                viewModel.saveParentPin(
                    pin
                )

                Toast.makeText(
                    requireContext(),
                    "PIN Saved",
                    Toast.LENGTH_SHORT
                ).show()
            }

            .show()
    }

    private fun showVerifyPinDialog() {

        val input =
            EditText(requireContext())

        input.inputType =
            InputType.TYPE_CLASS_NUMBER

        input.hint =
            "Enter Parent PIN"

        AlertDialog.Builder(
            requireContext()
        )
            .setTitle(
                "Parent Verification"
            )
            .setView(
                input
            )

            .setPositiveButton(
                "Verify"
            ) { _, _ ->

                val enteredPin =
                    input.text.toString()

                lifecycleScope.launch {

                    val isCorrect =
                        viewModel.verifyParentPin(
                            enteredPin
                        )

                    if (isCorrect) {

                        findNavController()
                            .navigate(
                                R.id.parentDashboardFragment
                            )

                    } else {

                        Toast.makeText(
                            requireContext(),
                            "Wrong PIN",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            .show()
    }

    override fun onDestroyView() {

        super.onDestroyView()

        _binding = null
    }
}