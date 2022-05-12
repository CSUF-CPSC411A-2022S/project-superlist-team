package com.example.superlist


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.superlist.database.Item
import com.example.superlist.databinding.ListItemBinding

/**
 * A RecyclerView adapter that uses the DiffCallback for better performance.
 */
class ItemListAdapter(private val context: Context) : ListAdapter<Item,
        ItemListAdapter.ItemViewHolder>(ItemDiffCallback()) {

    /**
     * Inner class used to store data about each element in the RecyclerView. We provide a binding
     * to make it easy to access elements of the layout.
     */
    class ItemViewHolder(val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var thumbnail: ImageView
        init {
            thumbnail =
                binding.root.findViewById<View>(R.id.itemImage) as ImageView
        }
        /**
         * Assign an intersection object
         */
        fun bind(item: Item) {
            binding.item = item
        }
    }

    /**
     * Creates a View to visualize one element in the RecyclerView.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // We use an inflater based on the parent (IntersectionListFragment) and create an
        // ItemViewHolder with binding to the layout to simplify access.
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(layoutInflater, parent, false)
        return ItemViewHolder(binding)
    }

    /**
     * The function is called whenever the RecyclerView displays a specific element. It provides
     * access to the ItemViewHolder and its position.
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        // Assign the corresponding element from the data and the click listener. Take note getItem
        // is a function provided by ListAdapter.

        println("Trying to load thumbnail: ${getItem(position).thumbnail}")
        Glide.with(context).load(getItem(position).thumbnail).override(300, 200)
            .into(holder.thumbnail)

        holder.bind(getItem(position))
    }
}

/**
 * Manages a RecyclerView list using the Eugene W. Myers's difference algorithm to reduce processing.
 */
class ItemDiffCallback : DiffUtil.ItemCallback<Item>() {
    /**
     * We use intersectionId because it is a unique ID referring to a single element.
     */
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.itemId == newItem.itemId
    }

    /**
     * We check all properties to check equality between Intersection objects.
     */
    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.searchName == newItem.searchName && oldItem.price == newItem.price
    }
}