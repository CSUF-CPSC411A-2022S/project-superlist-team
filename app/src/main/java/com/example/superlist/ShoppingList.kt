package com.example.superlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.example.superlist.database.ItemDao
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
        return binding.root
    }
}