package com.sample.practiceapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.sample.practiceapp.R
import com.sample.practiceapp.databinding.ItemTodoBinding
import com.sample.practiceapp.databinding.ItemUserBinding
import com.sample.practiceapp.model.TodoItem

class ToDoAdapter(val todos: List<TodoItem>) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    inner class ToDoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(
            ItemTodoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = todos.size

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {

        holder.binding.tvTitle.text = todos[position].title
        holder.binding.cbDone.isChecked = todos[position].completed
    }
}