package com.nammakathe.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nammakathe.core.domain.model.District
import com.nammakathe.core.util.setGradientBackground
import com.nammakathe.databinding.ItemDistrictCardBinding

class DistrictAdapter(
    private val onDistrictClick: (District) -> Unit
) : RecyclerView.Adapter<DistrictAdapter.DistrictViewHolder>() {

    private val items = mutableListOf<District>()
    private var isKannada = false

    fun submitList(districts: List<District>, kn: Boolean) {
        isKannada = kn; items.clear(); items.addAll(districts); notifyDataSetChanged()
    }

    inner class DistrictViewHolder(private val binding: ItemDistrictCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(district: District) {
            binding.tvDistrictName.text = if (isKannada) district.nameKn else district.name
            binding.tvRegion.text = district.region
            binding.tvHeroCount.text = "${district.heroCount} Heroes"
            binding.cardRoot.setGradientBackground(district.primaryColor, district.secondaryColor)
            binding.tvDistrictName.setTextColor(Color.WHITE)
            binding.tvRegion.setTextColor(Color.parseColor("#FFE0CC"))
            binding.tvHeroCount.setTextColor(Color.WHITE)
            binding.cardRoot.setOnClickListener { onDistrictClick(district) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DistrictViewHolder(ItemDistrictCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: DistrictViewHolder, position: Int) = holder.bind(items[position])
    override fun getItemCount() = items.size
}
