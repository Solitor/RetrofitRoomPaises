package com.example.retrofitroompaises.viewModel

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitroompaises.R
import com.example.retrofitroompaises.model.Country

class PaisAdapter(var con: Context, var paisViewModel: PaisViewModel) :
    RecyclerView.Adapter<PaisAdapter.ViewHolder>() {

    var showingList: List<Country> = emptyList()
    val booleanMutableList: MutableList<Boolean> = MutableList(showingList.size) { false }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var nameTV = v.findViewById<TextView>(R.id.paisAdapter_NameTextView)
        var officialTV = v.findViewById<TextView>(R.id.paisAdapter_OfficialTextView)
        var acronymTV = v.findViewById<TextView>(R.id.paisAdapter_AcronymTextView)
        var capitalTV = v.findViewById<TextView>(R.id.paisAdapter_CapitalTextView)
        var regionTV = v.findViewById<TextView>(R.id.paisAdapter_RegionTextView)
        var subregionTV = v.findViewById<TextView>(R.id.paisAdapter_SubRegionTextView)
        var areaTV = v.findViewById<TextView>(R.id.paisAdapter_AreaTextView)
        var populationTV = v.findViewById<TextView>(R.id.paisAdapter_PopulationTextView)
        var continentTV = v.findViewById<TextView>(R.id.paisAdapter_ContinentTextView)
        var buttonsLL = v.findViewById<LinearLayout>(R.id.paisAdapter_ButtonsLinearLayout)
        var editButton = v.findViewById<Button>(R.id.paisAdapter_EditButton)
        var deleteButton = v.findViewById<Button>(R.id.paisAdapter_DeleteButton)
        var paginaTV = v.findViewById<TextView>(R.id.paisAdapter_PaginaTextView)
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

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("LOOKOUT_UAV2", "onBindViewHolder")
        Log.d("LOOKOUT_UAV2", "POSITION: ${position}")

        holder.nameTV.text = showingList[position].name
        holder.officialTV.text = "Nome official:\n\t\t=> " +
                if (showingList[position].official.toString().isBlank()) {
                    "-não informado-"
                } else {
                    showingList[position].official.toString()
                }
        holder.acronymTV.text = "Acronym (sigla):\n\t\t=> " +
                if (showingList[position].acronym.toString().isBlank()) {
                    "-não informado-"
                } else {
                    showingList[position].acronym.toString()
                }
        holder.capitalTV.text = "Capital:\n\t\t=> " +
                if (showingList[position].capital.toString().isBlank()) {
                    "-não informado-"
                } else {
                    showingList[position].capital.toString()
                }
        holder.regionTV.text = "Região:\n\t\t=> " +
                if (showingList[position].region.toString().isBlank()) {
                    "-não informado-"
                } else {
                    showingList[position].region.toString()
                }
        holder.subregionTV.text = "Sub-Region:\n\t\t=> " +
                if (showingList[position].subregion.toString().isBlank()) {
                    "-não informado-"
                } else {
                    showingList[position].subregion.toString()
                }
        holder.areaTV.text = "Area (Km^2):\n\t\t=> " +
                if (showingList[position].area.toString().isBlank()) {
                    "-não informado-"
                } else {
                    showingList[position].area.toString()
                }
        holder.populationTV.text = "População:\n\t\t=> " +
                if (showingList[position].population.toString().isBlank()) {
                    "-não informado-"
                } else {
                    showingList[position].population.toString()
                }
        holder.continentTV.text = "Continente:\n\t\t=> " +
                if (showingList[position].continent.toString().isBlank()) {
                    "-não informado-"
                } else {
                    showingList[position].continent.toString()
                }
        holder.paginaTV.text =
            "País N° " + position.plus(1).toString() + " de " + showingList.size.toString()

        if (booleanMutableList[position]) {
            holder.officialTV.visibility = View.VISIBLE
            holder.acronymTV.visibility = View.VISIBLE
            holder.capitalTV.visibility = View.VISIBLE
            holder.regionTV.visibility = View.VISIBLE
            holder.subregionTV.visibility = View.VISIBLE
            holder.areaTV.visibility = View.VISIBLE
            holder.populationTV.visibility = View.VISIBLE
            holder.continentTV.visibility = View.VISIBLE
            holder.buttonsLL.visibility = View.VISIBLE
        } else {
            holder.officialTV.visibility = View.GONE
            holder.acronymTV.visibility = View.GONE
            holder.capitalTV.visibility = View.GONE
            holder.regionTV.visibility = View.GONE
            holder.subregionTV.visibility = View.GONE
            holder.areaTV.visibility = View.GONE
            holder.populationTV.visibility = View.GONE
            holder.continentTV.visibility = View.GONE
            holder.buttonsLL.visibility = View.GONE
        }
        holder.nameTV.setOnClickListener {
            if (booleanMutableList[position]) {
                holder.officialTV.visibility = View.GONE
                holder.acronymTV.visibility = View.GONE
                holder.capitalTV.visibility = View.GONE
                holder.regionTV.visibility = View.GONE
                holder.subregionTV.visibility = View.GONE
                holder.areaTV.visibility = View.GONE
                holder.populationTV.visibility = View.GONE
                holder.continentTV.visibility = View.GONE
                holder.buttonsLL.visibility = View.GONE
                booleanMutableList[position] = false
            } else {
                holder.officialTV.visibility = View.VISIBLE
                holder.acronymTV.visibility = View.VISIBLE
                holder.capitalTV.visibility = View.VISIBLE
                holder.regionTV.visibility = View.VISIBLE
                holder.subregionTV.visibility = View.VISIBLE
                holder.areaTV.visibility = View.VISIBLE
                holder.populationTV.visibility = View.VISIBLE
                holder.continentTV.visibility = View.VISIBLE
                holder.buttonsLL.visibility = View.VISIBLE
                booleanMutableList[position] = true
            }
        }
        holder.editButton.setOnClickListener {
            buttonEditAlertDialog(showingList[position])
        }
        holder.deleteButton.setOnClickListener {
            buttonDeleteAlertDialog(showingList[position])
        }
    }

    fun updateData(newData: List<Country>) {
        Log.d("LOOKOUT_UAV2", "updateData")
        showingList = newData
        booleanMutableList.clear()
        booleanMutableList.addAll(MutableList(showingList.size) { false })
        notifyDataSetChanged()
    }

    private fun buttonEditAlertDialog(country: Country) {
        val dialogView = LayoutInflater.from(con).inflate(R.layout.edit_item, null)
        var nameET = dialogView.findViewById<EditText>(R.id.editItem_NameEditText)
        var officialET = dialogView.findViewById<EditText>(R.id.editItem_OfficialEditText)
        var acronymET = dialogView.findViewById<EditText>(R.id.editItem_AcronymEditText)
        var capitalET = dialogView.findViewById<EditText>(R.id.editItem_CapitalEditText)
        var regionET = dialogView.findViewById<EditText>(R.id.editItem_RegionEditText)
        var subregionET = dialogView.findViewById<EditText>(R.id.editItem_SubRegionEditText)
        var areaET = dialogView.findViewById<EditText>(R.id.editItem_AreaEditText)
        var populationET = dialogView.findViewById<EditText>(R.id.editItem_PopulationEditText)
        var continentET = dialogView.findViewById<EditText>(R.id.editItem_ContinentEditText)
        var editButton = dialogView.findViewById<Button>(R.id.editItem_EditButton)
        var cancelButton = dialogView.findViewById<Button>(R.id.editItem_CancelButton)


        nameET.hint = country.name.toString()
        officialET.hint = country.official.toString()
        acronymET.hint = country.acronym.toString()
        capitalET.hint = country.capital.toString()
        regionET.hint = country.region.toString()
        subregionET.hint = country.subregion.toString()
        areaET.hint = country.area.toString()
        populationET.hint = country.population.toString()
        continentET.hint = country.continent.toString()

        val builder = AlertDialog.Builder(con)
        builder.setView(dialogView)

        val dialog = builder.create()

        cancelButton.setOnClickListener {
            dialog.dismiss()
        }

        editButton.setOnClickListener {
            if (nameET.text.isBlank()) {
                Toast.makeText(con, "Nome não pode estar vazio.", Toast.LENGTH_SHORT).show()
            } else {
                val entity = Country(
                    id = country.id,
                    name = nameET.text.toString().trim().takeIf { it.isNotBlank() }
                        ?: country.name.toString(),
                    official = officialET.text.toString().trim().takeIf { it.isNotBlank() }
                        ?: country.official.toString(),
                    acronym = acronymET.text.toString().trim().takeIf { it.isNotBlank() }
                        ?: country.acronym.toString(),
                    capital = capitalET.text.toString().trim().takeIf { it.isNotBlank() }
                        ?: country.capital.toString(),
                    region = regionET.text.toString().trim().takeIf { it.isNotBlank() }
                        ?: country.region.toString(),
                    subregion = subregionET.text.toString().trim().takeIf { it.isNotBlank() }
                        ?: country.subregion.toString(),
                    area = areaET.text.toString().toDoubleOrNull() ?: country.area,
                    population = populationET.text.toString().toIntOrNull() ?: country.population,
                    continent = continentET.text.toString().trim().takeIf { it.isNotBlank() }
                        ?: country.continent.toString()
                )
                paisViewModel.update(entity)
                Toast.makeText(con, "Edição efetuada: ${country.name}", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
        }
        dialog.show()
    }

    private fun buttonDeleteAlertDialog(country: Country) {
        val alertDialogBuilder = AlertDialog.Builder(con)
        val id: Int = country.id ?: 0
        alertDialogBuilder.setTitle("Deletar País")
        alertDialogBuilder.setMessage("O país ${country.name} será deletado, deseja continuar ?")
        alertDialogBuilder.setPositiveButton("Sim, deletar.") { dialog: DialogInterface, _: Int ->
            if (paisViewModel.deleteById(id)) {
                dialog.dismiss()
                Toast.makeText(con, "País deletado.", Toast.LENGTH_SHORT).show()
            } else {
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