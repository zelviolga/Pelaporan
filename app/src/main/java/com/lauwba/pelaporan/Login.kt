package com.lauwba.pelaporan

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.lauwba.pelaporan.config.Config
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class Login : AppCompatActivity() {

    private var pd: ProgressDialog?=null
    private var params:HashMap<String,String>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        params= hashMapOf()
        login.setOnClickListener {
            get_data_login().execute()
        }
    }

    inner class get_data_login : AsyncTask<String, Void, String>(){

        override fun onPreExecute() {   //method from asyntask, ecxecuted in first thread before Async excecution
            super.onPreExecute()
            pd= ProgressDialog.show(this@Login,"","Wait",true,true)
        }

        @SuppressLint("WrongThread")
        override fun doInBackground(vararg param: String?): String {    //method Async

            val handler= RequestHandler()
            params?.put("username",username.text.toString())
            params?.put("password",password.text.toString())

            val result= params?.let { handler.sendPostRequest(Config.url_login, it) }
            return result?:""
        }

        override fun onPostExecute(result: String?) {   //method Async result
            super.onPostExecute(result)
            pd?.dismiss()
            try{
                Log.d("result",result)
                val objek= JSONObject(result)
                if (objek.getInt("status")==1){
                    Toast.makeText(this@Login, "Login Failed!", Toast.LENGTH_SHORT).show()
                }else{
//                    Prefs.putString("user_username",username.text.toString())
                    Prefs.putString("user_username", username.text.toString())

                    intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this@Login, "Login Success!", Toast.LENGTH_SHORT).show()
                }
            }catch(e:Exception){
                e.printStackTrace()
            }

        }

    }
}
