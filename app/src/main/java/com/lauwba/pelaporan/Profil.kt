package com.lauwba.pelaporan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.activity_profil.*

class Profil : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        activity?.title = getString(R.string.title_beranda)
        val view = inflater.inflate(R.layout.activity_profil, container, false)
        return view

    }

    companion object {
        val TAG: String = Profil::class.java.simpleName
        fun newInstance() = Profil()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        username.text="" + Prefs.getString("user_username", "")
//        email.text="Your Email :" + Prefs.getString("user_email", "")
//        fullname.text="Full Name :" + Prefs.getString("user_fullname", "")
//        phone.text="Phone :" + Prefs.getString("user_phone", "")
//        address.text="Address :" + Prefs.getString("user_address", "")
//        logout.setOnClickListener { Prefs.clear()
//            activity?.supportFragmentManager?.beginTransaction()
//                ?.replace(R.id.coordinatorLayout,Login())
//                ?.commit()
//        }
    }
}
