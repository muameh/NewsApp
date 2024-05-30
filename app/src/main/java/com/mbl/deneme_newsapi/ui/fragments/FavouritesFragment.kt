package com.mbl.deneme_newsapi.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.mbl.deneme_newsapi.R
import com.mbl.deneme_newsapi.databinding.FragmentFavouritesBinding
import com.mbl.deneme_newsapi.ui.adapters.AdapterHeadlines
import com.mbl.deneme_newsapi.ui.adapters.AdaptersFavours
import com.mbl.deneme_newsapi.ui.adapters.SwipeToDeleteHelper
import com.mbl.deneme_newsapi.ui.viewmodel.NewViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritesFragment : Fragment() {
    private lateinit var binding: FragmentFavouritesBinding
    private lateinit var adaptersFavours: AdaptersFavours
    private lateinit var viewModel: NewViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: NewViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFavouritesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getfavourArticles()

        setupHeadlinesRecyclerView()

    }


    private fun setupHeadlinesRecyclerView(){
        adaptersFavours = AdaptersFavours(requireContext(),viewModel)
        binding.rvFavours.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            adapter = adaptersFavours
        ///-----------------------------------------Swipe
            val itemTouchHelper = ItemTouchHelper(SwipeToDeleteHelper(viewModel, adaptersFavours))
            itemTouchHelper.attachToRecyclerView(this)
        //------------------------------------------Swipe
        }
        activity?.let {
            viewModel.favourArticles.observe(viewLifecycleOwner){
                adaptersFavours.differ.submitList(it)
            }
        }
    }

}