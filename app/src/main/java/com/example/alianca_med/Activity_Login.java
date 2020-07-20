package com.example.alianca_med;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_Login extends AppCompatActivity {

    EditText txtLoginEmail, txtLoginSenha;
    Button btnLoginEntrar, btnLoginCadastro;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout__login);

        txtLoginEmail = findViewById(R.id.idLoginemail);
        txtLoginSenha = findViewById(R.id.idLoginsenha);

        db = new DatabaseHelper(this);

        btnLoginEntrar = findViewById(R.id.idbtnLogar);

        btnLoginEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, senha;

                email = txtLoginEmail.getText().toString();
                senha = txtLoginSenha.getText().toString();

                Boolean checarEmailSenha = db.validarEmailSenha(email,senha);

                if (checarEmailSenha == true){
                    Log.i("btnLoginEntrar","Cliquei no botão entrar do login");
                    Intent intent = new Intent(getApplicationContext(), Activity_Home.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(),"Acesso Negado!",Toast.LENGTH_SHORT).toString();
                }
            }
        });

        btnLoginCadastro = findViewById(R.id.idbtnCadastro);

        btnLoginCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Activity_Cadastro.class);
                startActivity(intent);
            }
        });
    }
}