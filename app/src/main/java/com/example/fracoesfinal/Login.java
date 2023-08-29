package com.example.fracoesfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText nome, sobrenome;
    Button iniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nome = findViewById(R.id.Nome);
        sobrenome = findViewById(R.id.Sobrenome);
        iniciar = findViewById(R.id.Iniciar);

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Recebe o nome e o sobrenome digitados pelo usuário.
                String nomeUsuario = nome.getText().toString();
                String sobrenomeUsuario = sobrenome.getText().toString();

                // Verifica se os campos estão preenchidos.
                if (nomeUsuario.trim().isEmpty() || sobrenomeUsuario.trim().isEmpty()) {
                    // Mostra uma mensagem de erro caso não estejam preenchidos.
                    Toast.makeText(Login.this, "Por favor, preencha o nome e o sobrenome.", Toast.LENGTH_SHORT).show();
                } else {
                    // Cria um novo Intent para a próxima tela.
                    Intent intent = new Intent(Login.this, MenuQuestoes.class);

                    // Adiciona o nome e o sobrenome do usuário ao Intent.
                    intent.putExtra("nomeUsuario", nomeUsuario);
                    intent.putExtra("sobrenomeUsuario", sobrenomeUsuario);

                    // Inicia a próxima tela.
                    startActivity(intent);
                }
            }
        });
    }
}