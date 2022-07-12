package com.gb.imageload

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gb.imageload.databinding.ActivityMainBinding
import com.gb.imageload.ui.coil.CoilFragment
import com.gb.imageload.ui.glide.GlideFragment
import com.gb.imageload.ui.picasso.PicassoFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_coil -> {
                    navigateTo(CoilFragment())
                }
                R.id.navigation_glide -> {
                    navigateTo(GlideFragment())
                }
                R.id.navigation_picasso -> {
                    navigateTo(PicassoFragment())
                }
            }
            true
        }
        binding.bottomNavigationView.selectedItemId = R.id.navigation_coil
    }

    private fun navigateTo(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}