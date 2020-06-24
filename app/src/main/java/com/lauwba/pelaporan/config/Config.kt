package com.lauwba.pelaporan.config

object Config {

    private const val Host="http://192.168.18.38/pelaporan/" //yang bakal berganti
    val url_login =  Host+"index.php/Login/select/"

    val URL_ADD = "http://192.168.18.38/pelaporan/api/tambahtl.php"
    val URL_GET_ALL = "http://192.168.18.38/pelaporan/api/tampilsemuatl.php"


    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    val KEY_EMP_ID = "id"
    val KEY_EMP_TL = "towerlamp"
    val KEY_EMP_SHIFT = "shift"
    val KEY_EMP_STATUS = "status" //desg itu variabel untuk posisi
    val KEY_EMP_HM = "hm" //salary itu variabel untuk gajih
    val KEY_EMP_FUEL = "fuel" //salary itu variabel untuk gajih

    //JSON Tags
    val TAG_JSON_ARRAY = "result"
    val TAG_ID = "id"
    val TAG_TL = "towerlamp"
    val TAG_POSISI = "desg"
    val TAG_GAJIH = "salary"

    //ID karyawan
    //emp itu singkatan dari Employee
    val EMP_ID = "emp_id"
}