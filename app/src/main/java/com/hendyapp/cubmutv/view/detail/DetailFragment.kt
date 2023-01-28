package com.hendyapp.cubmutv.view.detail

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.hendyapp.cubmutv.databinding.FragmentDetailBinding
import com.hendyapp.cubmutv.model.Coupon

class DetailFragment(): Fragment() {
    private lateinit var binding: FragmentDetailBinding

    private lateinit var data: Coupon

    constructor(data: Coupon) : this() {
        this.data = data
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.detailTitle.text = data.brandName
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            binding.detailDesc.text = Html.fromHtml(data.couponTnc, Html.FROM_HTML_MODE_COMPACT)
        } else {
            binding.detailDesc.text = Html.fromHtml(data.couponTnc)
        }
        Glide.with(binding.detailLogo)
            .load(data.brandLogo)
            .centerCrop()
            .into(binding.detailLogo)
        binding.detailRedeem.setOnClickListener {
            Toast.makeText(requireContext(), "Successfully redeemed '${data.brandName}' voucher", Toast.LENGTH_SHORT).show()
        }
    }
}