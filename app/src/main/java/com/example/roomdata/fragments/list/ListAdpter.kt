package com.example.roomdata.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.ListFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdata.R
import com.example.roomdata.model.User
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdpte(
    //val onItemClick: (User)->Unit
): RecyclerView.Adapter<ListAdpte.MyViewHolder>() {

    private var userList= emptyList<User>()
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row,parent,false))

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = userList[position]
        holder.itemView.id_text.text = currentItem.id.toString()
        holder.itemView.firstname_text.text = currentItem.firstName
        holder.itemView.last_text.text = currentItem.lastName
        holder.itemView.age_text.text = currentItem.age.toString()

        holder.itemView.rowLayout.setOnClickListener {

            //onItemClick(currentItem)

            var action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)



        }



    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(user:List<User>){
        this.userList= user
        notifyDataSetChanged()
    }


}