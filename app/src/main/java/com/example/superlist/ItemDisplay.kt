package com.example.superlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.superlist.databinding.FragmentItemDisplayBinding


class ItemDisplay : Fragment() {

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentItemDisplayBinding.inflate(layoutInflater)

        return binding.root

//        return inflater.inflate(R.layout.fragment_item_display, container, false)
    }

}