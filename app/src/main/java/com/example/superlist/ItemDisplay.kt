package com.example.superlist

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.superlist.database.ItemDatabase
import com.example.superlist.databinding.FragmentItemDisplayBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemDisplay : Fragment() {

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentItemDisplayBinding.inflate(layoutInflater)


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
        if (arguments?.getBoolean("searched") == false) {
            //Means we haven't searched on the api yet.
            Food.FoodAPI.Api.retrofitService.getProductByName(arguments?.getString("searchName") as String).enqueue(
                object : javax.security.auth.callback.Callback, Callback<ProductSearch> {
                    override fun onResponse(call: Call<ProductSearch>, response: Response<ProductSearch>) {
                        // We can access the properties of the Place object, but use safe calls
                        // to avoid issues.
                        System.out.println("getProductByName succeeded")
                        println("${response.body()?.products?.get(0)?.id?.toInt().toString()} ${response.body()?.type} ${response.body()?.products?.get(0)?.title} ${response.body()?.products?.get(0)?.image}")
                        Food.FoodAPI.Api.retrofitService.getNutrition(response.body()?.products?.get(0)?.id?.toInt().toString()).enqueue(
                            object : javax.security.auth.callback.Callback, Callback<Nutrition> {
                                override fun onResponse(call: Call<Nutrition>, response: Response<Nutrition>) {
                                    System.out.println("getNutrition succeeded")
                                    println("${response.body()?.calories} ${response.body()?.carbs} ${response.body()?.protein} ${response.body()?.fat}")
                                    val imageView = binding.imageViewThumbnail
                                    val thumbnail: String = arguments?.getString("thumbnail")!!
                                    Glide.with(application).load(thumbnail).override(300, 200).into(imageView)

                                    binding.textViewName.text = "Name: ${arguments?.getString("searchName")}"
                                    binding.textViewPrice.text = "Price: ${arguments?.getDouble("price").toString()}"

                                    binding.textViewCalories.text = "Calories: ${response.body()?.calories}"
                                    binding.textViewCarbs.text = "Carbs: ${response.body()?.carbs}"
                                    binding.textViewProtein.text = "Protein: ${response.body()?.protein}"
                                    binding.textViewFat.text = "Fat: ${response.body()?.fat}"


                                }

                                override fun onFailure(call: Call<Nutrition>, t: Throwable) {
                                    println("Failure ${t.message}")

                                }
                            })
                    }

                    override fun onFailure(call: Call<ProductSearch>, t: Throwable) {
                        println("Failure ${t.message}")

                    }
                })
//


        } else {
            //We have searched the api. so use the data already stored.
        }



        return binding.root

//        return inflater.inflate(R.layout.fragment_item_display, container, false)
    }

}