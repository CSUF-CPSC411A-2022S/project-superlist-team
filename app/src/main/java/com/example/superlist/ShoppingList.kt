package com.example.superlist

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.superlist.database.Item
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
                .navigate(R.id.action_shoppingList_to_searchFragment)
        }
        binding.testFoodDisplay.setOnClickListener { view: View ->
            val item = Item(itemId = 1, price = 12.0, searchName = "chicken noodle soup", productName = "", thumbnail = "https://www.simplyrecipes.com/thmb/wL2O85jWlNeh-Z6l3Mtbb13MXjQ=/2000x1334/filters:no_upscale():max_bytes(150000):strip_icc()/__opt__aboutcom__coeus__resources__content_migration__simply_recipes__uploads__2019__01__Chicken-Noodle-Soup-LEAD-2-1-ab6c430a6d39457a858fa5dfb1acd811.jpg", searched=false )
            val bundle = bundleOf("itemId" to item.itemId, "price" to item.price, "searchName" to item.searchName, "productName" to item.productName, "thumbnail" to item.thumbnail, "calories" to item.calories, "carbs" to item.carbs, "protein" to item.protein, "fat" to item.fat, "searched" to item.searched)
            view.findNavController()
                .navigate(R.id.action_shoppingList_to_itemDisplay, bundle)
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
        itemViewModel.itemString.observe(viewLifecycleOwner, Observer {
            it?.let {
                println("observer called")
                binding.asdf.text = it.toString()
            }
        })

        binding.buttonTestAddToDB.setOnClickListener { view: View ->
            val item = Item(itemId = 1, price = 12.0, searchName = "chicken noodle soup", productName = "", thumbnail = "https://www.simplyrecipes.com/thmb/wL2O85jWlNeh-Z6l3Mtbb13MXjQ=/2000x1334/filters:no_upscale():max_bytes(150000):strip_icc()/__opt__aboutcom__coeus__resources__content_migration__simply_recipes__uploads__2019__01__Chicken-Noodle-Soup-LEAD-2-1-ab6c430a6d39457a858fa5dfb1acd811.jpg", searched=false )
            itemViewModel.insertTest("chicken noodle soup", 12.0, "https://www.simplyrecipes.com/thmb/wL2O85jWlNeh-Z6l3Mtbb13MXjQ=/2000x1334/filters:no_upscale():max_bytes(150000):strip_icc()/__opt__aboutcom__coeus__resources__content_migration__simply_recipes__uploads__2019__01__Chicken-Noodle-Soup-LEAD-2-1-ab6c430a6d39457a858fa5dfb1acd811.jpg")
        }

        var itemListAdapter = ItemListAdapter(requireNotNull(this).activity?.applicationContext!!)

        // Attach intersection adapter.
        binding.itemRecyclerview.adapter = itemListAdapter

        itemViewModel.itemList.observe(viewLifecycleOwner, Observer {
            println("itemList changed")
            it?.let {
                itemListAdapter.submitList(it)
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



//        Food.FoodAPI.Api.retrofitService.getProductByName("chicken noodle soup").enqueue(
//            object : javax.security.auth.callback.Callback, Callback<ProductSearch> {
//                override fun onResponse(call: Call<ProductSearch>, response: Response<ProductSearch>) {
//                    // We can access the properties of the Place object, but use safe calls
//                    // to avoid issues.
//                    System.out.println("getProductByName succeeded")
//                    Log.d("getProductByName", response.toString())
//                    println("${response.body()?.type} ${response.body()?.products?.get(0)?.title} ${response.body()?.products?.get(0)?.image}")
//                }
//
//                override fun onFailure(call: Call<ProductSearch>, t: Throwable) {
//                    println("Failure ${t.message}")
//
//                }
//            })
//
//        Food.FoodAPI.Api.retrofitService.getNutrition("1003464").enqueue(
//            object : javax.security.auth.callback.Callback, Callback<Nutrition> {
//                override fun onResponse(call: Call<Nutrition>, response: Response<Nutrition>) {
//                    System.out.println("asdf1")
//                    println("${response.body()?.carbs} ${response.body()?.fat} ${response.body()?.protein} ${response.body()?.calories}")
//                }
//
//                override fun onFailure(call: Call<Nutrition>, t: Throwable) {
//                    println("Failure ${t.message}")
//
//                }
//            })

//        return inflater.inflate(R.layout.fragment_shopping_list, container, false)
        
        return binding.root

    }
}