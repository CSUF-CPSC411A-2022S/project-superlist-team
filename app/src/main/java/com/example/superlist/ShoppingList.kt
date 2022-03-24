package com.example.superlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.superlist.databinding.ActivityMainBinding
import com.example.superlist.databinding.FragmentShoppingListBinding


class ShoppingList : Fragment() {


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentShoppingListBinding.inflate(layoutInflater)
        binding.button.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(R.id.action_shoppingList_to_itemDisplay)
        }
        return inflater.inflate(R.layout.fragment_shopping_list, container, false)
    }
}