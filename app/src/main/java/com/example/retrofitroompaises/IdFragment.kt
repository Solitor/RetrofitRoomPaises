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
import com.example.retrofitroompaises.handler.AlertDialogIdHandler
import com.example.retrofitroompaises.handler.DataIdHandler
import com.example.retrofitroompaises.handler.JsonHandler
import com.example.retrofitroompaises.viewModel.PaisIdAdapter
import com.example.retrofitroompaises.viewModel.PaisViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class IdFragment(private val paisViewModel: PaisViewModel) : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var paisIdAdapter: PaisIdAdapter
    private lateinit var searchEditText: EditText
    private var searchQuery: String = ""
    private lateinit var alertDialogIdHandler: AlertDialogIdHandler
    private lateinit var dataIdHandler: DataIdHandler
    private lateinit var jsonHandler: JsonHandler
    private lateinit var spinnerFilter: Spinner
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var fabBackFragment: FloatingActionButton
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_id, container, false)

        recyclerView = view.findViewById(R.id.fragmentId_RecyclerView)
        searchEditText = view.findViewById(R.id.fragmentId_SearchBar)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        paisIdAdapter = PaisIdAdapter(
            requireContext(),
            emptyList(),
            paisViewModel
        )
        recyclerView.adapter = paisIdAdapter
        dataIdHandler = DataIdHandler(paisViewModel, paisIdAdapter, viewLifecycleOwner)
        jsonHandler = JsonHandler(paisViewModel, RetrofitClient.apiService, lifecycleScope)
        alertDialogIdHandler =
            AlertDialogIdHandler(requireContext(), paisViewModel, dataIdHandler, jsonHandler)

        dataIdHandler.findPaisById(searchQuery)

        spinnerFilter = view.findViewById(R.id.fragmentId_SpinnerFilter)

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

        fabAdd = view.findViewById(R.id.fragmentId_fabAdd)
        fabAdd.setOnClickListener{
            alertDialogIdHandler.showAddAlertDialog()
        }

        fabBackFragment = view.findViewById(R.id.fragmentId_fabBackFragment)
        fabBackFragment.setOnClickListener{
            parentFragmentManager.popBackStack()
        }

        return view
    }

    private fun filterData(searchQuery: String, filterOption: Int) {
        when (filterOption) {
            0 -> {
                dataIdHandler.findPaisById(searchQuery)
            }
            1 -> {
                dataIdHandler.findPaisByIdDesc(searchQuery)
            }
        }
    }
}
