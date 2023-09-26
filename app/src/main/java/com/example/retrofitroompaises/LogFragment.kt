package com.example.retrofitroompaises

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitroompaises.data.RetrofitClient
import com.example.retrofitroompaises.handler.AlertDialogHandler
import com.example.retrofitroompaises.handler.DataHandler
import com.example.retrofitroompaises.handler.DataLogHandler
import com.example.retrofitroompaises.handler.JsonHandler
import com.example.retrofitroompaises.viewModel.LogAdapter
import com.example.retrofitroompaises.viewModel.PaisAdapter
import com.example.retrofitroompaises.viewModel.PaisViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class LogFragment(private val paisViewModel: PaisViewModel) : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var logAdapter: LogAdapter
    private lateinit var searchEditText: EditText
    private var searchQuery: String = ""
    private lateinit var dataLogHandler: DataLogHandler
    private lateinit var spinnerFilter: Spinner
    private lateinit var fabBackFragment: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_log, container, false)

        recyclerView = view.findViewById(R.id.fragmentLog_RecyclerView)
        searchEditText = view.findViewById(R.id.fragmentLog_SearchBar)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        logAdapter = LogAdapter(
            requireContext(),
            paisViewModel
        )
        recyclerView.adapter = logAdapter
        dataLogHandler = DataLogHandler(paisViewModel, logAdapter, viewLifecycleOwner)

        dataLogHandler.findChangeLogByIdNewest(searchQuery)

        spinnerFilter = view.findViewById(R.id.fragmentLog_SpinnerFilter)

        spinnerFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                filterData(searchQuery, position) // Call a method to filter the data based on the selected option
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                spinnerFilter.setSelection(0)
                filterData(searchQuery, 0)
            }
        }

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                searchQuery = s.toString().trim()
                Log.d("LOOKOUT_UAV2", "findChangeLogByIdNewest call searchEditText")
                filterData(searchQuery, spinnerFilter.selectedItemPosition)
            }
        })

        fabBackFragment = view.findViewById(R.id.fragmentLog_fabBackFragment)
        fabBackFragment.setOnClickListener{
            parentFragmentManager.popBackStack()
        }

        return view
    }

    private fun filterData(searchQuery: String, filterOption: Int) {
        when (filterOption) {
            0 -> {
                dataLogHandler.findChangeLogByIdNewest(searchQuery)
            }
            1 -> {
                dataLogHandler.findChangeLogByIdOldest(searchQuery)
            }
            2 -> {
                dataLogHandler.findChangeLogByIdNewestOperation(searchQuery,"INSERT")
            }
            3 -> {
                dataLogHandler.findChangeLogByIdOldestOperation(searchQuery,"INSERT")
            }
            4 -> {
                dataLogHandler.findChangeLogByIdNewestOperation(searchQuery,"UPDATE")
            }
            5 -> {
                dataLogHandler.findChangeLogByIdOldestOperation(searchQuery,"UPDATE")
            }
            6 -> {
                dataLogHandler.findChangeLogByIdNewestOperation(searchQuery,"DELETE")
            }
            7 -> {
                dataLogHandler.findChangeLogByIdOldestOperation(searchQuery,"DELETE")
            }
        }
    }

}