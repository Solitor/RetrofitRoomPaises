package com.example.retrofitroompaises

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitroompaises.data.RetrofitClient
import com.example.retrofitroompaises.data.RetrofitClient.apiService
import com.example.retrofitroompaises.model.Pais
import com.example.retrofitroompaises.model.PaisJSON
import com.example.retrofitroompaises.viewModel.PaisAdapter
import com.example.retrofitroompaises.viewModel.PaisViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var paisViewModel: PaisViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var paisAdapter: PaisAdapter
    private lateinit var searchEditText: EditText
    private var searchQuery: String = ""
    private lateinit var fabAddItem: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        paisViewModel = ViewModelProvider(this).get(PaisViewModel::class.java)
        recyclerView = findViewById(R.id.recycler_view)
        searchEditText = findViewById(R.id.search_bar)
        recyclerView.layoutManager = LinearLayoutManager(this)
        paisAdapter = PaisAdapter(this, emptyList()) // Initialize the adapter with an empty list
        recyclerView.adapter = paisAdapter

        lifecycleScope.launch(Dispatchers.IO) {
            insertAllPaisJSONs()
        }

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

        fabAddItem = findViewById(R.id.floatingActionButton)
        fabAddItem.setOnClickListener {
            showAlertDialog()
        }
    }

    private suspend fun insertAllPaisJSONs() {
        try {
            val response = apiService.getPaisesJSON()
            if (response.isSuccessful) {
                Log.d("LOOKOUT_UAV", "API request successful")
                paisViewModel.deleteAllPaises()
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

    private fun showAlertDialog() {
        val dialogView = layoutInflater.inflate(R.layout.add_item, null)
        val addItemId = dialogView.findViewById<EditText>(R.id.addItemId)
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
            val itemId = addItemId.text.toString().trim()
            val itemName = addItemNome.text.toString().trim()
            val itemRegiao = addItemRegiao.text.toString().trim()
            val itemRegiaoIntermediaria = addItemRegiaoIntermediaria.text.toString().trim()
            val itemSubRegiao = addItemSubRegiao.text.toString().trim()

            if (itemId.isNotEmpty() && itemName.isNotEmpty() && itemRegiao.isNotEmpty()) {
                if(itemId.isDigitsOnly()){
                    val entity = Pais(
                        id = itemId.toInt(),
                        nome = itemName,
                        regiao = itemRegiao,
                        regiaoIntermediaria = itemRegiaoIntermediaria,
                        subRegiao = itemSubRegiao
                    )
                    val insertResult = paisViewModel.insert(entity)
                    if(insertResult){
                        Toast.makeText(this, "País adicionado: ID->$itemId", Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    }else{
                        Toast.makeText(this, "Já existe um país com este ID", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this, "ID deve conter apenas numeros.", Toast.LENGTH_SHORT).show()
                }

            } else {
                // Show an error or prompt to fill all the required fields
                Toast.makeText(this, "ID, Nome e Região não podem estar vazios.", Toast.LENGTH_SHORT).show()
            }
        }

        dialog.show()
    }
}