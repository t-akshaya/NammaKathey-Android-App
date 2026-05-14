package com.nammakathe.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nammakathe.core.domain.model.Hero
import com.nammakathe.core.util.animatePulse
import com.nammakathe.core.util.setGradientBackground
import com.nammakathe.databinding.ItemHeroCardBinding

class HeroCardAdapter(
    private val onHeroClick: (Hero) -> Unit,
    private val onFavoriteClick: (Hero, Boolean) -> Unit
) : RecyclerView.Adapter<HeroCardAdapter.HeroViewHolder>() {

    private val items = mutableListOf<Hero>()
    private var isKannada = false

    fun submitList(
        heroes: List<Hero>,
        kn: Boolean
    ) {

        isKannada = kn

        items.clear()

        items.addAll(
            heroes
        )

        notifyDataSetChanged()
    }

    inner class HeroViewHolder(
        private val binding: ItemHeroCardBinding
    ) : RecyclerView.ViewHolder(
        binding.root
    ) {

        fun bind(
            hero: Hero
        ) {

            val imageRes =
                binding.root.context
                    .resources
                    .getIdentifier(

                        hero.id,

                        "drawable",

                        binding.root.context.packageName
                    )

            if (imageRes != 0) {

                binding.ivHeroImage
                    .setImageResource(
                        imageRes
                    )

                binding.ivHeroImage.visibility =
                    android.view.View.VISIBLE

                binding.tvHeroEmoji.visibility =
                    android.view.View.GONE

            } else {

                binding.tvHeroEmoji.text =
                    hero.emoji

                binding.tvHeroEmoji.visibility =
                    android.view.View.VISIBLE

                binding.ivHeroImage.visibility =
                    android.view.View.GONE
            }

            binding.tvHeroName.text =
                if (isKannada)
                    hero.nameKn
                else
                    hero.name

            binding.tvHeroTitle.text =
                if (isKannada)
                    hero.titleKn
                else
                    hero.title

            binding.tvHeroEra.text =
                hero.era

            binding.tvCategoryTag.text =
                if (isKannada)
                    hero.category.labelKn
                else
                    hero.category.label

            binding.tvDistrictTag.text =
                "📍 ${hero.districtName}"

            binding.tvCompletedBadge.visibility =
                if (hero.isCompleted)
                    android.view.View.VISIBLE
                else
                    android.view.View.GONE

            binding.btnFavorite.text =
                if (hero.isFavorite)
                    "❤️"
                else
                    "🤍"

            binding.cardRoot
                .setGradientBackground(
                    "#FFF8F0",
                    "#FFF0E0"
                )

            binding.btnFavorite
                .setOnClickListener {

                    it.animatePulse()

                    onFavoriteClick(
                        hero,
                        !hero.isFavorite
                    )
                }

            binding.cardRoot
                .setOnClickListener {

                    onHeroClick(
                        hero
                    )
                }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) =
        HeroViewHolder(
            ItemHeroCardBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent,
                false
            )
        )

    override fun onBindViewHolder(
        holder: HeroViewHolder,
        position: Int
    ) {

        holder.bind(
            items[position]
        )
    }

    override fun getItemCount() =
        items.size
}