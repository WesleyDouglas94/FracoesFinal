package com.example.fracoesfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuDeAvaliacao extends AppCompatActivity {

    CardView card_questao_1, card_questao_2, card_questao_3, card_questao_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_de_avaliacao);

        card_questao_1 = findViewById(R.id.card_questao_1);
        card_questao_2 = findViewById(R.id.card_questao_2);
        card_questao_3 = findViewById(R.id.card_questao_3);
        card_questao_4 = findViewById(R.id.card_questao_4);


        card_questao_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuDeAvaliacao.this, Questao_Avaliacao_1.class);
                startActivity(intent);
            }
        });

        card_questao_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuDeAvaliacao.this, Questao_Avaliacao_2.class);
                startActivity(intent);
            }
        });

    }
}