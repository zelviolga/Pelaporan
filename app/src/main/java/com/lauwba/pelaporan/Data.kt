package com.lauwba.pelaporan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class Data : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        activity?.title = getString(R.string.title_beranda)
        val view = inflater.inflate(R.layout.activity_data, container, false)
        return view
    }
}
