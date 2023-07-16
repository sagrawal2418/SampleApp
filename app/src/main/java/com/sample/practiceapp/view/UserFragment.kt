package com.sample.practiceapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.practiceapp.UiState
import com.sample.practiceapp.adapter.UserAdapter
import com.sample.practiceapp.databinding.FragmentUserBinding
import com.sample.practiceapp.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding
    private lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: UserViewModel by viewModel()

        viewModel.itemState.observe(viewLifecycleOwner) {
            when (it) {
                is UiState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    // Create adapter passing in the sample user data
                    userAdapter = UserAdapter(it.data)
                    userAdapter.notifyDataSetChanged()
                    // Attach the adapter to the recyclerview to populate items
                    binding.rvTodos.adapter = userAdapter
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
        viewModel.getUsers()
    }
}