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

class AlertDialogHandler(
    private val context: Context,
    private val paisViewModel: PaisViewModel,
    private val dataHandler: DataHandler,
    private val jsonHandler: JsonHandler
) {
    fun showAddAlertDialog() {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.add_item, null)
        val addItemNome = dialogView.findViewById<EditText>(R.id.addItemNome)
        val addItemRegiao = dialogView.findViewById<EditText>(R.id.addItemRegiao)
        val addItemRegiaoIntermediaria =
            dialogView.findViewById<EditText>(R.id.addItemRegiaoIntermediaria)
        val addItemSubRegiao = dialogView.findViewById<EditText>(R.id.addItemSubRegiao)
        val addItemCancel: Button = dialogView.findViewById(R.id.addItemCancel)
        val addItemButton: Button = dialogView.findViewById(R.id.addItemButton)

        val builder = AlertDialog.Builder(context)
        builder.setView(dialogView)

        val dialog = builder.create()

        addItemCancel.setOnClickListener {
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
                Toast.makeText(context, "País adicionado: $itemName", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            } else {
                Toast.makeText(context, "Nome e Região não podem estar vazios.", Toast.LENGTH_SHORT)
                    .show()
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
            dataHandler.findPaisByNome("")
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
            dataHandler.findPaisByNome("")
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