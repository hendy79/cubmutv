package com.hendyapp.cubmutv.view.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

import com.hendyapp.cubmutv.databinding.FragmentMainBinding
import com.hendyapp.cubmutv.util.Constants
import com.hendyapp.cubmutv.view.custom.WrapContentLinearLayoutManager
import com.hendyapp.cubmutv.view.detail.DetailActivity
import com.hendyapp.cubmutv.view.main.adapter.CategoriesAdapter
import com.hendyapp.cubmutv.view.main.viewmodel.MainViewModel

/**
 * Loads a grid of cards with movies to browse.
 */
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: CategoriesAdapter

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        this.adapter = CategoriesAdapter(requireContext()) { coupon ->
            val intent = Intent(requireActivity(), DetailActivity::class.java)
            intent.putExtra(Constants.PARCEL_DATA, coupon)
            startActivity(intent)
        }
        binding.mainRv.apply {
            layoutManager = WrapContentLinearLayoutManager(requireContext())
            adapter = this@MainFragment.adapter
        }
        viewModel.categoriesLiveData.observe(viewLifecycleOwner) {
            this.adapter.submitList(it.toList())
        }
        viewModel.getCategoriesWithCoupons()
    }
}