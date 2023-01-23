package com.example.roomdata.fragments.update

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomdata.R
import com.example.roomdata.model.User
import com.example.roomdata.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {

private val args by navArgs<UpdateFragmentArgs>()
  private lateinit var mUserViewModel: UserViewModel
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_update, container, false)
       view.updateFirstName.setText(args.currentUser.firstName)
    mUserViewModel= ViewModelProvider(this).get(UserViewModel::class.java)
        view.updateFirstName.setText(args.currentUser.firstName)
        view.updateLasttName.setText(args.currentUser.lastName)
        view.updateAge.setText(args.currentUser.age.toString())

    view.update_btn.setOnClickListener {
        updateItem()
    }

        //add menu

        setHasOptionsMenu(true)
        return view
    }
    private fun updateItem(){

        val firstName = updateFirstName.text.toString()
        val lastName = updateLasttName.text.toString()
        val age = Integer.parseInt(updateAge.text.toString())

        if(inputChek(firstName,lastName,updateAge.text))
        {
            val updateUser = User(args.currentUser.id,firstName,lastName,age)

            mUserViewModel.updateUser(updateUser)
            Toast.makeText(requireContext(),"update Successfully!",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

        }
        else
        {
            Toast.makeText(requireContext(),"Please Fill!",Toast.LENGTH_SHORT).show()

        }
    }
    private fun inputChek(firstName:String,lastName:String,age: Editable):Boolean{

        return !(TextUtils.isEmpty(firstName)&& TextUtils.isEmpty(lastName)&& age.isEmpty())

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.delete_menu,menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.menu_delete){
                deleteUser()
        }
    return super.onContextItemSelected(item)

    }


    private fun deleteUser()
    {
        val builde = AlertDialog.Builder(requireContext())
        builde.setPositiveButton("yes"){_,_ ->

            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(),"Successfully remove data ${args.currentUser.firstName}",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builde.setNegativeButton("No"){_,_ ->

        }
        builde.setTitle("Delete ${args.currentUser.firstName}?")
        builde.setMessage("Are you sure you want to delete ${args.currentUser.firstName}")
        builde.create().show()
    }


}