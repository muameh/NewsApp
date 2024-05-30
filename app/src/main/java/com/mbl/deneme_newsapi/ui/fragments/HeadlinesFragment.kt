package com.mbl.deneme_newsapi.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mbl.deneme_newsapi.R
import com.mbl.deneme_newsapi.databinding.FragmentHeadlinesBinding
import com.mbl.deneme_newsapi.ui.adapters.AdapterHeadlines
import com.mbl.deneme_newsapi.ui.viewmodel.NewViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeadlinesFragment : Fragment() {
    private lateinit var binding: FragmentHeadlinesBinding
    private lateinit var viewModel: NewViewModel
    private lateinit var newsAdapterHeadlines: AdapterHeadlines

    private lateinit var countryCode : String
    private lateinit var category : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: NewViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHeadlinesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var countries = arrayOf("USA","Turkey","China","France","Japan","Russia","Germany","United Kingdom","Israel","India" )
        var arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item,countries)
        binding.spinner.adapter = arrayAdapter

        countryCode = ""
        category = ""

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var x = countries[position]
                countryCode = getCountryCode(x)
                viewModel.getHeadlines(countryCode,category)
                setupHeadlinesRecyclerView()
                errorMessage()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                viewModel.getHeadlines("tr")
            }
        }

        binding.toggleButtonx.addOnButtonCheckedListener { toggleGroup, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.general-> {
                        viewModel.getHeadlines(countryCode,"general")
                        setupHeadlinesRecyclerView()
                    }
                    R.id.business -> {
                        viewModel.getHeadlines(countryCode,"business")
                        setupHeadlinesRecyclerView()
                    }
                    R.id.entertainment -> {
                        viewModel.getHeadlines(countryCode,"entertainment")
                        setupHeadlinesRecyclerView()
                    }
                    R.id.sports -> {
                        viewModel.getHeadlines(countryCode,"sports")
                        setupHeadlinesRecyclerView()
                    }
                    R.id.technology -> {
                        viewModel.getHeadlines(countryCode,"technology")
                        setupHeadlinesRecyclerView()
                    }
                }
            }
        }

    }
/*
 override fun onResume() {
        super.onResume()
        viewModel.getHeadlines("us")
    }
 */


    private fun setupHeadlinesRecyclerView(){
        newsAdapterHeadlines = AdapterHeadlines(requireContext(),viewModel)
        binding.recyclerviewHeadlines.apply {
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            adapter = newsAdapterHeadlines
        }
        activity?.let {
            viewModel.headLines.observe(viewLifecycleOwner){
                newsAdapterHeadlines.differ.submitList(it)
            }
        }
    }

    private fun getCountryCode(country: String): String {
        return when (country) {
            "USA" -> "us"
            "Turkey" -> "tr"
            "China" -> "ch"
            "France" -> "fr"
            "Japan" -> "jp"
            "Russia" -> "ru"
            "Germany" -> "de"
            "United Kingdom" -> "gb"
            "Israel" -> "il"
            "India" -> "in"
            else -> "us"
        }
    }

    private fun errorMessage(){
        viewModel.error.observe(viewLifecycleOwner, Observer { errorMessage ->
            errorMessage?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            }
        })
    }

}
