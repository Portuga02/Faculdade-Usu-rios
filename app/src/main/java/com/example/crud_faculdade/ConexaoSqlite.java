package com.example.crud_faculdade;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexaoSqlite extends SQLiteOpenHelper {

    private static final String name = "Banco.db";
    private static final int version = 1;

    public ConexaoSqlite(@Nullable Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE usuarios (id integer primary key autoincrement," +
                "nome varchar (50)," +
                "cpf varchar (50)," +
                "idade varchar (3)," +
                "sexo varchar (6)," +
                "email varchar(50)," +
                "telefone varchar(15)" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
