package com.example.superlist

import android.R
import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.superlist.database.Item
import com.example.superlist.database.ItemDatabase
import com.example.superlist.databinding.FragmentSearchBinding
import com.example.superlist.databinding.FragmentShoppingListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList


class SearchFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSearchBinding.inflate(layoutInflater)

        val listView = binding.listView

        val context = this.requireContext()
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

        binding.button.setOnClickListener { view: View ->
            val searchString = binding.editTextSearch.text.toString()
            Search.SearchAPI.Api.retrofitService.searchWalmart(searchString).enqueue(
                object : javax.security.auth.callback.Callback, Callback<SearchData> {
                    override fun onResponse(call: Call<SearchData>, response: Response<SearchData>) {
                        Log.d("searchWalmart", response.toString())
                        var itemsStringAr = ArrayList<String>()
                        var itemsResult = ArrayList<organic_result>()
                        var i = 0
                        for (result in response.body()?.organic_results!!) {
//                            binding.list.text = binding.list.text.toString() + "\n $${result.primary_offer.offer_price} - ${result.title}"
                            itemsStringAr.add("$${result.primary_offer.offer_price} - ${result.title}")
                            itemsResult.add(result)
                            if(i == 11) break
                            i++
                        }
                        listView.setAdapter(ArrayAdapter<String>(context, R.layout.simple_list_item_1, itemsStringAr))
                        listView.setOnItemClickListener(OnItemClickListener { arg0, textView, index, arg3 ->
                            Log.v(
                                "clicked1",
                                ((textView as TextView).text as String)!!
                            )
                        val selectedItem = itemsResult[index]
                        val item = Item(price = selectedItem.primary_offer.offer_price, searchName = searchString , productName = selectedItem.title, thumbnail = selectedItem.thumbnail, searched=false )
                        itemViewModel.insert(item)

                        })
                    }

                    override fun onFailure(call: Call<SearchData>, t: Throwable) {
                        println("Failure ${t.message}")

                    }
                })

        }





//        setContentView(ll)

//        listView.setAdapter( ArrayAdapter <String> (this, R.layout.simple_list_item_1, countries));
//        listView.setOnItemClickListener(new OnItemClickListener() {
//
//            public void onItemClick(AdapterView <? > arg0, View arg1, int arg2,
//                long arg3) {
//                Log.v("clicked", (String)((TextView) arg1).getText());
//            }
//
//        });
//        ll.addView(lv);
//        setContentView(ll);

        return binding.root

    }

    fun searchAPI(searchString: String) {

    }
}