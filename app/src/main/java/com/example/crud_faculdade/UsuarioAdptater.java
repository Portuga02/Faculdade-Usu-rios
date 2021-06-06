package com.example.crud_faculdade;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class UsuarioAdptater extends BaseAdapter {
    private List<Usuario> usuarios;
    private Activity activity;


    public UsuarioAdptater(Activity activity, List<Usuario> usuarios) {
        this.activity = activity;
        this.usuarios = usuarios;
    }

    @Override
    public int getCount() {
        return usuarios.size();
    }

    @Override
    public Object getItem(int position) {
        return usuarios.get(position);
    }

    @Override
    public long getItemId(int position) {

        return usuarios.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view = activity.getLayoutInflater().inflate(R.layout.item, viewGroup, false);
        TextView id = view.findViewById(R.id.txt_id);
        TextView nome = view.findViewById(R.id.txt_nome);
        Usuario usuario = usuarios.get(position);
        id.setText(usuario.getId().toString());
        nome.setText(usuario.getNome());
        return view;
    }
}
