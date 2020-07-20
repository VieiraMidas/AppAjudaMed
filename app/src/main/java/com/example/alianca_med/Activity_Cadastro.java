package com.example.alianca_med;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_Cadastro extends AppCompatActivity {

    DatabaseHelper db;

    EditText txtNome, txtEmail, txtCNPJ, txtCEP, txtLogradouro, txtTelefone, txtSenha, txtConfirmarSenha;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout__cadastro);

        db = new DatabaseHelper(this);

        txtNome = findViewById(R.id.idnome);
        txtEmail = findViewById(R.id.idemail);
        txtCNPJ = findViewById(R.id.idcnpj);
        txtCEP = findViewById(R.id.idcep);
        txtLogradouro = findViewById(R.id.idlogradouro);
        txtTelefone = findViewById(R.id.idlogradouro);
        txtSenha = findViewById(R.id.idsenha);
        txtConfirmarSenha = findViewById(R.id.idconfirmarsenha);

        btnRegistrar = findViewById(R.id.idBtnRegistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome, email, cnpj, cep, logradouro, telefone, senha, confirmarSenha;

                nome = txtNome.getText().toString();
                email = txtEmail.getText().toString();
                cnpj = txtCNPJ.getText().toString();
                cep = txtCEP.getText().toString();
                logradouro = txtLogradouro.getText().toString();
                telefone = txtTelefone.getText().toString();
                senha = txtSenha.getText().toString();
                confirmarSenha = txtConfirmarSenha.getText().toString();

                if (nome.equals("") || email.equals("") || cnpj.equals("") || cep.equals("") || logradouro.equals("") || telefone.equals("") || senha.equals("") || confirmarSenha.equals("")){
                    Toast.makeText(getApplicationContext(), "Favor, inserir valores!",Toast.LENGTH_SHORT).show();
                } else {
                    if (senha.equals(confirmarSenha)) {
                        Boolean checarCNPJ = db.validarCNPJ(cnpj);
                        if (checarCNPJ == true){
                            Boolean inserir = db.insert(nome , email , cnpj , cep , logradouro, telefone , senha);
                            if (inserir == true){
                                Toast.makeText(getApplicationContext(), "Registro inserido com sucesso!", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(getApplicationContext(),"CNPJ inserido já existe", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(),"Senha não confere!",Toast.LENGTH_SHORT).show();
                    }
                }

                Intent intent = new Intent(getApplicationContext(), Activity_Login.class);
                startActivity(intent);
            }
        });
    }
}