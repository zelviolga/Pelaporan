package com.lauwba.pelaporan

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(com.lauwba.pelaporan.R.id.coordinatorLayout, Data())
            .commit()

        bottom.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                com.lauwba.pelaporan.R.id.navigation_data -> {
                    supportFragmentManager.beginTransaction()
                        .replace(com.lauwba.pelaporan.R.id.coordinatorLayout, Data())
                        .commit()
                    return@setOnNavigationItemSelectedListener true
                }

                com.lauwba.pelaporan.R.id.navigation_tambah -> {
                    supportFragmentManager.beginTransaction()
                        .replace(com.lauwba.pelaporan.R.id.coordinatorLayout, Tambah())
                        .commit()
//                    intent = Intent(applicationContext, Help::class.java)
//                    startActivity(intent)
                    return@setOnNavigationItemSelectedListener true
                }


                com.lauwba.pelaporan.R.id.navigation_profil -> {
//                    supportFragmentManager.beginTransaction()
//                        .replace(com.lauwba.pelaporan.R.id.coordinatorLayout, Profil())
//                        .commit()

                    //untuk code jika sudah login maka yg tampil adalah hal data yg sudah login
                    if(Prefs.contains("user_username")){
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.coordinatorLayout,Profil())
                            .commit()
                    }else{
//            judul.text="Login"
//                        supportFragmentManager.beginTransaction()
//                            .replace(R.id.coordinatorLayout,Login())
//                            .commit()
                        intent = Intent(applicationContext, Login::class.java)
                        startActivity(intent)
                    }
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var menuInflate = menuInflater
        menuInflate.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.getItemId()){

            R.id.menu_settings -> {
                Toast.makeText(applicationContext, "Feature is locked", Toast.LENGTH_LONG).show()
            }
            R.id.menu_profile -> {
                supportFragmentManager.beginTransaction()
                    .replace(com.lauwba.pelaporan.R.id.coordinatorLayout, Profil())
                    .commit()
            }

            R.id.menu_logout -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }



    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setMessage("Keluar dari Aplikasi?")
        builder.setPositiveButton("Ya", DialogInterface.OnClickListener { dialog, which ->
            //if user pressed "yes", then he is allowed to exit from application
            finish()
        })
        builder.setNegativeButton("Tidak", DialogInterface.OnClickListener { dialog, which ->
            //if user select "No", just cancel this dialog and continue with app
            dialog.cancel()
        })
        val alert = builder.create()
        alert.show()
    }
}
