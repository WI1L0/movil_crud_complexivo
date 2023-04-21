package com.example.crud_productos;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crud_productos.Database.DB;

public class Login extends AppCompatActivity {

    private Button loginb;

    private EditText loginuser, loginpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginn);

        SQLiteDatabase sdb = (new DB(Login.this).getWritableDatabase());
        if (sdb != null){
            Toast.makeText(this, "BASE DE DATOS CREADA", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "ERROR EN CREAR BASE DE DATOS ", Toast.LENGTH_SHORT).show();
        }

        loginb = (Button) findViewById(R.id.loginboton);
        loginuser = (EditText) findViewById(R.id.loginuser);
        loginpass = (EditText) findViewById(R.id.loginpassword);


        loginb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!loginuser.getText().toString().equals("Admin")){
                    Toast.makeText(Login.this, "Usuario erroneo", Toast.LENGTH_SHORT).show();
                } else if (!loginpass.getText().toString().equals("Admin")){
                    Toast.makeText(Login.this, "Contrase;a erronea", Toast.LENGTH_SHORT).show();
                }

                if (loginuser.getText().toString().equals("Admin") && loginpass.getText().toString().equals("Admin")){
                    Toast.makeText(Login.this, "Bienvenido", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Login.this, Inicio.class);
                    startActivity(intent);
                }
            }
        });

    }
}
