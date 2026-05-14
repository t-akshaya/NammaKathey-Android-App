package com.nammakathe.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nammakathe.core.domain.model.StoryPage
import com.nammakathe.core.util.setGradientBackground
import com.nammakathe.databinding.ItemStoryPageBinding

class StoryPagerAdapter(
    private val pages: List<StoryPage>,
    private var isKannada: Boolean
) : RecyclerView.Adapter<StoryPagerAdapter.StoryViewHolder>() {

    fun setLanguage(kn: Boolean) { isKannada = kn; notifyDataSetChanged() }

    inner class StoryViewHolder(private val binding: ItemStoryPageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(page: StoryPage) {
            binding.tvPageNumber.text = "Page ${page.pageNumber}"
            binding.tvChapterTitle.text = if (isKannada) page.titleKn else page.title
            binding.tvStoryContent.text = if (isKannada) page.contentKn else page.content
            binding.tvIllustration.text = page.illustrationEmoji
            binding.pageRoot.setGradientBackground(page.bgGradientStart, page.bgGradientEnd)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        StoryViewHolder(ItemStoryPageBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) = holder.bind(pages[position])
    override fun getItemCount() = pages.size
}
