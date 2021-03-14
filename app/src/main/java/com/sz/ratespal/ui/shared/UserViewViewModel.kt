package com.sz.ratespal.ui.shared;

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sz.ratespal.entities.LoginResponseData
import com.sz.ratespal.entities.User
import com.sz.ratespal.storage.repository.UserRepository
import com.sz.ratespal.utils.Print

class UserViewViewModel @ViewModelInject constructor(
    private val repository: UserRepository
) : ViewModel() {

    val user: LiveData<User> by lazy {
        repository.getUser()
    }

    suspend fun storeUser(userData: LoginResponseData, token: String?) {
        token?.takeIf { it.isNotEmpty() }?.let {
            repository.insertUser(User(
                1,
                System.currentTimeMillis().toInt(),
                userData.data.name,
                userData.data.email,
                token
            ))
        }
    }

}

