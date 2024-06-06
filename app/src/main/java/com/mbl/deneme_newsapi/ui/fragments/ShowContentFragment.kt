package com.mbl.deneme_newsapi.ui.fragments

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mbl.deneme_newsapi.R
import com.mbl.deneme_newsapi.databinding.FragmentHeadlinesBinding
import com.mbl.deneme_newsapi.databinding.FragmentShowContentBinding
import com.mbl.deneme_newsapi.ui.viewmodel.NewViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ShowContentFragment : Fragment() {


    private lateinit var binding: FragmentShowContentBinding
    private lateinit var viewModel: NewViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: NewViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentShowContentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle : ShowContentFragmentArgs by navArgs()

        //val article = bundle.articleForNavigation
        //val articleUrl : String = bundle.articleUrlFromFavourites


        bundle.articleForNavigation?.let {
            val article = bundle.articleForNavigation

            try {
                article?.let { it1 -> openAnUrl(it1.articleURL) }
            } catch (e : Exception) {
                Toast.makeText(requireContext(),"Failed Code: ${e.message}",Toast.LENGTH_SHORT).show()
            }

            binding.floatingActionButton.setOnClickListener {
                article?.let { it1 -> viewModel.addArticle(it1) }
                //Navigation.findNavController(it).navigate(R.id.action_showContentFragment_to_favouritesFragment)
                Toast.makeText(requireContext(),"Added to favorites!",Toast.LENGTH_SHORT).show()
            }
        }

        bundle.articleUrlFromFavourites.let {
            val articleUrlFromFavourites = bundle.articleUrlFromFavourites
            try {
                if (articleUrlFromFavourites != null) {
                    openAnUrl(articleUrlFromFavourites)
                }
            } catch (e : Exception) {
                Toast.makeText(requireContext(),"Failed Code: ${e.message}",Toast.LENGTH_SHORT).show()
            }
        }




    }

    fun openAnUrl(url:String) {
        binding.myWebView.webViewClient = WebViewClient()
        binding.myWebView.loadUrl(url)
        //binding.myWebView.settings.javaScriptEnabled = true

    }

}

/*
        // Geri tuşuna basıldığında WebView'ın önceki sayfaya gitmesini sağlamak için OnBackPressedCallback kullanma
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (binding.myWebView.canGoBack()) {
                    binding.myWebView.goBack()
                } else {
                    // WebView'ın geriye gidilecek bir sayfası yoksa, varsayılan olarak fragmentın geriye gitmesini sağla
                    findNavController().popBackStack()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
 */