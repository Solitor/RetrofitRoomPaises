package com.example.retrofitroompaises

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment

class StartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_start, container, false)

        view.findViewById<ConstraintLayout>(R.id.fragmentStart_ConstraintLayout).setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.masterFrameLayout, MainFragment())
                .commit()
        }

        return view
    }
}