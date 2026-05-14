package com.nammakathe.ui.onboarding

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nammakathe.databinding.ItemOnboardingPageBinding

class OnboardingAdapter(private val pages: List<OnboardingPage>) :
    RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>() {

    inner class OnboardingViewHolder(private val binding: ItemOnboardingPageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(page: OnboardingPage) {
            binding.tvEmoji.text = page.emoji
            binding.tvTitle.text = page.title
            binding.tvTitleKn.text = page.titleKn
            binding.tvDescription.text = page.description
            binding.tvDescriptionKn.text = page.descriptionKn
            try {
                val gradient = GradientDrawable(
                    GradientDrawable.Orientation.TL_BR,
                    intArrayOf(Color.parseColor(page.bgStart), Color.parseColor(page.bgEnd))
                )
                binding.root.background = gradient
            } catch (e: Exception) { /* fallback */ }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        OnboardingViewHolder(ItemOnboardingPageBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: OnboardingViewHolder, position: Int) = holder.bind(pages[position])
    override fun getItemCount() = pages.size
}
