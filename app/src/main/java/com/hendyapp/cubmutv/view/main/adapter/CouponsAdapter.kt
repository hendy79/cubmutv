package com.hendyapp.cubmutv.view.main.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.hendyapp.cubmutv.databinding.ItemCouponBinding
import com.hendyapp.cubmutv.model.Coupon

class CouponsAdapter(
    private val context: Context,
    private val onItemClicked: (Coupon) -> Unit
): ListAdapter<Coupon, CouponsAdapter.CouponViewHolder>(DiffCallback()) {
    private class DiffCallback : DiffUtil.ItemCallback<Coupon>() {
        override fun areItemsTheSame(oldItem: Coupon, newItem: Coupon) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Coupon, newItem: Coupon) =
            oldItem.id == newItem.id &&
            oldItem.name == newItem.name &&
            oldItem.brandLogo == newItem.brandLogo &&
            oldItem.couponTnc == newItem.couponTnc &&
            oldItem.onHovered == newItem.onHovered
    }

    class CouponViewHolder(val binding: ItemCouponBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Coupon, onItemClicked: (Coupon) -> Unit) {
            Glide.with(binding.icouImage)
                .load(item.brandLogo)
                .fitCenter()
                .into(binding.icouImage)

            binding.root.setOnClickListener {
                onItemClicked(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CouponViewHolder(
            ItemCouponBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )


    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: CouponViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item, onItemClicked)
    }
}