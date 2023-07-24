
package com.example.retrofitroompaises

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitroompaises.data.RetrofitClient.apiService
import com.example.retrofitroompaises.model.Pais
import com.example.retrofitroompaises.viewModel.PaisAdapter
import com.example.retrofitroompaises.viewModel.PaisViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        paisViewModel = ViewModelProvider(this).get(PaisViewModel::class.java)
        recyclerView = findViewById(R.id.recycler_view)
        searchEditText = findViewById(R.id.search_bar)
        recyclerView.layoutManager = LinearLayoutManager(this)
        paisAdapter = PaisAdapter(this, emptyList(), paisViewModel) // Initialize the adapter with an empty list
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

        fabMenu = findViewById(R.id.floatingActionButtonMenu)
        fabBack = findViewById(R.id.floatingActionButtonBack)
        fabAdd = findViewById(R.id.floatingActionButtonAdd)
        fabDelete = findViewById(R.id.floatingActionButtonDelete)
        fabReset = findViewById(R.id.floatingActionButtonReset)
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

        fabFragment = findViewById(R.id.floatingActionButtonFragment)
        fabFragment.setOnClickListener {
            openMyFragment()
        }

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
            paisViewModel.getAllPaises().observe(this, Observer { paisList ->
                Log.d("LOOKOUT_UAV2", "GetAllPaises UPDATE-SIZE: ${paisList.size}")
                paisAdapter.updateData(paisList) // Update the adapter's data when the list changes
            })
        }
        else{
            paisViewModel.findByNome(busca).observe(this, Observer { paisList ->
                Log.d("LOOKOUT_UAV2", "FindByNome UPDATE: ${paisList}")
                paisAdapter.updateData(paisList)
            })
        }
    }

    private fun showAddAlertDialog() {
        val dialogView = layoutInflater.inflate(R.layout.add_item, null)
        val addItemNome = dialogView.findViewById<EditText>(R.id.addItemNome)
        val addItemRegiao = dialogView.findViewById<EditText>(R.id.addItemRegiao)
        val addItemRegiaoIntermediaria = dialogView.findViewById<EditText>(R.id.addItemRegiaoIntermediaria)
        val addItemSubRegiao = dialogView.findViewById<EditText>(R.id.addItemSubRegiao)
        val addItemCancel: Button = dialogView.findViewById(R.id.addItemCancel)
        val addItemButton: Button = dialogView.findViewById(R.id.addItemButton)

        val builder = AlertDialog.Builder(this)
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
                Toast.makeText(this, "País adicionado: $itemName", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            } else {
                // Show an error or prompt to fill all the required fields
                Toast.makeText(this,"Nome e Região não podem estar vazios.",Toast.LENGTH_SHORT).show()
            }
        }

        dialog.show()
    }

    private fun showDeleteAlertDialog() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Delete Operation")
        alertDialogBuilder.setMessage("Esta operação deletará todos os itens da database, tem certeza que deseja prosseguir ?")
        alertDialogBuilder.setPositiveButton("Sim, deletar conteúdo.") { dialog: DialogInterface, _: Int ->
            paisViewModel.deleteAllPaises()
            findPaisByNome("")
            dialog.dismiss()
            Toast.makeText(this, "Países deletados", Toast.LENGTH_SHORT).show()
        }
        alertDialogBuilder.setNegativeButton("Não, abortar.") { dialog: DialogInterface, _: Int ->
            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun showResetAlertDialog() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Reset Operation")
        alertDialogBuilder.setMessage("Esta operação irá reinserir todos os paises da Json-Api na database, tem certeza que deseja prosseguir ?")
        alertDialogBuilder.setPositiveButton("Sim, reinserir conteúdo.") { dialog: DialogInterface, _: Int ->
            resetJsonOperation()
            findPaisByNome("")
            dialog.dismiss()
            Toast.makeText(this, "Países reinseridos", Toast.LENGTH_SHORT).show()
        }
        alertDialogBuilder.setNegativeButton("Não, abortar.") { dialog: DialogInterface, _: Int ->
            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun openMyFragment() {
        // Create an instance of your fragment
        val myFragment = MyFragment()

        // Get the FragmentManager and start a transaction
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        // Replace the container (FrameLayout) with the fragment
        fragmentTransaction.replace(R.id.masterConstraintLayout, myFragment)

        // Add the transaction to the back stack (optional but allows going back)
        fragmentTransaction.addToBackStack(null)

        // Commit the transaction
        fragmentTransaction.commit()
    }
}