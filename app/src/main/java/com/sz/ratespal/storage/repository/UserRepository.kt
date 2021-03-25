package com.sz.ratespal.storage.repository

import androidx.lifecycle.LiveData
import com.sz.ratespal.entities.User
import com.sz.ratespal.storage.dao.UserDao
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val localDataSource: UserDao
) {

    fun getUser(): LiveData<User> {
        return localDataSource.getUser()
    }

    fun deleteUser() {
        localDataSource.deleteUser()
    }

    suspend fun insertUser(value: User) {
        localDataSource.insert(value)
    }

}