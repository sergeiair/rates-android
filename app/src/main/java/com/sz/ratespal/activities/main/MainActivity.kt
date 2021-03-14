package com.sz.ratespal.activities.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import com.sz.ratespal.R
import com.sz.ratespal.entities.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Navigator {

    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController
    }

    override fun navigate(fragmentId: Int) {
        navController?.navigate(fragmentId)
    }

    override fun navigateToAuthorizedArea(user: User?) {
        if (user?.token?.isNotEmpty() == true) {
            navigate(R.id.overviewFragment)
        }
    }

}