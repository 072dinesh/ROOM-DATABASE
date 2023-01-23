package com.example.roomdata.repository

import androidx.lifecycle.LiveData
import com.example.roomdata.data.UserDao
import com.example.roomdata.model.User

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User)
    {
        userDao.deleteUser(user)

    }
    suspend fun deleteAllUser(){
        userDao.deleteAllUser()
    }

//    suspend fun searshUser(string: String){
//        userDao.searshUser(string)
//    }

    fun searchDatabase(searchQuery: String):LiveData<List<User>> {
        return userDao.searchDatabase(searchQuery)
    }

}