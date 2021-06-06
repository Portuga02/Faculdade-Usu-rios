package com.example.crud_faculdade;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UsuariosDAO {
    private ConexaoSqlite conexaoSqlite;
    private SQLiteDatabase banco;

    public UsuariosDAO(Context context) {
        conexaoSqlite = new ConexaoSqlite(context);
        banco = conexaoSqlite.getReadableDatabase();

    }

    public List<Usuario> obterUsuariosCadastrados() {
        List<Usuario> usuarios = new ArrayList<>();
        Cursor cursor;
        cursor = banco.query("usuarios", new String[]{"id", "nome", "cpf", "idade", "sexo", "email, telefone"}, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            Usuario usuario = new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNome(cursor.getString(1));
            usuario.setCPF(cursor.getString(2));
            usuario.setIdade(cursor.getString(3));
            usuario.setSexo(cursor.getString(4));
            usuario.setEmail(cursor.getString(5));
            usuario.setTelefone(cursor.getString(6));
            usuarios.add(usuario);
        }
        return usuarios;
    }

    public long inserir(Usuario usuario) {
        ContentValues values = new ContentValues();
        values.put("nome", usuario.getNome());
        values.put("cpf", usuario.getCPF());
        values.put("idade", usuario.getIdade());
        values.put("sexo", usuario.getSexo());
        values.put("email", usuario.getEmail());
        values.put("telefone", usuario.getTelefone());

        return banco.insert("usuarios", null, values);
    }


    public void excluir(Usuario usuario) {

        banco.delete("usuarios", "id = ?", new String[]{usuario.getId().toString()});

    }

    public void atualizar(Usuario usuario) {
        ContentValues values = new ContentValues();
        values.put("nome", usuario.getNome());
        values.put("cpf", usuario.getCPF());
        values.put("idade", usuario.getIdade());
        values.put("sexo", usuario.getSexo());
        values.put("email", usuario.getEmail());
        values.put("telefone", usuario.getTelefone());

        banco.update("usuarios", values, "id = ?", new String[]{usuario.getId().toString()});
    }
}
