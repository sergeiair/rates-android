package com.sz.ratespal.activities.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.sz.ratespal.App
import com.sz.ratespal.R
import com.sz.ratespal.entities.User
import com.sz.ratespal.ui.shared.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.lang.ref.WeakReference

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Navigator {

    private val viewModel: UserViewModel by viewModels()

    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController
    }

    override fun onResume() {
        super.onResume()

        App.currentActivity = WeakReference(this)
    }

    override fun navigate(fragmentId: Int) {
        navController?.navigate(fragmentId)
    }

    override fun navigateToAuthorizedArea(user: User?) {
        if (user?.token?.isNotEmpty() == true) {
            App.token = user.token
            navigate(R.id.overviewFragment)
        }
    }

    override fun logOut() {
        viewModel.deleteUser()
        navigate(R.id.loginFragment)
    }

}