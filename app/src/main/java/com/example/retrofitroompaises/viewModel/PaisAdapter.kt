package com.example.retrofitroompaises.viewModel

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitroompaises.R
import com.example.retrofitroompaises.model.Pais

class PaisAdapter(var con: Context, var paisList: List<Pais>, var paisViewModel: PaisViewModel) :
    RecyclerView.Adapter<PaisAdapter.ViewHolder>() {

    var showingList = paisList
    val booleanMutableList: MutableList<Boolean> = MutableList(showingList.size) { false }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var nomeVH                  = v.findViewById<TextView>(R.id.nome_tv)
        var regiaoIntermediariaVH   = v.findViewById<TextView>(R.id.regiaoIntermediaria_tv)
        var subRegiaoVH             = v.findViewById<TextView>(R.id.subRegiao_tv)
        var regiaoVH                = v.findViewById<TextView>(R.id.regiao_tv)
        var buttonsLayout           = v.findViewById<LinearLayout>(R.id.buttonsLinearLayout)
        var buttonEditVH            = v.findViewById<Button>(R.id.listItemEdit)
        var buttonDeleteVH          = v.findViewById<Button>(R.id.listItemDelete)
        var pagnumeroVH             = v.findViewById<TextView>(R.id.pagina_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("LOOKOUT_UAV2", "onCreateViewHolder")
        val view = LayoutInflater.from(con).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d("LOOKOUT_UAV2", "getItemCount : ${showingList.size}")
        return showingList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("LOOKOUT_UAV2", "onBindViewHolder")
        Log.d("LOOKOUT_UAV2", "POSITION: ${position}")

        val currentItem = showingList[position]
        val auxNome = currentItem.nome.toString()
        val auxRegiao = "Região:\n\t\t=> " + currentItem.regiao.toString()
        val auxRegiaoIntermediaria = if(currentItem.regiaoIntermediaria.toString().isNullOrBlank()){
            "Região Intermediaria:\n\t\t=> -não informada-"
        }else{
            "Região Intermediaria:\n\t\t=> " + currentItem.regiaoIntermediaria.toString()
        }
        val auxSubRegiao = if(currentItem.subRegiao.toString().isNullOrBlank()){
            "Sub-Região:\n\t\t=> -não informada-"
        }else{
            "Sub-Região:\n\t\t=> " + currentItem.subRegiao.toString()
        }
        val auxPagina = "País " + position.plus(1).toString() + " de " + showingList.size.toString()

        holder.nomeVH.text = auxNome
        holder.regiaoVH.text = auxRegiao
        holder.regiaoIntermediariaVH.text = auxRegiaoIntermediaria
        holder.subRegiaoVH.text = auxSubRegiao
        holder.pagnumeroVH.text = auxPagina

        if (booleanMutableList[position]) {
            holder.regiaoIntermediariaVH.visibility = View.VISIBLE
            holder.subRegiaoVH.visibility = View.VISIBLE
            holder.regiaoVH.visibility = View.VISIBLE
            holder.buttonsLayout.visibility = View.VISIBLE
        } else {
            holder.regiaoIntermediariaVH.visibility = View.GONE
            holder.subRegiaoVH.visibility = View.GONE
            holder.regiaoVH.visibility = View.GONE
            holder.buttonsLayout.visibility = View.GONE
        }
        holder.nomeVH.setOnClickListener {
            if (booleanMutableList[position]) {
                holder.regiaoIntermediariaVH.visibility = View.GONE
                holder.subRegiaoVH.visibility = View.GONE
                holder.regiaoVH.visibility = View.GONE
                holder.buttonsLayout.visibility = View.GONE
                booleanMutableList[position] = false
            } else {
                holder.regiaoIntermediariaVH.visibility = View.VISIBLE
                holder.subRegiaoVH.visibility = View.VISIBLE
                holder.regiaoVH.visibility = View.VISIBLE
                holder.buttonsLayout.visibility = View.VISIBLE
                booleanMutableList[position] = true
            }
        }
        holder.buttonEditVH.setOnClickListener{

        }
        holder.buttonDeleteVH.setOnClickListener{
            buttonDeleteAlertDialog(currentItem)
        }
    }

    fun updateData(newData: List<Pais>) {
        Log.d("LOOKOUT_UAV2", "updateData")
        showingList = newData
        booleanMutableList.clear()
        booleanMutableList.addAll(MutableList(showingList.size) { false })
        notifyDataSetChanged()
    }
    private fun buttonDeleteAlertDialog(pais : Pais) {
        val alertDialogBuilder = AlertDialog.Builder(con)
        val id: Int = pais.id ?: 0
        alertDialogBuilder.setTitle("Deletar País")
        alertDialogBuilder.setMessage("Deletar o país ${pais.nome} ?")
        alertDialogBuilder.setPositiveButton("Sim, deletar.") { dialog: DialogInterface, _: Int ->
            if(paisViewModel.deleteById(id)){
                dialog.dismiss()
                Toast.makeText(con, "País deletado.", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(con, "Error ID not found", Toast.LENGTH_SHORT).show()
            }
        }
        alertDialogBuilder.setNegativeButton("Não.") { dialog: DialogInterface, _: Int ->
            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}