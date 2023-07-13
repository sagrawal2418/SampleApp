package com.sample.practiceapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.sample.practiceapp.databinding.ItemUserBinding
import com.sample.practiceapp.model.User

class UserAdapter(
    val users: List<User>
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.binding.tvUserName.text = user.username
        holder.binding.tvEmail.text = user.email
        holder.binding.tvName.text = user.name

        holder.binding.root.setOnClickListener {
            holder.itemView.context.toast("User item has been clicked:: ${user.name}")
        }
    }

    private fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}