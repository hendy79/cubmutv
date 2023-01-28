package com.hendyapp.cubmutv.view.main

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.hendyapp.cubmutv.R
import com.hendyapp.cubmutv.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Loads [MainFragment].
 */
@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_browse_fragment, MainFragment())
                .commitNow()
        }
    }
}