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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.retrofitroompaises.data.RetrofitClient.apiService
import com.example.retrofitroompaises.model.Pais
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    private lateinit var paisViewModel: PaisViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var paisAdapter: PaisAdapter
    private lateinit var searchEditText: EditText
    private var searchQuery: String = ""
    private lateinit var fabMenu: FloatingActionButton
    private lateinit var fabBack: FloatingActionButton
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var fabDelete: FloatingActionButton
    private lateinit var fabReset: FloatingActionButton
    private lateinit var fabFragment: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        paisViewModel = ViewModelProvider(this)[PaisViewModel::class.java]
        recyclerView = view.findViewById(R.id.fragmentMain_RecyclerView)
        searchEditText = view.findViewById(R.id.fragmentMain_SearchBar)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        paisAdapter = PaisAdapter(requireContext(), emptyList(), paisViewModel) // Initialize the adapter with an empty list
        recyclerView.adapter = paisAdapter

        //resetJsonOperation()

        Log.d("LOOKOUT_UAV2", "findPaisByNome call inicial")
        findPaisByNome(searchQuery)

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                searchQuery = s.toString().trim()
                Log.d("LOOKOUT_UAV2", "findPaisByNome call searchEditText")
                findPaisByNome(searchQuery)
            }
        })

        fabMenu = view.findViewById(R.id.fragmentMain_fabMenu)
        fabBack = view.findViewById(R.id.fragmentMain_fabBack)
        fabAdd = view.findViewById(R.id.fragmentMain_fabAdd)
        fabDelete = view.findViewById(R.id.fragmentMain_fabDelete)
        fabReset = view.findViewById(R.id.fragmentMain_fabReset)
        closeMenu()

        fabMenu.setOnClickListener {
            openMenu()
        }
        fabBack.setOnClickListener {
            closeMenu()
        }
        fabAdd.setOnClickListener {
            showAddAlertDialog()
            closeMenu()
        }
        fabDelete.setOnClickListener {
            showDeleteAlertDialog()
            closeMenu()
        }
        fabReset.setOnClickListener {
            showResetAlertDialog()
            closeMenu()
        }

        fabFragment = view.findViewById(R.id.fragmentMain_fabChangeFragment)
        fabFragment.setOnClickListener {
            openMyFragment()
        }

        return view

    }

    private fun closeMenu() {
        fabMenu.visibility = View.VISIBLE
        fabBack.visibility = View.GONE
        fabAdd.visibility = View.GONE
        fabDelete.visibility = View.GONE
        fabReset.visibility = View.GONE
    }

    private fun openMenu() {
        fabMenu.visibility = View.GONE
        fabBack.visibility = View.VISIBLE
        fabAdd.visibility = View.VISIBLE
        fabDelete.visibility = View.VISIBLE
        fabReset.visibility = View.VISIBLE
    }

    private fun resetJsonOperation(){
        lifecycleScope.launch(Dispatchers.IO) {
            insertAllPaisJSONs()
        }
    }
    private suspend fun insertAllPaisJSONs() {
        try {
            val response = apiService.getPaisesJSON()
            if (response.isSuccessful) {
                Log.d("LOOKOUT_UAV", "API request successful")
                val responseBody = response.body()
                responseBody?.forEach {
                    val entity = Pais(
                        id = it.id.m49,
                        nome = it.nome,
                        regiaoIntermediaria = it.regiaoIntermediaria?.nome ?: "",
                        subRegiao = it.subRegiao.nome,
                        regiao = it.subRegiao.regiao.nome
                    )
                    paisViewModel.insert(entity)
                }
            } else {
                val errorBody = response.errorBody()?.string()
                Log.d("LOOKOUT_UAV", "API request unsuccessful: $errorBody")
            }
        } catch (e: Exception) {
            Log.e("LOOKOUT_UAV", "Exception occurred: ${e.message}", e)
        }
    }

    private fun findPaisByNome(busca: String) {
        if(busca.isEmpty()){
            paisViewModel.getAllPaises().observe(viewLifecycleOwner, Observer { paisList ->
                Log.d("LOOKOUT_UAV2", "GetAllPaises UPDATE-SIZE: ${paisList.size}")
                paisAdapter.updateData(paisList) // Update the adapter's data when the list changes
            })
        }
        else{
            paisViewModel.findByNome(busca).observe(viewLifecycleOwner, Observer { paisList ->
                Log.d("LOOKOUT_UAV2", "FindByNome UPDATE: ${paisList}")
                paisAdapter.updateData(paisList)
            })
        }
    }

    private fun showAddAlertDialog() {
        val dialogView = requireActivity().layoutInflater.inflate(R.layout.add_item, null)
        val addItemNome = dialogView.findViewById<EditText>(R.id.addItemNome)
        val addItemRegiao = dialogView.findViewById<EditText>(R.id.addItemRegiao)
        val addItemRegiaoIntermediaria = dialogView.findViewById<EditText>(R.id.addItemRegiaoIntermediaria)
        val addItemSubRegiao = dialogView.findViewById<EditText>(R.id.addItemSubRegiao)
        val addItemCancel: Button = dialogView.findViewById(R.id.addItemCancel)
        val addItemButton: Button = dialogView.findViewById(R.id.addItemButton)

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogView)

        val dialog = builder.create()

        addItemCancel.setOnClickListener {
            // Handle insert button click
            // Perform any desired action
            dialog.dismiss()
        }

        addItemButton.setOnClickListener {
            val itemName = addItemNome.text.toString().trim()
            val itemRegiao = addItemRegiao.text.toString().trim()
            val itemRegiaoIntermediaria = addItemRegiaoIntermediaria.text.toString().trim()
            val itemSubRegiao = addItemSubRegiao.text.toString().trim()

            if (itemName.isNotEmpty() && itemRegiao.isNotEmpty()) {
                val entity = Pais(
                    nome = itemName,
                    regiao = itemRegiao,
                    regiaoIntermediaria = itemRegiaoIntermediaria,
                    subRegiao = itemSubRegiao
                )
                paisViewModel.insert(entity)
                Toast.makeText(requireContext(), "País adicionado: $itemName", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            } else {
                // Show an error or prompt to fill all the required fields
                Toast.makeText(requireContext(),"Nome e Região não podem estar vazios.",Toast.LENGTH_SHORT).show()
            }
        }

        dialog.show()
    }

    private fun showDeleteAlertDialog() {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Delete Operation")
        alertDialogBuilder.setMessage("Esta operação deletará todos os itens da database, tem certeza que deseja prosseguir ?")
        alertDialogBuilder.setPositiveButton("Sim, deletar conteúdo.") { dialog: DialogInterface, _: Int ->
            paisViewModel.deleteAllPaises()
            findPaisByNome("")
            dialog.dismiss()
            Toast.makeText(requireContext(), "Países deletados", Toast.LENGTH_SHORT).show()
        }
        alertDialogBuilder.setNegativeButton("Não, abortar.") { dialog: DialogInterface, _: Int ->
            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun showResetAlertDialog() {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Reset Operation")
        alertDialogBuilder.setMessage("Esta operação irá reinserir todos os paises da Json-Api na database, tem certeza que deseja prosseguir ?")
        alertDialogBuilder.setPositiveButton("Sim, reinserir conteúdo.") { dialog: DialogInterface, _: Int ->
            resetJsonOperation()
            findPaisByNome("")
            dialog.dismiss()
            Toast.makeText(requireContext(), "Países reinseridos", Toast.LENGTH_SHORT).show()
        }
        alertDialogBuilder.setNegativeButton("Não, abortar.") { dialog: DialogInterface, _: Int ->
            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun openMyFragment() {
        val myFragment = MyFragment()
        parentFragmentManager.beginTransaction()
            .replace(R.id.masterFrameLayout, myFragment)
            .addToBackStack(null)
            .commit()
    }

}