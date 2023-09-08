package com.example.retrofitroompaises.handler

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.retrofitroompaises.R
import com.example.retrofitroompaises.model.Country
import com.example.retrofitroompaises.model.Pais
import com.example.retrofitroompaises.viewModel.PaisViewModel

class AlertDialogIdHandler(
    private val con: Context,
    private val paisViewModel: PaisViewModel,
    private val dataIdHandler: DataIdHandler,
    private val jsonHandler: JsonHandler
) {
    fun showAddAlertDialog() {
        val dialogView = LayoutInflater.from(con).inflate(R.layout.add_id_item, null)
        val idET = dialogView.findViewById<EditText>(R.id.addIdItem_IdEditText)
        val nameET = dialogView.findViewById<EditText>(R.id.addIdItem_NameEditText)
        val officialET = dialogView.findViewById<EditText>(R.id.addIdItem_OfficialEditText)
        val acronymET = dialogView.findViewById<EditText>(R.id.addIdItem_AcronymEditText)
        val capitalET = dialogView.findViewById<EditText>(R.id.addIdItem_CapitalEditText)
        val regionET = dialogView.findViewById<EditText>(R.id.addIdItem_RegionEditText)
        val subregionET = dialogView.findViewById<EditText>(R.id.addIdItem_SubRegionEditText)
        val areaET = dialogView.findViewById<EditText>(R.id.addIdItem_AreaEditText)
        val populationET = dialogView.findViewById<EditText>(R.id.addIdItem_PopulationEditText)
        val continentET = dialogView.findViewById<EditText>(R.id.addIdItem_ContinentEditText)
        val insertButton = dialogView.findViewById<Button>(R.id.addIdItem_InsertButton)
        val cancelButton = dialogView.findViewById<Button>(R.id.addIdItem_CancelButton)

        val builder = AlertDialog.Builder(con)
        builder.setView(dialogView)

        val dialog = builder.create()

        cancelButton.setOnClickListener {
            dialog.dismiss()
        }
        insertButton.setOnClickListener {
            if(idET.text.toString().isEmpty()){ // Id não especificado
                if (nameET.text.isBlank()) {
                    Toast.makeText(con, "Nome não pode estar vazio.", Toast.LENGTH_SHORT).show()
                } else {
                    val entity = Country(
                        name = nameET.text.toString().trim(),
                        official = officialET.text.toString().trim(),
                        acronym = acronymET.text.toString().trim(),
                        capital = capitalET.text.toString().trim(),
                        region = regionET.text.toString().trim(),
                        subregion = subregionET.text.toString().trim(),
                        area = areaET.text.toString().toDoubleOrNull(),
                        population = populationET.text.toString().toIntOrNull(),
                        continent = continentET.text.toString().trim()
                    )
                    paisViewModel.insert(entity)
                    Toast.makeText(con, "País adicionado: ${entity.name}", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
            } else { // Id especificado
                if (nameET.text.isBlank()) {
                    Toast.makeText(con, "Nome não pode estar vazio.", Toast.LENGTH_SHORT).show()
                } else {
                    val entity = Country(
                        id = idET.text.toString().trim().toInt(),
                        name = nameET.text.toString().trim(),
                        official = officialET.text.toString().trim(),
                        acronym = acronymET.text.toString().trim(),
                        capital = capitalET.text.toString().trim(),
                        region = regionET.text.toString().trim(),
                        subregion = subregionET.text.toString().trim(),
                        area = areaET.text.toString().toDoubleOrNull(),
                        population = populationET.text.toString().toIntOrNull(),
                        continent = continentET.text.toString().trim()
                    )
                    if(paisViewModel.insert(entity)){
                        Toast.makeText(con, "País adicionado: ${entity.id}", Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    } else {
                        Toast.makeText(con, "ID invalido ou já existente.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
        dialog.show()
    }

    fun showDeleteAlertDialog() {
        val alertDialogBuilder = AlertDialog.Builder(con)
        alertDialogBuilder.setTitle("Delete Operation")
        alertDialogBuilder.setMessage("Esta operação deletará todos os itens da database, tem certeza que deseja prosseguir ?")
        alertDialogBuilder.setPositiveButton("Sim, deletar conteúdo.") { dialog: DialogInterface, _: Int ->
            paisViewModel.deleteAllCountries()
            dataIdHandler.findCountryById("")
            dialog.dismiss()
            Toast.makeText(con, "Países deletados", Toast.LENGTH_SHORT).show()
        }
        alertDialogBuilder.setNegativeButton("Não, abortar.") { dialog: DialogInterface, _: Int ->
            dialog.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    fun showResetAlertDialog() {
        val alertDialogBuilder = AlertDialog.Builder(con)
        alertDialogBuilder.setTitle("Reset Operation")
        alertDialogBuilder.setMessage("Esta operação irá reinserir todos os paises da Json-Api na database, tem certeza que deseja prosseguir ?")
        alertDialogBuilder.setPositiveButton("Sim, reinserir conteúdo.") { dialog: DialogInterface, _: Int ->
            jsonHandler.resetJsonOperation()
            dataIdHandler.findCountryById("")
            dialog.dismiss()
            Toast.makeText(con, "Países reinseridos", Toast.LENGTH_SHORT).show()
        }
        alertDialogBuilder.setNegativeButton("Não, abortar.") { dialog: DialogInterface, _: Int ->
            dialog.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}