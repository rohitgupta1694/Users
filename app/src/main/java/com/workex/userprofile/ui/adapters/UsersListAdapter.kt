package com.workex.userprofile.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.workex.userprofile.R
import com.workex.userprofile.model.User
import kotlinx.android.synthetic.main.user_list_item.view.*


class UsersListAdapter(
    private val usersList: ArrayList<User>,
    private val listener: OnListItemClickListener
) :
    RecyclerView.Adapter<UsersListAdapter.ViewHolder>() {

    //region Override Methods

    override fun getItemCount(): Int = usersList.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.user_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(usersList[position], listener)
    }

    //endregion

    //region Users List View Holder

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(
            user: User,
            listener: OnListItemClickListener
        ) {
            itemView.userFullName.text = user.firstName + user.lastName
            itemView.userName.text = "@" + user.userName

            Glide.with(itemView.userProfileImage.context)
                .load(user.avatar)
                .into(itemView.userProfileImage)
//                .apply(
//                    RequestOptions()
//                        .centerCrop()
//                        .diskCacheStrategy(DiskCacheStrategy.ALL)
//                        .priority(Priority.HIGH)
//                )

            itemView.setOnClickListener { listener.onItemClick(user) }
        }
    }

    //endregion

    //region On Item Click Listener

    interface OnListItemClickListener {

        fun onItemClick(user: User)
    }

    //endregion

}