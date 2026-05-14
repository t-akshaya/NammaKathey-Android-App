package com.nammakathe.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nammakathe.core.domain.model.HeroBadge
import com.nammakathe.databinding.ItemBadgeBinding

class BadgeAdapter(
    private val badges: List<HeroBadge>,
    private val earnedIds: Set<String>,
    private val isKannada: Boolean
) : RecyclerView.Adapter<BadgeAdapter.BadgeViewHolder>() {

    inner class BadgeViewHolder(
        private val binding: ItemBadgeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(badge: HeroBadge) {

            val isEarned =
                badge.id in earnedIds

            binding.tvBadgeEmoji.text =
                badge.icon

            binding.tvBadgeName.text =
                if (isKannada)
                    badge.nameKn
                else
                    badge.name

            binding.tvBadgeDesc.text =
                if (isKannada)
                    badge.descriptionKn
                else
                    badge.description

            binding.tvBadgeRarity.text =
                badge.rarity.label

            binding.tvBadgeRarity.setTextColor(
                Color.parseColor("#F57C00")
            )

            binding.tvBadgeStatus.text =
                if (isEarned)
                    "✅ Earned"
                else
                    "🔒 Not earned"

            binding.root.alpha =
                if (isEarned) 1f else 0.4f
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BadgeViewHolder {

        return BadgeViewHolder(
            ItemBadgeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: BadgeViewHolder,
        position: Int
    ) {
        holder.bind(
            badges[position]
        )
    }

    override fun getItemCount(): Int =
        badges.size
}