package com.example.booktest.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.booktest.R
import com.example.booktest.databinding.SerchFragmentBinding

class SearchFragment: Fragment() {

    private lateinit var binding: SerchFragmentBinding
    private lateinit var adapterFilter: ArrayAdapter<String>
    private lateinit var adapterBookType: ArrayAdapter<String>

    private lateinit var comunicator: Comunicator

    override fun onAttach(context: Context) {
        // the context is the main activity
        super.onAttach(context)
        when(context){
            is Comunicator -> comunicator = context
            else -> throw Exception("Incorrect Host Activity")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = SerchFragmentBinding.inflate(inflater, container, false)
        initViews()

        return binding.root
    }

    private fun initViews() {

        adapterFilter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1,requireContext().resources.getStringArray(R.array.book_filter))
        adapterBookType = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1,requireContext().resources.getStringArray(R.array.book_print_type))

        binding.apply {
            bookFilter.adapter = adapterFilter
            bookPrintType.adapter = adapterBookType
            bookSerch.setOnClickListener {
                sendSearchParams()
            }
        }
    }

    private fun sendSearchParams() {
        binding.bookSearchTitle.editText?.text?.let {
            val bookTitle = it.toString()
            val bookFilter = binding.bookFilter.selectedItem.toString()
            val bookPrintType = binding.bookPrintType.selectedItem.toString()

            comunicator.sendDataToSearch(bookTitle,bookFilter,bookPrintType)
        }
    }

}