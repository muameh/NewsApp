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
import com.mbl.deneme_newsapi.ui.fragments.HeadlinesFragmentDirections
import com.mbl.deneme_newsapi.ui.viewmodel.NewViewModel


class AdapterHeadlines(var mContext: Context, var viewModel: NewViewModel)
    : RecyclerView.Adapter<AdapterHeadlines.Adapter1ViewHolder>() {

    inner class Adapter1ViewHolder(val itemBinding: ItemNewsBinding)
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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter1ViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return Adapter1ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: Adapter1ViewHolder, position: Int) {
        //val currentArticle = differ.currentList[position]

        val filteredList = differ.currentList.filter { it.title != "[Removed]" }
        val currentArticle = filteredList[position]


        val ImageUrl = currentArticle.urlToImage

        if (ImageUrl != null)
            Glide.with(mContext).load(ImageUrl).into(holder.itemBinding.articleImage)

        holder.itemBinding.apply {
            articleTitle.text = currentArticle.title
            articleDescription.text = currentArticle.description
            articleDateTime.text = takeUntilT(currentArticle.publishedAt)
            articleSource.text = currentArticle.source!!.name
        }

        holder.itemBinding.cardView.setOnClickListener {
            val articleURL: String = currentArticle.url
            var articleTitle: String = currentArticle.title
            var articleDescription: String = currentArticle.description
            var articleDateTime: String = currentArticle.publishedAt
            var articleSource: String = currentArticle.source.name
            var articleUrloImage: String = currentArticle.urlToImage

            val navigationArticle = ArticleForNavigation(
                task_id = 0,
                articleURL = articleURL ?: "null",
                articleTitle = articleTitle ?: "null",
                articleDescription = articleDescription ?: "null",
                articleDateTime = articleDateTime ?: "null",
                articleSource = articleSource ?: "null",
                articleUrloImage = articleUrloImage ?: "null"
            )

            val direction = HeadlinesFragmentDirections.actionHeadlinesFragmentToShowContentFragment(navigationArticle, null)
            Navigation.findNavController(it).navigate(direction)
            //it.findNavController().navigate(direction)

        }
    }

    fun takeUntilT(input: String): String {
        // "T" harfinin indeksini bul
        val index = input.indexOf('T')

        // "T" harfi bulunamadıysa, tüm string'i geri döndür
        if (index == -1) {
            return input
        }

        // "T" harfine kadar olan kısmı geri döndür
        return input.substring(0, index)
    }


}
