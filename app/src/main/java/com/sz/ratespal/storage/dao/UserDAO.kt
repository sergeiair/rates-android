package com.sz.ratespal.storage.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sz.ratespal.entities.User

@Dao
interface UserDao {

    @Query("SELECT * FROM users LIMIT 1")
    fun getUser() : LiveData<User>

    @Query("DELETE FROM users")
    fun deleteUser()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)


}