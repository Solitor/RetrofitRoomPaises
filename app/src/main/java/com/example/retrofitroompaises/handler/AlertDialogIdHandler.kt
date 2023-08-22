package com.example.retrofitroompaises.handler

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.retrofitroompaises.R
import com.example.retrofitroompaises.model.Pais
import com.example.retrofitroompaises.viewModel.PaisViewModel

class AlertDialogIdHandler(
    private val context: Context,
    private val paisViewModel: PaisViewModel,
    private val dataIdHandler: DataIdHandler,
    private val jsonHandler: JsonHandler
) {
    fun showAddAlertDialog() {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.add_id_item, null)
        val addItemId = dialogView.findViewById<EditText>(R.id.addIdItemId)
        val addItemNome = dialogView.findViewById<EditText>(R.id.addIdItemNome)
        val addItemRegiao = dialogView.findViewById<EditText>(R.id.addIdItemRegiao)
        val addItemRegiaoIntermediaria =
            dialogView.findViewById<EditText>(R.id.addIdItemRegiaoIntermediaria)
        val addItemSubRegiao = dialogView.findViewById<EditText>(R.id.addIdItemSubRegiao)
        val addItemCancel: Button = dialogView.findViewById(R.id.addIdItemCancel)
        val addItemButton: Button = dialogView.findViewById(R.id.addIdItemButton)

        val builder = AlertDialog.Builder(context)
        builder.setView(dialogView)

        val dialog = builder.create()

        addItemCancel.setOnClickListener {
            dialog.dismiss()
        }
        addItemButton.setOnClickListener {
            val itemId = addItemId.text.toString().trim()
            val itemName = addItemNome.text.toString().trim()
            val itemRegiao = addItemRegiao.text.toString().trim()
            val itemRegiaoIntermediaria = addItemRegiaoIntermediaria.text.toString().trim()
            val itemSubRegiao = addItemSubRegiao.text.toString().trim()

            if(itemId.isEmpty()){
                if (itemName.isNotEmpty() && itemRegiao.isNotEmpty()) {
                    val entity = Pais(
                        nome = itemName,
                        regiao = itemRegiao,
                        regiaoIntermediaria = itemRegiaoIntermediaria,
                        subRegiao = itemSubRegiao
                    )
                    paisViewModel.insert(entity)
                    Toast.makeText(context, "País adicionado: $itemName", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                } else {
                    Toast.makeText(context, "Nome e Região não podem estar vazios.", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                if (itemName.isNotEmpty() && itemRegiao.isNotEmpty()) {
                    val entity = Pais(
                        id = itemId.toInt(),
                        nome = itemName,
                        regiao = itemRegiao,
                        regiaoIntermediaria = itemRegiaoIntermediaria,
                        subRegiao = itemSubRegiao
                    )
                    if(paisViewModel.insert(entity)){
                        Toast.makeText(context, "País adicionado: $itemName", Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    } else {
                        Toast.makeText(context, "ID invalido ou já existente.", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    Toast.makeText(context, "Nome e Região não podem estar vazios.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        dialog.show()
    }

    fun showDeleteAlertDialog() {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle("Delete Operation")
        alertDialogBuilder.setMessage("Esta operação deletará todos os itens da database, tem certeza que deseja prosseguir ?")
        alertDialogBuilder.setPositiveButton("Sim, deletar conteúdo.") { dialog: DialogInterface, _: Int ->
            paisViewModel.deleteAllPaises()
            dataIdHandler.findPaisById("")
            dialog.dismiss()
            Toast.makeText(context, "Países deletados", Toast.LENGTH_SHORT).show()
        }
        alertDialogBuilder.setNegativeButton("Não, abortar.") { dialog: DialogInterface, _: Int ->
            dialog.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    fun showResetAlertDialog() {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle("Reset Operation")
        alertDialogBuilder.setMessage("Esta operação irá reinserir todos os paises da Json-Api na database, tem certeza que deseja prosseguir ?")
        alertDialogBuilder.setPositiveButton("Sim, reinserir conteúdo.") { dialog: DialogInterface, _: Int ->
            jsonHandler.resetJsonOperation()
            dataIdHandler.findPaisById("")
            dialog.dismiss()
            Toast.makeText(context, "Países reinseridos", Toast.LENGTH_SHORT).show()
        }
        alertDialogBuilder.setNegativeButton("Não, abortar.") { dialog: DialogInterface, _: Int ->
            dialog.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}