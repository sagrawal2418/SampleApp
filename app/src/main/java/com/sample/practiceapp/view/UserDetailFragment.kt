package com.sample.practiceapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sample.practiceapp.adapter.UserAdapter
import com.sample.practiceapp.databinding.UserFragmentBinding

/**
 * A simple [Fragment] subclass.
 * Use the [UserDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class UserDetailFragment : Fragment() {

    private lateinit var binding: UserFragmentBinding
    private lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = UserFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//
//        viewModel.items.observe(viewLifecycleOwner) {
//            // Create adapter passing in the sample user data
//            userAdapter = UserAdapter(it)
//            // Attach the adapter to the recyclerview to populate items
//            binding.rvTodos.adapter = userAdapter
//            // Set layout manager to position the items
//            binding.rvTodos.layoutManager = LinearLayoutManager(context)
//            // That's all!
//        }
//
//        viewModel.loading.observe(viewLifecycleOwner) { value ->
//            binding.progressBar.visibility = if (value) View.VISIBLE else View.GONE
//        }
//
//        viewModel.getUsers()
    }
}