package com.lauwba.pelaporan

import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import com.lauwba.pelaporan.config.Config
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList
import java.util.HashMap

class ViewTowerLamp : AppCompatActivity(), AdapterView.OnItemClickListener {

    private var listView: ListView? = null
    private var JSON_STRING: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_tower_lamp)
        listView = findViewById(R.id.listView) as ListView
        listView!!.setOnItemClickListener(this)
        getJSON()
    }

    private fun showEmployee() {
        var jsonObject: JSONObject? = null
        val list = ArrayList<HashMap<String, String>>()
        try {
            jsonObject = JSONObject(JSON_STRING)
            val result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY)

            for (i in 0 until result.length()) {
                val jo = result.getJSONObject(i)
                val id = jo.getString(Config.TAG_ID)
                val towerlamp = jo.getString(Config.TAG_TL)

                val employees = HashMap<String, String>()
                employees[Config.TAG_ID] = id
                employees[Config.TAG_TL] = towerlamp
                list.add(employees)
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val adapter = SimpleAdapter(
            this@ViewTowerLamp, list, R.layout.list_item,
            arrayOf(Config.TAG_ID, Config.TAG_TL),
            intArrayOf(R.id.id, R.id.towerlamp)
        )

        listView?.setAdapter(adapter)
    }

    private fun getJSON() {
        class GetJSON : AsyncTask<Void, Void, String>() {

            lateinit var loading: ProgressDialog
            override fun onPreExecute() {
                super.onPreExecute()
                loading = ProgressDialog.show(
                    this@ViewTowerLamp,
                    "Mengambil Data",
                    "Mohon Tunggu...",
                    false,
                    false
                )
            }

            override fun onPostExecute(s: String) {
                super.onPostExecute(s)
                loading.dismiss()
                JSON_STRING = s
                showEmployee()
            }

            override fun doInBackground(vararg params: Void): String {
                val rh = RequestHandler()
                return rh.sendGetRequest(Config.URL_GET_ALL)
            }
        }

        val gj = GetJSON()
        gj.execute()
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val intent = Intent(this, TampilTl::class.java)
        val map = parent?.getItemAtPosition(position) as HashMap<*, *>
        val empId = map[Config.TAG_ID]!!.toString()
        intent.putExtra(Config.EMP_ID, empId)
        startActivity(intent)
    }

}
