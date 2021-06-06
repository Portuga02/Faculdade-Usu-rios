package com.example.crud_faculdade;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CadastroUsuariosActivity extends AppCompatActivity {


    private EditText nome;
    private EditText CPF;
    private EditText idade;
    private EditText Sexo;
    private EditText Email;
    private EditText Telefone;
    private UsuariosDAO dao;
    private Usuario usuario = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = findViewById(R.id.id_nome);
        CPF = findViewById(R.id.Id_cpf);
        idade = findViewById(R.id.id_idade);
        Sexo = findViewById(R.id.id_sexo);
        Email = findViewById(R.id.id_email);
        Telefone = findViewById(R.id.id_telefone);
        dao = new UsuariosDAO(this);

        Intent it = getIntent();
        if (it.hasExtra("usuario")) {
            usuario = (Usuario) it.getSerializableExtra("usuario");
            nome.setText(usuario.getNome());
            CPF.setText(usuario.getCPF());
            idade.setText(usuario.getIdade());
            Sexo.setText(usuario.getSexo());
            Email.setText(usuario.getEmail());
            Telefone.setText(usuario.getTelefone());

        }


    }


    public void salvar(View view) {

        if (usuario == null) {
            Usuario usuario = new Usuario();
            usuario.setNome(nome.getText().toString());
            usuario.setCPF(CPF.getText().toString());
            usuario.setIdade(idade.getText().toString());
            usuario.setSexo(Sexo.getText().toString());
            usuario.setEmail(Email.getText().toString());
            usuario.setTelefone(Telefone.getText().toString());
            long id = dao.inserir(usuario);
            Toast.makeText(this, " O Usuário cadastrado com sucesso" + Toast.LENGTH_SHORT + id, Toast.LENGTH_LONG).show();
        } else {
            usuario.setNome(nome.getText().toString());
            usuario.setCPF(CPF.getText().toString());
            usuario.setIdade(idade.getText().toString());
            usuario.setSexo(Sexo.getText().toString());
            usuario.setEmail(Email.getText().toString());
            usuario.setTelefone(Telefone.getText().toString());
            dao.atualizar(usuario);
            Toast.makeText(this, " o Aluno foi Atualizado com sucesso" + Toast.LENGTH_SHORT, Toast.LENGTH_LONG).show();

        }

    }


}