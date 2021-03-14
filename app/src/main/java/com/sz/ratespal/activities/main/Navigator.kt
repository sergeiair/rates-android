package com.sz.ratespal.activities.main

import com.sz.ratespal.entities.User

interface Navigator {

    fun navigate(fragmentId: Int)

    fun navigateToAuthorizedArea(user: User?)

}