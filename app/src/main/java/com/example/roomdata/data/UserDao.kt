package com.example.roomdata.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomdata.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
     suspend fun updateUser(user: User)

     @Delete
     suspend fun deleteUser(user: User)

     @Query("DELETE FROM user_table")
     suspend fun deleteAllUser()

//    @Query("SELECT * FROM user_table WHERE name LIKE '%' || :searsh || '%' ORDER BY important DESC ")
//    suspend fun searshUser(search: String?): LiveData<List<User>>

    @Query("SELECT * FROM user_table WHERE firstName LIKE :searchQuery OR lastName LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<User>>




    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData() : LiveData<List<User>>
}