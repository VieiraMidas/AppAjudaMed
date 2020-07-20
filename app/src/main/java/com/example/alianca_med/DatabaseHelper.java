package com.example.alianca_med;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.session.PlaybackState;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, "DBaliaca_med.sql", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tbUsuarios (nome text, email text, cnpj text primary key, cep text, logradouro text, telefone text, senha text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists tbUsuarios");
    }

    public boolean insert(String nome, String email, String cnpj, String cep, String logradouro, String telefone, String senha) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", nome);
        contentValues.put("email", email);
        contentValues.put("cnpj", cnpj);
        contentValues.put("cep", cep);
        contentValues.put("logradouro", logradouro);
        contentValues.put("telefone", telefone);
        contentValues.put("senha", senha);

        long inserido = db.insert("tbUsuarios",null, contentValues);

        if (inserido == 1){
            return false;
        } else {
            return true;
        }
    }
    public Boolean validarCNPJ (String cnpj){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from tbUsuarios where cnpj = ?", new String[]{cnpj});
        if (cursor.getCount() > 0) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean validarEmailSenha (String email, String senha){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from tbUsuarios where email = ? and senha = ?", new String[]{email,senha});
        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }
}
