package com.mbl.deneme_newsapi.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mbl.deneme_newsapi.data.model.Article
import com.mbl.deneme_newsapi.data.model.ArticleForNavigation
import com.mbl.deneme_newsapi.databinding.Card2Binding
import com.mbl.deneme_newsapi.databinding.ItemNewsBinding
import com.mbl.deneme_newsapi.ui.fragments.FavouritesFragmentDirections
import com.mbl.deneme_newsapi.ui.fragments.HeadlinesFragmentDirections
import com.mbl.deneme_newsapi.ui.fragments.ShowContentFragmentDirections
import com.mbl.deneme_newsapi.ui.viewmodel.NewViewModel

class AdaptersFavours(var mContext: Context, var viewModel: NewViewModel) :
    RecyclerView.Adapter<AdaptersFavours.AdapterFavViewHolder>() {

    inner class AdapterFavViewHolder(val itemBinding: ItemNewsBinding) :
        RecyclerView.ViewHolder(itemBinding.root)


    private val differCallback = object : DiffUtil.ItemCallback<ArticleForNavigation>() {

        override fun areItemsTheSame(
            oldItem: ArticleForNavigation,
            newItem: ArticleForNavigation
        ): Boolean {
            return oldItem.task_id == newItem.task_id &&
                    oldItem.articleDescription == newItem.articleDescription
        }

        override fun areContentsTheSame(
            oldItem: ArticleForNavigation,
            newItem: ArticleForNavigation
        ): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterFavViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdapterFavViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: AdapterFavViewHolder, position: Int) {
        val currentArticle = differ.currentList[position]

        val ImageUrl = currentArticle.articleUrloImage

        if (ImageUrl != null)
            Glide.with(mContext).load(ImageUrl).into(holder.itemBinding.articleImage)

        holder.itemBinding.apply {
            articleTitle.text = currentArticle.articleTitle
            articleDescription.text = currentArticle.articleDescription
            articleDateTime.text = currentArticle.articleDateTime
            articleSource.text = currentArticle.articleSource
        }


        holder.itemBinding.cardView.setOnClickListener {
            val articleURL = currentArticle.articleURL
            val direction =
                FavouritesFragmentDirections.actionFavouritesFragmentToShowContentFragment(
                    null,
                    articleURL
                )
            it.findNavController().navigate(direction)

        }

    }
}