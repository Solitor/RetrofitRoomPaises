package com.example.retrofitroompaises

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitroompaises.viewModel.PaisAdapter
import com.example.retrofitroompaises.viewModel.PaisViewModel
import androidx.lifecycle.lifecycleScope
import com.example.retrofitroompaises.data.RetrofitClient.apiService
import com.example.retrofitroompaises.handler.AlertDialogHandler
import com.example.retrofitroompaises.handler.DataHandler
import com.example.retrofitroompaises.handler.JsonHandler
import com.example.retrofitroompaises.handler.MenuHandler

class MainFragment : Fragment() {

    private lateinit var paisViewModel: PaisViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var paisAdapter: PaisAdapter
    private lateinit var searchEditText: EditText
    private var searchQuery: String = ""
    private lateinit var menuHandler: MenuHandler
    private lateinit var alertDialogHandler: AlertDialogHandler
    private lateinit var dataHandler: DataHandler
    private lateinit var jsonHandler: JsonHandler

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        paisViewModel = ViewModelProvider(this)[PaisViewModel::class.java]
        recyclerView = view.findViewById(R.id.fragmentMain_RecyclerView)
        searchEditText = view.findViewById(R.id.fragmentMain_SearchBar)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        paisAdapter = PaisAdapter(
            requireContext(),
            emptyList(),
            paisViewModel
        )
        recyclerView.adapter = paisAdapter
        dataHandler = DataHandler(paisViewModel, paisAdapter, viewLifecycleOwner)
        jsonHandler = JsonHandler(paisViewModel, apiService, lifecycleScope)
        alertDialogHandler =
            AlertDialogHandler(requireContext(), paisViewModel, dataHandler, jsonHandler)

        Log.d("LOOKOUT_UAV2", "findPaisByNome call inicial")
        dataHandler.findPaisByNome(searchQuery)

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                searchQuery = s.toString().trim()
                Log.d("LOOKOUT_UAV2", "findPaisByNome call searchEditText")
                dataHandler.findPaisByNome(searchQuery)
            }
        })
        menuHandler = MenuHandler(view)
        menuHandler.closeMenu()

        menuHandler.fabMenu.setOnClickListener {
            menuHandler.openMenu()
        }
        menuHandler.fabBack.setOnClickListener {
            menuHandler.closeMenu()
        }
        menuHandler.fabAdd.setOnClickListener {
            alertDialogHandler.showAddAlertDialog()
            menuHandler.closeMenu()
        }
        menuHandler.fabDelete.setOnClickListener {
            alertDialogHandler.showDeleteAlertDialog()
            menuHandler.closeMenu()
        }
        menuHandler.fabReset.setOnClickListener {
            alertDialogHandler.showResetAlertDialog()
            menuHandler.closeMenu()
        }

        menuHandler.fabFilterFragment.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.masterFrameLayout, FiltersFragment(paisViewModel))
                .addToBackStack(null)
                .commit()
        }

        menuHandler.fabIdFragment.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.masterFrameLayout, IdFragment(paisViewModel))
                .addToBackStack(null)
                .commit()
        }

        return view

    }


}