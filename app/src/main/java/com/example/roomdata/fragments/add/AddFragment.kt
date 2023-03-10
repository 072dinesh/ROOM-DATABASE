package com.example.roomdata.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomdata.R
import com.example.roomdata.model.User
import com.example.roomdata.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class addFragment : Fragment() {

        private lateinit var mUserViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        view.add_btn.setOnClickListener {
                insertDataToDatabasde()
            }
    return view
    }
    private fun insertDataToDatabasde()
    {

       val firstName = addFirstName.text.toString()
        val lastName = addLasttName.text.toString()
        val age = addAge.text

        if(inputChek(firstName,lastName,age))
        {
            val user = User(0,firstName,lastName,Integer.parseInt(age.toString()))
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(),"Successfully added!",Toast.LENGTH_LONG).show()

        findNavController().navigate(R.id.action_addFragment_to_listFragment)

        }
        else
        {
            Toast.makeText(requireContext(),"please fill out all filled",Toast.LENGTH_LONG).show()
        }

    }
    private fun inputChek(firstName:String,lastName:String,age:Editable):Boolean{

        return !(TextUtils.isEmpty(firstName)&& TextUtils.isEmpty(lastName)&& age.isEmpty())

    }

}

