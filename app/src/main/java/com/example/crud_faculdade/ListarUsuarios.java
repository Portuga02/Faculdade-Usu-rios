package com.example.crud_faculdade;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ListarUsuarios extends AppCompatActivity {

    private ListView listView;
    private UsuariosDAO dao;
    private List<Usuario> usuarios;
    private List<Usuario> usuariosFiltrados = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_usuarios);
        listView = findViewById(R.id.listar_usuarios);
        dao = new UsuariosDAO(this);
        usuarios = dao.obterUsuariosCadastrados();
        usuariosFiltrados.addAll(usuarios);  /*TRABALHANDO COM DUAS LISTAS PARA OBTER OS RESULTADOS CONSULTADOS*/
        // ArrayAdapter<Usuario> adaptador = new ArrayAdapter<Usuario>(this, android.R.layout.simple_dropdown_item_1line, usuariosFiltrados);
        UsuarioAdptater adaptador = new UsuarioAdptater(this, usuariosFiltrados);
        listView.setAdapter(adaptador);
        registerForContextMenu(listView);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal, menu);

        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                procuraUsuarios(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        return true;


    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto, menu);
    }

    public void procuraUsuarios(String Nome) {
        usuariosFiltrados.clear();
        for (Usuario usuarios : usuarios) {
            if (usuarios.getNome().toLowerCase().contains(Nome.toLowerCase())) {
                usuariosFiltrados.add(usuarios);
            }
        }
        listView.invalidateViews();
    }

    public void excluir(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Usuario usuarioExcluir = usuariosFiltrados.get(menuInfo.position);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Realmente deseja excluir o usuário selecionado ?")
                .setNegativeButton("NÃO", null)

                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        usuariosFiltrados.remove(usuarioExcluir);
                        usuarios.remove(usuarioExcluir);
                        dao.excluir(usuarioExcluir);
                        listView.invalidateViews();


                    }
                }).create();

        dialog.show();

    }

    public void atualizar(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Usuario usuarioAtualizar = usuariosFiltrados.get(menuInfo.position);
        Intent it = new Intent(this, CadastroUsuariosActivity.class);
        it.putExtra("usuarios", usuarioAtualizar);
        startActivity(it);

    }

    public void Cadastrar(MenuItem item) {
        Intent it = new Intent(this, CadastroUsuariosActivity.class);
        startActivity(it);

    }


    public void onResume() {
        super.onResume();
        usuarios = dao.obterUsuariosCadastrados();
        usuariosFiltrados.clear();
        usuariosFiltrados.addAll(usuarios);
        listView.invalidateViews();

    }
}