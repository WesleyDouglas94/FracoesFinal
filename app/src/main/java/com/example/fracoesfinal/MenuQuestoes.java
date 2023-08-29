package com.example.fracoesfinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MenuQuestoes extends AppCompatActivity {

    CardView card_introducao, card_avaliacao;
    EditText senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_questoes);

        card_introducao = findViewById(R.id.card_introducao);
        card_avaliacao = findViewById(R.id.card_avaliacao);

        card_introducao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Solicita uma senha ao usuário
                AlertDialog.Builder builder = new AlertDialog.Builder(MenuQuestoes.this);
                builder.setTitle("Senha necessária");
                builder.setMessage("Digite sua senha");

                // Adiciona um campo de texto à AlertDialog
                final EditText passwordEditText = new EditText(MenuQuestoes.this);
                builder.setView(passwordEditText);

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Faz algo com a senha inserida pelo usuário
                        String senhaDigitada = passwordEditText.getText().toString();

                        if (senhaDigitada.equals("+")) {
                            // A senha está correta, então direciona o usuário para a tela de introdução
                            Intent intent = new Intent(MenuQuestoes.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            // A senha está incorreta, então exibe uma mensagem de erro
                            Toast.makeText(MenuQuestoes.this, "Senha incorreta", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                builder.setNegativeButton("Cancelar", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        card_avaliacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Solicita uma senha ao usuário
                AlertDialog.Builder builder = new AlertDialog.Builder(MenuQuestoes.this);
                builder.setTitle("Senha necessária");
                builder.setMessage("Digite sua senha");

                // Adiciona um campo de texto à AlertDialog
                final EditText passwordEditText = new EditText(MenuQuestoes.this);
                builder.setView(passwordEditText);

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Faz algo com a senha inserida pelo usuário
                        String senhaDigitada = passwordEditText.getText().toString();

                        if (senhaDigitada.equals("")) {
                            // A senha está correta, então direciona o usuário para a tela de introdução
                            Intent intent = new Intent(MenuQuestoes.this, MenuDeAvaliacao.class);
                            startActivity(intent);
                        } else {
                            // A senha está incorreta, então exibe uma mensagem de erro
                            Toast.makeText(MenuQuestoes.this, "Senha incorreta", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                builder.setNegativeButton("Cancelar", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}