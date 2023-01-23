package com.example.roomdata.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdata.R
import com.example.roomdata.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*


class ListFragment : Fragment(), SearchView.OnQueryTextListener,
    androidx.appcompat.widget.SearchView.OnQueryTextListener {


        private lateinit var mUserViewModel : UserViewModel
    val adapter = ListAdpte()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        //Recyclerview


//        val adapter = ListAdpte(
//            onItemClick = {
//
//               var action=  ListFragmentDirections.actionListFragmentToUpdateFragment(it)
//
//
//            //navigate(action)
//            }
//        )
        val recyclerView=view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer {
                user ->
            adapter.setData(user)
        })
        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)

        }

        setHasOptionsMenu(true)
        return view

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {


        inflater.inflate(R.menu.searsh_data,menu)
        val search = menu.findItem(R.id.menu_searsh)
        val searchView = search.actionView as androidx.appcompat.widget.SearchView

        searchView.setOnQueryTextListener(this)



    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.menus_delete -> {
                deleteAllUser()
            }

        }

        return super.onOptionsItemSelected(item)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }


    override fun onQueryTextChange(query: String?): Boolean {
        if(query != null){
            searchDatabase(query)
        }
        return true
    }





    // We have just created this function for searching our database
    private fun searchDatabase(query: String) {
        // %" "% because our custom sql query will require that
        val searchQuery = "%$query%"
        //Log.d("$searchQuery", "valuessss: ")
        mUserViewModel.searchDatabase(searchQuery).observe(this, { user ->
            user.let {
                adapter.setData(it)
            }
        })



    }


    private fun deleteAllUser()
    {
        val builde = AlertDialog.Builder(requireContext())
        builde.setPositiveButton("yes"){_,_ ->

            mUserViewModel.deleteAllUser()
            Toast.makeText(requireContext(),"Successfully remove everything",
                Toast.LENGTH_SHORT).show()
             }
        builde.setNegativeButton("No"){_,_ ->

        }
        builde.setTitle("Delete everything")
        builde.setMessage("Are you sure you want to delete everything")
        builde.create().show()
    }

}