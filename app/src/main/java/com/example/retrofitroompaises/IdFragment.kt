package com.example.retrofitroompaises

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.retrofitroompaises.viewModel.PaisViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class IdFragment(private val paisViewModel: PaisViewModel) : Fragment() {

    private lateinit var fabBackFragment: FloatingActionButton
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_id, container, false)

        fabBackFragment = view.findViewById(R.id.fragmentId_fabBackFragment)
        fabBackFragment.setOnClickListener{
            parentFragmentManager.popBackStack()
        }

        return view
    }
}
