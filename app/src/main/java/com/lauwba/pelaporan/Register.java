package com.lauwba.pelaporan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

public class Register extends AppCompatActivity {

    Intent intent;
    EditText username, regPass, regConfPass, email, fullname, phone, address ;
    Button btnReg;
    int success;
    TextView textlogin;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.emailregister);
        regPass = findViewById(R.id.password);
        regConfPass = findViewById(R.id.confirmpassword);

        btnReg = findViewById(R.id.btndaftar);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                runningReg(username, regPass, regConfPass);

            }
        });

    }

    private void runningReg(final EditText username, final EditText regPass, final EditText regConfPass) {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Register ...");
        showDialog();
        AndroidNetworking.post("http://192.168.18.38/pelaporan/api/register.php")
                .addBodyParameter("id", "") //id bersifat Auto_Increment tidak perlu diisi/(diisi NULL) cek create.php
                .addBodyParameter("user_username", username.getText().toString()) //mengirimkan data nama yang akan diisi dengan varibel nama
                .addBodyParameter("user_password", regPass.getText().toString()) //mengirimkan data agama yang akan diisi dengan varibel agama
                .addBodyParameter("confirm_password", regConfPass.getText().toString())
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {

                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            success = response.getInt("success");
                            if (success ==1){
                                Toast.makeText(getApplicationContext(),
                                        response.getString("message"), Toast.LENGTH_LONG).show();

                                //action kalo sudah berhasil register isian di kolom akan hilang
                                username.setText("");
                                regPass.setText("");
                                regConfPass.setText("");
                            }else{
                                Toast.makeText(getApplicationContext(),
                                        response.getString("message"), Toast.LENGTH_LONG).show();
                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        hideDialog();
                    }
                });
    }

    private void showMessage(String message) {
        Toast.makeText(Register.this, message, Toast.LENGTH_LONG).show();
    }

    public void login(View view) {
        intent = new Intent(Register.this, Login.class);
        finish();
        startActivity(intent);
    }
    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
