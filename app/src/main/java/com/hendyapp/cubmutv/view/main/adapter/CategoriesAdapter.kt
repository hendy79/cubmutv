package com.hendyapp.cubmutv.view.main.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hendyapp.cubmutv.databinding.ItemCategoryBinding
import com.hendyapp.cubmutv.model.Category
import com.hendyapp.cubmutv.model.Coupon
import com.hendyapp.cubmutv.view.custom.WrapContentLinearLayoutManager

class CategoriesAdapter(
    private val context: Context,
    private val onItemClicked: (Coupon) -> Unit
): ListAdapter<Category, CategoriesAdapter.CategoryViewHolder>(DiffCallback()) {
    private class DiffCallback : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Category, newItem: Category) =
            oldItem.id == newItem.id &&
            oldItem.name == newItem.name

    }

    class CategoryViewHolder(private val binding: ItemCategoryBinding): RecyclerView.ViewHolder(binding.root) {
        private lateinit var layoutManager: LinearLayoutManager
        private lateinit var adapter: CouponsAdapter
        private var size = 0

        fun bind(item: Category, onItemClicked: (Coupon) -> Unit, context: Context, size: Int) {
            this.size = size

            binding.icatTitle.text = item.name

            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            this.adapter = CouponsAdapter(context) {
                onItemClicked(it)
            }

            binding.icatRv.apply {
                layoutManager = this@CategoryViewHolder.layoutManager
                adapter = this@CategoryViewHolder.adapter
            }

            binding.icatRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    handleOnScroll()
                }
            })

            this.adapter.submitList(item.coupons.toList()) {
                handleOnScroll()
            }

            binding.icatLeft.setOnClickListener {
                var first = this@CategoryViewHolder.layoutManager.findFirstVisibleItemPosition()
                val last = this@CategoryViewHolder.layoutManager.findLastVisibleItemPosition()
                first -= last-first
                if(first < 0)
                    first = 0
                binding.icatRv.smoothScrollToPosition(first)
            }

            binding.icatRight.setOnClickListener {
                val first = this@CategoryViewHolder.layoutManager.findFirstVisibleItemPosition()
                var last = this@CategoryViewHolder.layoutManager.findLastVisibleItemPosition()
                last += last-first
                if(last >= this.size)
                    last = this.size-1
                binding.icatRv.smoothScrollToPosition(last)
            }

            handleOnScroll()
        }

        private fun handleOnScroll() {
            val first = this@CategoryViewHolder.layoutManager.findFirstVisibleItemPosition()
            val last = this@CategoryViewHolder.layoutManager.findLastVisibleItemPosition()
            binding.icatLeft.visibility = View.GONE
            binding.icatRight.visibility = View.GONE
            if(first > 0) {
                binding.icatLeft.visibility = View.VISIBLE
            }
            if(last < this.size-2) {
                binding.icatRight.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CategoryViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item, onItemClicked, context, currentList.size)
    }
}