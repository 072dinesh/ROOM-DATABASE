package com.example.roomdata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {

//lateinit var database : ContactDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//
//        database = ContactDatabase.getDatabase(this)
//         GlobalScope.launch {
//            database.contactDao().insertcontact(Contact(0,"dinesh","24556825", Date()))
//        }
//
//
//    }
//
//    fun getdata(view: View) {
//        database.contactDao().getContact().observe(this, Observer{
//            Log.d("cheezycode",it.toString())
//        })
//



    setupActionBarWithNavController(findNavController(R.id.fragmentContainerView) )



     }

    override fun onSupportNavigateUp(): Boolean {

        val navController = findNavController(R.id.fragmentContainerView)

        return navController.navigateUp()|| super.onSupportNavigateUp()
    }

}