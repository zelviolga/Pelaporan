package com.lauwba.pelaporan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_tambah.*

class Tambah : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        activity?.title = getString(R.string.title_beranda)
        val view = inflater.inflate(R.layout.activity_tambah, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btntl.setOnClickListener {
            startActivity(Intent(activity, InputTowerLamp::class.java))
        }

//        btntl.setOnClickListener {
//            var intent = intent
//            intent = Intent(this@Tambah, InputTowerLamp::class.java)
//            startActivity(intent)
//        }
    }
}
