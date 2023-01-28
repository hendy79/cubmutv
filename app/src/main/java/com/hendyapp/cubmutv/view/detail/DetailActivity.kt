package com.hendyapp.cubmutv.view.detail

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.hendyapp.cubmutv.R
import com.hendyapp.cubmutv.databinding.ActivityDetailBinding
import com.hendyapp.cubmutv.model.Coupon
import com.hendyapp.cubmutv.util.Constants

class DetailActivity : FragmentActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var data: Coupon? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        data = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(Constants.PARCEL_DATA, Coupon::class.java)
        } else {
            intent.getParcelableExtra(Constants.PARCEL_DATA)
        }

        if(data == null)
            finish()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.detail_browse_fragment, DetailFragment(data!!))
                .commitNow()
        }
    }
}