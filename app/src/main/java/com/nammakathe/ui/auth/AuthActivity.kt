package com.nammakathe.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.nammakathe.core.domain.model.AgeGroup
import com.nammakathe.databinding.ActivityAuthBinding
import com.nammakathe.ui.home.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.UUID

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private val viewModel: AuthViewModel by viewModels()

    private var selectedAgeGroup = AgeGroup.TWEENS
    private var selectedAvatar = 0

    private var isLoginMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAgeGroupChips()
        setupAvatarSelection()
        setupClickListeners()
    }

    private fun setupAgeGroupChips() {

        binding.chipAge58.setOnClickListener {
            selectedAgeGroup = AgeGroup.KIDS
            updateAgeUI()
        }

        binding.chipAge912.setOnClickListener {
            selectedAgeGroup = AgeGroup.TWEENS
            updateAgeUI()
        }

        binding.chipAge1316.setOnClickListener {
            selectedAgeGroup = AgeGroup.TEENS
            updateAgeUI()
        }

        updateAgeUI()
    }

    private fun updateAgeUI() {

        binding.chipAge58.isChecked = false
        binding.chipAge912.isChecked = false
        binding.chipAge1316.isChecked = false

        when (selectedAgeGroup) {
            AgeGroup.KIDS -> binding.chipAge58.isChecked = true
            AgeGroup.TWEENS -> binding.chipAge912.isChecked = true
            AgeGroup.TEENS -> binding.chipAge1316.isChecked = true
        }
    }

    private fun setupAvatarSelection() {

        val avatars = listOf(
            binding.avatar1,
            binding.avatar2,
            binding.avatar3,
            binding.avatar4
        )

        avatars.forEachIndexed { index, avatar ->

            avatar.setOnClickListener {

                selectedAvatar = index

                avatars.forEach {
                    it.alpha = 0.4f
                    it.scaleX = 1f
                    it.scaleY = 1f
                }

                avatar.alpha = 1f
                avatar.scaleX = 1.15f
                avatar.scaleY = 1.15f
            }
        }

        binding.avatar1.alpha = 1f
    }

    private fun setupClickListeners() {

        // MAIN BUTTON
        binding.btnStartJourney.setOnClickListener {

            if (isLoginMode) {

                performLogin()

            } else {

                performRegister()
            }
        }

        // LOGIN TEXT
        binding.tvLogin.setOnClickListener {

            isLoginMode = true

            binding.etName.visibility = View.GONE
            binding.etConfirmPassword.visibility = View.GONE

            binding.btnStartJourney.text = "LOGIN"
        }

        // GUEST
        binding.tvGuestMode.setOnClickListener {

            saveAndProceed(
                name = "Young Explorer",
                email = "guest@namma.com",
                password = "guest123",
                isGuest = true
            )
        }
    }

    private fun performRegister() {

        val name = binding.etName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        val confirmPassword =
            binding.etConfirmPassword.text.toString().trim()

        if (name.isEmpty()) {
            binding.etName.error = "Enter name"
            return
        }

        if (email.isEmpty()) {
            binding.etEmail.error = "Enter email"
            return
        }

        if (password.isEmpty()) {
            binding.etPassword.error = "Enter password"
            return
        }

        if (password != confirmPassword) {
            binding.etConfirmPassword.error =
                "Passwords do not match"
            return
        }

        saveAndProceed(
            name = name,
            email = email,
            password = password,
            isGuest = false
        )
    }

    private fun performLogin() {

        val email =
            binding.etEmail.text.toString().trim()

        val password =
            binding.etPassword.text.toString().trim()

        if (email.isEmpty()) {
            binding.etEmail.error = "Enter email"
            return
        }

        if (password.isEmpty()) {
            binding.etPassword.error = "Enter password"
            return
        }

        viewModel.loginUser(

            email = email,
            password = password,

            onSuccess = {

                Toast.makeText(
                    this,
                    "Login Successful",
                    Toast.LENGTH_SHORT
                ).show()

                startActivity(
                    Intent(
                        this,
                        MainActivity::class.java
                    )
                )

                finish()
            },

            onFailure = {

                Toast.makeText(
                    this,
                    "Invalid credentials",
                    Toast.LENGTH_SHORT
                ).show()
            }
        )
    }

    private fun saveAndProceed(
        name: String,
        email: String,
        password: String,
        isGuest: Boolean
    ) {

        lifecycleScope.launch {

            val userId =
                "user_${UUID.randomUUID().toString().take(8)}"

            viewModel.saveUser(
                userId = userId,
                name = name,
                email = email,
                password = password,
                ageGroup = selectedAgeGroup.name,
                isGuest = isGuest,
                avatarIndex = selectedAvatar
            )

            startActivity(
                Intent(
                    this@AuthActivity,
                    MainActivity::class.java
                )
            )

            finish()
        }
    }
}