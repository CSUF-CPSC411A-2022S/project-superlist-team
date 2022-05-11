package com.example.superlist

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.superlist.database.ItemDatabase
import com.example.superlist.databinding.FragmentSearchBinding
import com.example.superlist.databinding.FragmentShoppingListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSearchBinding.inflate(layoutInflater)

        binding.button.setOnClickListener { view: View ->
//            searchAPI()
            val searchString = binding.editTextSearch.text.toString()
            Search.SearchAPI.Api.retrofitService.searchWalmart(searchString).enqueue(
                object : javax.security.auth.callback.Callback, Callback<SearchData> {
                    override fun onResponse(call: Call<SearchData>, response: Response<SearchData>) {
                        Log.d("searchWalmart", response.toString())
                        for (result in response.body()?.organic_results!!) {
                            binding.list.text = binding.list.text.toString() + "\n $${result.primary_offer.offer_price} - ${result.title}"

                        }
                    }

                    override fun onFailure(call: Call<SearchData>, t: Throwable) {
                        println("Failure ${t.message}")

                    }
                })

        }

        // Get reference to this application
        val application = requireNotNull(this).activity?.applicationContext

        // Retrieve Intersection data access object.
        val dataSource = ItemDatabase.getInstance(application as Application).itemDao

        // Create a factory that generates IntersectionViewModels connected to the database.
        val viewModelFactory = ItemViewModelFactory(dataSource, application as Application)

        // Generate an IntersectionViewModel using the factory.
        val itemViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(ItemViewModel::class.java)
        binding.itemViewModel = itemViewModel
        binding.lifecycleOwner = this

        return binding.root

    }

    fun searchAPI(searchString: String) {

    }
}