package com.example.retrofitroompaises.handler

import android.view.View
import com.example.retrofitroompaises.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MenuHandler(view: View) {
    var fabMenu: FloatingActionButton = view.findViewById(R.id.fragmentMain_fabMenu)
    var fabBack: FloatingActionButton = view.findViewById(R.id.fragmentMain_fabBack)
    var fabAdd: FloatingActionButton = view.findViewById(R.id.fragmentMain_fabAdd)
    var fabDelete: FloatingActionButton = view.findViewById(R.id.fragmentMain_fabDelete)
    var fabReset: FloatingActionButton = view.findViewById(R.id.fragmentMain_fabReset)
    var fabFilterFragment: FloatingActionButton = view.findViewById(R.id.fragmentMain_fabFilterFragment)
    var fabIdFragment: FloatingActionButton = view.findViewById(R.id.fragmentMain_fabIdFragment)
    var fabLogFragment: FloatingActionButton = view.findViewById(R.id.fragmentMain_fabLogFragment)

    fun closeMenu() {
        fabMenu.visibility = View.VISIBLE
        fabBack.visibility = View.GONE
        fabAdd.visibility = View.GONE
        fabDelete.visibility = View.GONE
        fabReset.visibility = View.GONE
    }

    fun openMenu() {
        fabMenu.visibility = View.GONE
        fabBack.visibility = View.VISIBLE
        fabAdd.visibility = View.VISIBLE
        fabDelete.visibility = View.VISIBLE
        fabReset.visibility = View.VISIBLE
    }
}
