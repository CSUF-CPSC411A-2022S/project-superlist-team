package com.example.superlist

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.superlist.database.ItemDao
import com.example.superlist.database.ItemDatabase
import com.example.superlist.databinding.FragmentShoppingListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ShoppingList : Fragment() {


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentShoppingListBinding.inflate(layoutInflater)

        binding.add.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(R.id.action_shoppingList_to_itemDisplay)
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
        println("asdf123")
        itemViewModel.itemString.observe(viewLifecycleOwner, Observer {
            it?.let {
                println("observer called")
                binding.asdf.text = it.toString()
            }
        })
//        print(itemViewModel.database.getAll().get(0))

//        Search.SearchAPI.Api.retrofitService.searchWalmart("chicken noodle soup").enqueue(
//            object : javax.security.auth.callback.Callback, Callback<SearchData> {
//                override fun onResponse(call: Call<SearchData>, response: Response<SearchData>) {
//                    // We can access the properties of the Place object, but use safe calls
//                    // to avoid issues.
//                    System.out.println("searchWalmart succeeded")
//                    Log.d("searchWalmart", response.toString())
//                    println("${response.body()?.organic_results?.get(0)?.title} ${response.body()?.organic_results?.get(0)?.thumbnail} ${response.body()?.organic_results?.get(0)?.primary_offer?.offer_price}")
//                  //  println("${response.body()?.type} ${response.body()?.products?.get(0)?.title} ${response.body()?.products?.get(0)?.image}")
//                }
//
//                override fun onFailure(call: Call<SearchData>, t: Throwable) {
//                    println("Failure ${t.message}")
//
//                }
//            })

//        Food.FoodAPI.Api.retrofitService.getNutrition("1003464").enqueue(
//            object : javax.security.auth.callback.Callback, Callback<Nutrition> {
//                override fun onResponse(call: Call<Nutrition>, response: Response<Nutrition>) {
//                    System.out.println("asdf1")
//                    println("${response.body()?.carbs} ${response.body()?.fat} ${response.body()?.protein}")
//                }
//
//                override fun onFailure(call: Call<Nutrition>, t: Throwable) {
//                    println("Failure ${t.message}")
//
//                }
//            })


//        return inflater.inflate(R.layout.fragment_shopping_list, container, false)
        val imageView = binding.imageView3
        Glide.with(this).load("https://i5.walmartimages.com/asr/5c52eaee-e372-4f74-aa4a-ec9420dddacf_1.04efc7490b601d0b1ace3aeac6fa2f94.jpeg?odnHeight=200&odnWidth=200&odnBg=ffffff").override(300, 200).into(imageView)
        return binding.root

    }
}