package com.example.retrofitroompaises.viewModel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitroompaises.R
import com.example.retrofitroompaises.model.ChangeLog
import com.example.retrofitroompaises.model.Country
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class LogAdapter(var con: Context, var paisViewModel: PaisViewModel) :
    RecyclerView.Adapter<LogAdapter.ViewHolder>() {

    var showingList: List<ChangeLog> = emptyList()
    val booleanMutableList: MutableList<Boolean> = MutableList(showingList.size) { false }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var titleTV = v.findViewById<TextView>(R.id.logAdapter_TitleTextView)
        var colapsableLL = v.findViewById<LinearLayout>(R.id.logAdapter_ColapsableLayout)
        var timestampTV = v.findViewById<TextView>(R.id.logAdapter_TimestampTextView)
        var OperationTV = v.findViewById<TextView>(R.id.logAdapter_OperationTextView)
        var ChangesTV = v.findViewById<TextView>(R.id.logAdapter_ChangesTextView)
        var paginaTV = v.findViewById<TextView>(R.id.logAdapter_PaginaTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("LOOKOUT_UAV2", "onCreateViewHolder")
        val view = LayoutInflater.from(con).inflate(R.layout.list_log, parent, false)
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

        val date = Date(showingList[position].timestamp)
        val displayFormatFull = SimpleDateFormat("dd/MM/yyyy, HH:mm:ss.SSS", Locale.getDefault())
        val displayFormatSimple = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val formattedTimestampFull = displayFormatFull.format(date)
        val formattedTimestampSimple = displayFormatSimple.format(date)

        holder.titleTV.text = "ID : ${showingList[position].countryId}"
        holder.timestampTV.text = "Timestamp: \n=> ${formattedTimestampFull}"
        holder.OperationTV.text = "Operation: \n=> ${showingList[position].operation}"
        holder.paginaTV.text =
            "=> " + position.plus(1).toString() + " out of " + showingList.size.toString()

        if(showingList[position].operation.equals("INSERT")) {
            val countryAfter = jsonToCountry(showingList[position].after!!)!!
            var changes = "Changes \n\n"
            if (countryAfter.id != null) {
                changes += "\t=> Id:\n\t\tTo ${countryAfter.id}\n\n"
            }
            if(countryAfter.name != ""){
                changes += "\t=> Name:\n\t\tTo ${countryAfter.name}\n\n"
            }
            if(countryAfter.official != ""){
                changes += "\t=> Official:\n\t\tTo ${countryAfter.official.toString()}\n\n"
            }
            if(countryAfter.acronym != ""){
                changes += "\t=> Acronym:\n\t\tTo ${countryAfter.acronym.toString()}\n\n"
            }
            if(countryAfter.capital != ""){
                changes += "\t=> Capital:\n\t\tTo ${countryAfter.capital.toString()}\n\n"
            }
            if(countryAfter.region != ""){
                changes += "\t=> Region:\n\t\tTo ${countryAfter.region.toString()}\n\n"
            }
            if(countryAfter.subregion != ""){
                changes += "\t=> Subregion:\n\t\tTo ${countryAfter.subregion.toString()}\n\n"
            }
            if(countryAfter.area != null){
                changes += "\t=> Area:\n\t\tTo ${countryAfter.area}\n\n"
            }
            if(countryAfter.population != null){
                changes += "\t=> Population:\n\t\tTo ${countryAfter.population}\n\n"
            }
            if(countryAfter.continent != ""){
                changes += "\t=> Continent:\n\t\tTo ${countryAfter.continent.toString()}\n\n"
            }
            holder.ChangesTV.text = changes
        }

        else if(showingList[position].operation.equals("UPDATE")){
            val countryBefore = jsonToCountry(showingList[position].before!!)!!
            val countryAfter = jsonToCountry(showingList[position].after!!)!!
            var changes = "Changes\n\n"
            if(countryAfter.id != countryBefore.id){
                changes += "\t=> Id:\n\t\tFrom ${countryBefore.id}\n\t\tTo ${countryAfter.id}\n\n"
            }
            if(countryAfter.name != countryBefore.name){
                changes += "\t=> Name:\n\t\tFrom ${countryBefore.name}\n\t\tTo ${countryAfter.name}\n\n"
            }
            if(countryAfter.official != countryBefore.official){
                changes += "\t=> Official:\n\t\tFrom ${countryBefore.official.toString()}\n\t\tTo ${countryAfter.official.toString()}\n\n"
            }
            if(countryAfter.acronym != countryBefore.acronym){
                changes += "\t=> Acronym:\n\t\tFrom ${countryBefore.acronym.toString()}\n\t\tTo ${countryAfter.acronym.toString()}\n\n"
            }
            if(countryAfter.capital != countryBefore.capital){
                changes += "\t=> Capital:\n\t\tFrom ${countryBefore.capital.toString()}\n\t\tTo ${countryAfter.capital.toString()}\n\n"
            }
            if(countryAfter.region != countryBefore.region){
                changes += "\t=> Region:\n\t\tFrom ${countryBefore.region.toString()}\n\t\tTo ${countryAfter.region.toString()}\n\n"
            }
            if(countryAfter.subregion != countryBefore.subregion){
                changes += "\t=> Subregion:\n\t\tFrom ${countryBefore.subregion.toString()}\n\t\tTo ${countryAfter.subregion.toString()}\n\n"
            }
            if(countryAfter.area != countryBefore.area){
                changes += "\t=> Area:\n\t\tFrom ${countryBefore.area}\n\t\tTo ${countryAfter.area}\n\n"
            }
            if(countryAfter.population != countryBefore.population){
                changes += "\t=> Population:\n\t\tFrom ${countryBefore.population}\n\t\tTo ${countryAfter.population}\n\n"
            }
            if(countryAfter.continent != countryBefore.continent){
                changes += "\t=> Continent:\n\t\tFrom ${countryBefore.continent.toString()}\n\t\tTo ${countryAfter.continent.toString()}\n\n"
            }
            holder.ChangesTV.text = changes
        }

        else /* if(showingList[position].operation.equals("DELETE")) */{
            val countryBefore = jsonToCountry(showingList[position].before!!)!!
            var changes = "Changes\n\n"
            if(countryBefore.id != null){
                changes += "\t=> Id:\n\t\t${countryBefore.id}\n\t\t\tdeleted\n\n"
            }
            if(countryBefore.name != ""){
                changes += "\t=> Name:\n\t\t${countryBefore.name}\n\t\t\tdeleted\n\n"
            }
            holder.ChangesTV.text = changes

        }

        if (booleanMutableList[position]) {
            holder.colapsableLL.visibility = View.VISIBLE
            holder.titleTV.text = "ID modified: ${showingList[position].countryId}"
        } else {
            holder.colapsableLL.visibility = View.GONE
            holder.titleTV.text = "${formattedTimestampSimple}\nID : ${showingList[position].countryId}, ${showingList[position].operation}"
        }
        holder.titleTV.setOnClickListener {
            if (booleanMutableList[position]) {
                holder.colapsableLL.visibility = View.GONE
                holder.titleTV.text = "${formattedTimestampSimple}\nID : ${showingList[position].countryId}, ${showingList[position].operation}"
                booleanMutableList[position] = false
            } else {
                holder.colapsableLL.visibility = View.VISIBLE
                holder.titleTV.text = "ID modified: ${showingList[position].countryId}"
                booleanMutableList[position] = true
            }
        }
    }

    fun updateData(newData: List<ChangeLog>) {
        Log.d("LOOKOUT_UAV2", "updateData")
        showingList = newData
        booleanMutableList.clear()
        booleanMutableList.addAll(MutableList(showingList.size) { false })
        notifyDataSetChanged()
    }
    fun jsonToCountry(jsonString: String): Country? {
        val gson = Gson()
        return gson.fromJson(jsonString, Country::class.java)
    }
}