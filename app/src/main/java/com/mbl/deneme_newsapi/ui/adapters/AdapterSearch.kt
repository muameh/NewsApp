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
import com.mbl.deneme_newsapi.ui.fragments.SearchFragmentDirections
import com.mbl.deneme_newsapi.ui.viewmodel.NewViewModel

class AdapterSearch(var mContext: Context, var viewModel: NewViewModel)
    : RecyclerView.Adapter<AdapterSearch.AdapterSearchViewHolder>() {

    inner class AdapterSearchViewHolder(val itemBinding: ItemNewsBinding)
        : RecyclerView.ViewHolder(itemBinding.root)


    private val differCallback = object : DiffUtil.ItemCallback<Article>() {

        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url== newItem.url &&
                    oldItem.title == newItem.title &&
                    oldItem.urlToImage == newItem.urlToImage &&
                    oldItem.description == newItem.description
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this,differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterSearchViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return AdapterSearchViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: AdapterSearchViewHolder, position: Int) {
        val currentArticle = differ.currentList[position]

        val ImageUrl = currentArticle.urlToImage

        if (ImageUrl!=null)
            Glide.with(mContext).load(ImageUrl).into(holder.itemBinding.articleImage)

        holder.itemBinding.apply {
            articleTitle.text = currentArticle.title
            articleDescription.text = currentArticle.description
            articleDateTime.text = currentArticle.publishedAt
            articleSource.text = currentArticle.source!!.name
        }

        holder.itemBinding.cardView.setOnClickListener {
            val articleURL : String = currentArticle.url
            var articleTitle : String  = currentArticle.title
            var articleDescription : String = currentArticle.description
            var articleDateTime : String = currentArticle.publishedAt
            var articleSource : String = currentArticle.source.name
            var articleUrloImage : String = currentArticle.urlToImage

            val navigationArticle = ArticleForNavigation(task_id = 0,
                articleURL = articleURL ?: "null",
                articleTitle = articleTitle ?: "null",
                articleDescription = articleDescription ?: "null",
                articleDateTime = articleDateTime ?: "null",
                articleSource = articleSource ?: "null",
                articleUrloImage = articleUrloImage ?: "null"
            )

            val direction = SearchFragmentDirections.actionSearchFragmentToShowContentFragment(navigationArticle,null)
            Navigation.findNavController(it).navigate(direction)
            //it.findNavController().navigate(direction)


        }



    }
}