package com.example.retrofitroompaises.viewModel

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
import com.example.retrofitroompaises.model.Pais

class PaisIdAdapter(var con: Context, var paisViewModel: PaisViewModel) :
    RecyclerView.Adapter<PaisIdAdapter.ViewHolder>() {

    var showingList: List<Country> = emptyList()
    val booleanMutableList: MutableList<Boolean> = MutableList(showingList.size) { false }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var idTV = v.findViewById<TextView>(R.id.paisIdAdapter_IdTextView)
        var nameTV = v.findViewById<TextView>(R.id.paisIdAdapter_NameTextView)
        var officialTV = v.findViewById<TextView>(R.id.paisIdAdapter_OfficialTextView)
        var acronymTV = v.findViewById<TextView>(R.id.paisIdAdapter_AcronymTextView)
        var capitalTV = v.findViewById<TextView>(R.id.paisIdAdapter_CapitalTextView)
        var regionTV = v.findViewById<TextView>(R.id.paisIdAdapter_RegionTextView)
        var subregionTV = v.findViewById<TextView>(R.id.paisIdAdapter_SubRegionTextView)
        var areaTV = v.findViewById<TextView>(R.id.paisIdAdapter_AreaTextView)
        var populationTV = v.findViewById<TextView>(R.id.paisIdAdapter_PopulationTextView)
        var continentTV = v.findViewById<TextView>(R.id.paisIdAdapter_ContinentTextView)
        var buttonsLL = v.findViewById<LinearLayout>(R.id.paisIdAdapter_ButtonsLinearLayout)
        var editButton = v.findViewById<Button>(R.id.paisIdAdapter_EditButton)
        var deleteButton = v.findViewById<Button>(R.id.paisIdAdapter_DeleteButton)
        var paginaTV = v.findViewById<TextView>(R.id.paisIdAdapter_PaginaTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("LOOKOUT_UAV2", "onCreateViewHolder")
        val view = LayoutInflater.from(con).inflate(R.layout.list_id, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d("LOOKOUT_UAV2", "getItemCount : ${showingList.size}")
        return showingList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("LOOKOUT_UAV2", "onBindViewHolder")
        Log.d("LOOKOUT_UAV2", "POSITION: ${position}")

        holder.idTV.text = "Id: [" + showingList[position].id.toString() + "]"
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
            holder.nameTV.visibility = View.VISIBLE
            holder.officialTV.visibility = View.VISIBLE
            holder.acronymTV.visibility = View.VISIBLE
            holder.capitalTV.visibility = View.VISIBLE
            holder.regionTV.visibility = View.VISIBLE
            holder.subregionTV.visibility = View.VISIBLE
            holder.areaTV.visibility = View.VISIBLE
            holder.populationTV.visibility = View.VISIBLE
            holder.continentTV.visibility = View.VISIBLE
            holder.buttonsLL.visibility = View.VISIBLE
            holder.idTV.text = "Id: [" + showingList[position].id.toString() + "]"
        } else {
            holder.nameTV.visibility = View.GONE
            holder.officialTV.visibility = View.GONE
            holder.acronymTV.visibility = View.GONE
            holder.capitalTV.visibility = View.GONE
            holder.regionTV.visibility = View.GONE
            holder.subregionTV.visibility = View.GONE
            holder.areaTV.visibility = View.GONE
            holder.populationTV.visibility = View.GONE
            holder.continentTV.visibility = View.GONE
            holder.buttonsLL.visibility = View.GONE
            holder.idTV.text =
                "[" + showingList[position].id.toString() + "] " + showingList[position].name.toString()
        }
        holder.idTV.setOnClickListener {
            if (booleanMutableList[position]) {
                holder.nameTV.visibility = View.GONE
                holder.officialTV.visibility = View.GONE
                holder.acronymTV.visibility = View.GONE
                holder.capitalTV.visibility = View.GONE
                holder.regionTV.visibility = View.GONE
                holder.subregionTV.visibility = View.GONE
                holder.areaTV.visibility = View.GONE
                holder.populationTV.visibility = View.GONE
                holder.continentTV.visibility = View.GONE
                holder.buttonsLL.visibility = View.GONE
                holder.idTV.text =
                    "[" + showingList[position].id.toString() + "] " + showingList[position].name.toString()
                booleanMutableList[position] = false
            } else {
                holder.nameTV.visibility = View.VISIBLE
                holder.officialTV.visibility = View.VISIBLE
                holder.acronymTV.visibility = View.VISIBLE
                holder.capitalTV.visibility = View.VISIBLE
                holder.regionTV.visibility = View.VISIBLE
                holder.subregionTV.visibility = View.VISIBLE
                holder.areaTV.visibility = View.VISIBLE
                holder.populationTV.visibility = View.VISIBLE
                holder.continentTV.visibility = View.VISIBLE
                holder.buttonsLL.visibility = View.VISIBLE
                holder.idTV.text = "Id: [" + showingList[position].id.toString() + "]"
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
        val dialogView = LayoutInflater.from(con).inflate(R.layout.edit_id_item, null)
        var idET = dialogView.findViewById<EditText>(R.id.editIdItem_IdEditText)
        var nameET = dialogView.findViewById<EditText>(R.id.editIdItem_NameEditText)
        var officialET = dialogView.findViewById<EditText>(R.id.editIdItem_OfficialEditText)
        var acronymET = dialogView.findViewById<EditText>(R.id.editIdItem_AcronymEditText)
        var capitalET = dialogView.findViewById<EditText>(R.id.editIdItem_CapitalEditText)
        var regionET = dialogView.findViewById<EditText>(R.id.editIdItem_RegionEditText)
        var subregionET = dialogView.findViewById<EditText>(R.id.editIdItem_SubRegionEditText)
        var areaET = dialogView.findViewById<EditText>(R.id.editIdItem_AreaEditText)
        var populationET = dialogView.findViewById<EditText>(R.id.editIdItem_PopulationEditText)
        var continentET = dialogView.findViewById<EditText>(R.id.editIdItem_ContinentEditText)
        var editButton = dialogView.findViewById<Button>(R.id.editIdItem_EditButton)
        var cancelButton = dialogView.findViewById<Button>(R.id.editIdItem_CancelButton)

        idET.hint = country.id.toString()
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
                if (idET.text.toString().isBlank() or
                    (idET.text.toString() == country.id.toString())
                ) { //Nada foi digitado no IdEditText
                    val entity = Country(
                        id = country.id,
                        name = nameET.text.toString().trim().takeIf { it.isNotBlank() }
                            ?: country.name.toString(),
                        official = if (officialET.text.toString().trim().equals("null")) {
                            null
                        } else {
                            officialET.text.toString().trim().takeIf { it.isNotBlank() }
                                ?: country.official.toString()
                        },
                        acronym = if (acronymET.text.toString().trim().equals("null")) {
                            null
                        } else {
                            acronymET.text.toString().trim().takeIf { it.isNotBlank() }
                                ?: country.acronym.toString()
                        },
                        capital = if (capitalET.text.toString().trim().equals("null")) {
                            null
                        } else {
                            capitalET.text.toString().trim().takeIf { it.isNotBlank() }
                                ?: country.capital.toString()
                        },
                        region = if (regionET.text.toString().trim().equals("null")) {
                            null
                        } else {
                            regionET.text.toString().trim().takeIf { it.isNotBlank() }
                                ?: country.region.toString()
                        },
                        subregion = if (subregionET.text.toString().trim().equals("null")) {
                            null
                        } else {
                            subregionET.text.toString().trim().takeIf { it.isNotBlank() }
                                ?: country.subregion.toString()
                        },
                        area = if (areaET.text.toString().trim().equals("null")) {
                            null
                        } else {
                            areaET.text.toString().toDoubleOrNull() ?: country.area
                        },
                        population = if (populationET.text.toString().trim().equals("null")) {
                            null
                        } else {
                            populationET.text.toString().toIntOrNull() ?: country.population
                        },
                        continent = if (continentET.text.toString().trim().equals("null")) {
                            null
                        } else {
                            continentET.text.toString().trim().takeIf { it.isNotBlank() }
                                ?: country.continent.toString()
                        }
                    )
                    paisViewModel.update(entity)
                    Toast.makeText(con, "Edição efetuada: ID ${country.id}", Toast.LENGTH_SHORT)
                        .show()
                    dialog.dismiss()
                } else { // Algo foi digitado no IdEditText
                    val entity = Country(
                        id = idET.text.toString().toInt(),
                        name = nameET.text.toString().trim().takeIf { it.isNotBlank() }
                            ?: country.name.toString(),
                        official = if (officialET.text.toString().trim().equals("null")) {
                            null
                        } else {
                            officialET.text.toString().trim().takeIf { it.isNotBlank() }
                                ?: country.official.toString()
                        },
                        acronym = if (acronymET.text.toString().trim().equals("null")) {
                            null
                        } else {
                            acronymET.text.toString().trim().takeIf { it.isNotBlank() }
                                ?: country.acronym.toString()
                        },
                        capital = if (capitalET.text.toString().trim().equals("null")) {
                            null
                        } else {
                            capitalET.text.toString().trim().takeIf { it.isNotBlank() }
                                ?: country.capital.toString()
                        },
                        region = if (regionET.text.toString().trim().equals("null")) {
                            null
                        } else {
                            regionET.text.toString().trim().takeIf { it.isNotBlank() }
                                ?: country.region.toString()
                        },
                        subregion = if (subregionET.text.toString().trim().equals("null")) {
                            null
                        } else {
                            subregionET.text.toString().trim().takeIf { it.isNotBlank() }
                                ?: country.subregion.toString()
                        },
                        area = if (areaET.text.toString().trim().equals("-")) {
                            null
                        } else {
                            areaET.text.toString().toDoubleOrNull() ?: country.area
                        },
                        population = if (populationET.text.toString().trim().equals("-")) {
                            null
                        } else {
                            populationET.text.toString().toIntOrNull() ?: country.population
                        },
                        continent = if (continentET.text.toString().trim().equals("null")) {
                            null
                        } else {
                            continentET.text.toString().trim().takeIf { it.isNotBlank() }
                                ?: country.continent.toString()
                        }
                    )
                    if (paisViewModel.insert(entity)) {
                        paisViewModel.deleteById(country.id ?: 0)
                        Toast.makeText(con, "Edição efetuada: ID ${country.id}", Toast.LENGTH_SHORT)
                            .show()
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

    private fun buttonDeleteAlertDialog(country: Country) {
        val alertDialogBuilder = AlertDialog.Builder(con)
        val id: Int = country.id ?: 0
        alertDialogBuilder.setTitle("Deletar Id")
        alertDialogBuilder.setMessage("O Id ${country.id} será deletado, deseja continuar ?")
        alertDialogBuilder.setPositiveButton("Sim, deletar.") { dialog: DialogInterface, _: Int ->
            if (paisViewModel.deleteById(id)) {
                dialog.dismiss()
                Toast.makeText(con, "Id deletado.", Toast.LENGTH_SHORT).show()
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