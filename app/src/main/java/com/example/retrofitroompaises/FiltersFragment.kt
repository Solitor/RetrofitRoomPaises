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
import com.example.retrofitroompaises.handler.JsonHandler
import com.example.retrofitroompaises.viewModel.PaisAdapter
import com.example.retrofitroompaises.viewModel.PaisViewModel

class FiltersFragment(private val paisViewModel: PaisViewModel) : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var paisAdapter: PaisAdapter
    private lateinit var searchEditText: EditText
    private var searchQuery: String = ""
    private lateinit var alertDialogHandler: AlertDialogHandler
    private lateinit var dataHandler: DataHandler
    private lateinit var jsonHandler: JsonHandler
    private lateinit var spinnerFilter: Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_filters, container, false)

        recyclerView = view.findViewById(R.id.fragmentFilter_RecyclerView)
        searchEditText = view.findViewById(R.id.fragmentFilter_SearchBar)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        paisAdapter = PaisAdapter(
            requireContext(),
            emptyList(),
            paisViewModel
        )
        recyclerView.adapter = paisAdapter
        dataHandler = DataHandler(paisViewModel, paisAdapter, viewLifecycleOwner)
        jsonHandler = JsonHandler(paisViewModel, RetrofitClient.apiService, lifecycleScope)
        alertDialogHandler =
            AlertDialogHandler(requireContext(), paisViewModel, dataHandler, jsonHandler)

        dataHandler.findPaisByNome(searchQuery)

        spinnerFilter = view.findViewById(R.id.fragmentFilter_SpinnerFilter)

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
                Log.d("LOOKOUT_UAV2", "findPaisByNome call searchEditText")
                filterData(searchQuery, spinnerFilter.selectedItemPosition)
            }
        })

        return view
    }

    private fun filterData(searchQuery: String, filterOption: Int) {
        when (filterOption) {
            0 -> {
                dataHandler.findPaisByRegiao(searchQuery)
            }
            1 -> {
                dataHandler.findPaisByRegiaoIntermediaria(searchQuery)
            }
            2 -> {
                dataHandler.findPaisBySubRegiao(searchQuery)
            }
        }
    }

}