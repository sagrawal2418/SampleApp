package com.sample.practiceapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.practiceapp.UiState
import com.sample.practiceapp.adapter.ToDoAdapter
import com.sample.practiceapp.databinding.UserFragmentBinding
import com.sample.practiceapp.viewmodel.ToDoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 * Use the [ToDoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class ToDoFragment : Fragment() {

    private lateinit var toDoAdapter: ToDoAdapter
    private lateinit var binding: UserFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = UserFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val viewModel: ToDoViewModel by viewModel()
        viewModel.itemState.observe(viewLifecycleOwner) {
            when (it) {
                is UiState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    // Create adapter passing in the sample user data
                    toDoAdapter = ToDoAdapter(it.data)
                    toDoAdapter.notifyDataSetChanged()
                    // Attach the adapter to the recyclerview to populate items
                    binding.rvTodos.adapter = toDoAdapter
                    // Set layout manager to position the items
                    binding.rvTodos.layoutManager = LinearLayoutManager(context)
                    // That's all!
                    binding.rvTodos.visibility = View.VISIBLE
                }

                is UiState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.rvTodos.visibility = View.GONE
                }

                is UiState.Error -> {
                    //Handle Error
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
        viewModel.getToDos()
    }

}