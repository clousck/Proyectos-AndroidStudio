package com.example.myapplicationutn007;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplicationutn007.SQLite.Usuarios;

public class LoginActivity extends AppCompatActivity {

    EditText usuario, contrasenia;
    Usuarios lstUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        usuario = findViewById(R.id.usernameEditText);
        contrasenia = findViewById(R.id.passwordEditText);
        lstUsuarios = new Usuarios(this, "biblioteca.db", 1);
    }

    public void Login(View view)
    {
        boolean isAutenitated = lstUsuarios.login(usuario.getText().toString(), contrasenia.getText().toString());
        if(isAutenitated)
        {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else
        {
            usuario.setText("");
            contrasenia.setText("");
        }
    }
}