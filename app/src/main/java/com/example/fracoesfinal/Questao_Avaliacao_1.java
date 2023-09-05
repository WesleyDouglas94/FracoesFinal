package com.example.fracoesfinal;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fracoesfinal.banco.DatabaseHelper;
import com.example.fracoesfinal.banco.Question;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;




public class Questao_Avaliacao_1 extends AppCompatActivity {

    // Declare suas variáveis PieChart
    private PieChart graficoPizza1;
    private PieChart graficoPizza2;
    private int selectedDenominator = 1;
    private int selectedNumerator = 1;
    private boolean isGraph1Editable = true;
    private boolean isGraph2Editable = true;
    private boolean isGraph3Editable = true;

    private PieEntry lastSelectedSlice = null;

     // Substitua pelo valor correto que você está esperando
    private String respostaCorreta2 = "1/4"; // Substitua pelo valor correto que você está esperando

    private String respostaCorreta3 = "3/4";

    TextView respostaCorreta1;

    private float sumOfSelectedSlices = 0;
    private float sumOfSelectedSlices2 = 0;

    private int selectedSliceIndex = -1;
    private ArrayList<PieEntry> entradasGrafico1 = new ArrayList<>();

    private List<Integer> sliceColors = new ArrayList<>();


    private TextView perguntaPrincipal, parte1, parte2;





    // Declare outras variáveis e views necessárias

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questao_avaliacao1);

        TextView textViewSum1 = findViewById(R.id.textViewSum1);
        TextView textViewSum2 = findViewById(R.id.textViewSum2);


        perguntaPrincipal = findViewById(R.id.Pergunta);
        parte1 = findViewById(R.id.perguntaParte1);
        parte2 = findViewById(R.id.perguntaParte2);




    //BANCO DE DADOS
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Inserir a primeira pergunta
        ContentValues values = new ContentValues();
        values.put("perguntaPrincipal", "Sua mãe está fazendo uma salada de frutas e quer adicionar melancia. Ela cortou 1/6 de uma melancia em cubos e adicionou à salada, e depois decidiu adicionar mais 1/6 de melancia. Qual será a quantidade total de melancia na salada?");
        values.put("parte1", "Insira o denominador no gráfico e depois selecione o número de fatias para a primeira parte da fração.");
        values.put("parte2", "Insira o denominador no gráfico e depois selecione o número de fatias para a segunda parte da fração.");
        values.put("resposta_parte1", "1/6");
        values.put("resposta_parte2", "1/6");
        values.put("resposta_correta", "2/6");
        db.insert("pergunta", null, values);
        // Inserir a segunda pergunta
        values.clear();
        values.put("perguntaPrincipal", "Se você cortar a melancia em 10 fatias iguais e der 4 fatias para os adultos e 3 fatias para as crianças, responda qual a fração que representa a quantidade de fatias que foi dividida");
        values.put("parte1", "Insira o denominador no gráfico e depois selecione o número de fatias para a primeira parte da fração.");
        values.put("parte2", "Insira o denominador no gráfico e depois selecione o número de fatias para a segunda parte da fração.");
        values.put("resposta_parte1", "4/10");
        values.put("resposta_parte2", "3/10");
        values.put("resposta_correta", "7/10");
        db.insert("pergunta", null, values);

        // Inserir a terceira pergunta
        values.clear();
        values.put("perguntaPrincipal", "Uma pesa 3/4 de quilo e a outra pesa 5/4 de quilo. Qual é o´peso total das duas melancias??");
        values.put("parte1", "Insira o denominador no gráfico e depois selecione o número de fatias para a primeira parte da fração.");
        values.put("parte2", "Insira o denominador no gráfico e depois selecione o número de fatias para a segunda parte da fração.");
        values.put("resposta_parte1", "5/4");
        values.put("resposta_parte2", "3/4");
        values.put("resposta_correta", "8/4");
        db.insert("pergunta", null, values);

        dbHelper.getReadableDatabase();
        // Consultar todas as perguntas
        Cursor cursor = db.rawQuery("SELECT * FROM pergunta ORDER BY RANDOM() LIMIT 1", null);
        // Percorrer os resultados
        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex("perguntaPrincipal");
            int columnIndexParte1 = cursor.getColumnIndex("parte1");
            int columnIndexParte2 = cursor.getColumnIndex("parte2");
            if (columnIndex >= 0) {
                String perguntaP = cursor.getString(columnIndex);
                String p1 = cursor.getString(columnIndexParte1);
                String p2 = cursor.getString(columnIndexParte2);
                perguntaPrincipal.setText(perguntaP);
                parte1.setText(p1);
                parte2.setText(p2);
            }
        }


        cursor.close();
        db.close();

        //FIM BANCO DE DADOS


        // Inicialize suas variáveis PieChart
        graficoPizza1 = findViewById(R.id.grafico_pizza1);
        graficoPizza2 = findViewById(R.id.grafico_pizza2);

        // Configurar gráfico 1
        ArrayList<PieEntry> entradasGrafico1 = new ArrayList<>();
        entradasGrafico1.add(new PieEntry(1f, "1"));
        configurePieChart(graficoPizza1, entradasGrafico1);

        // Configurar gráfico 2
        ArrayList<PieEntry> entradasGrafico2 = new ArrayList<>();
        entradasGrafico2.add(new PieEntry(1f, "1"));
        configurePieChart(graficoPizza2, entradasGrafico2);



        //LISTENER DO PRIMEIRO GRAFICO ----------------------

        graficoPizza1.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                if (isGraph1Editable) {
                    showDivisionInputDialog(graficoPizza1);
                }else {
                    PieEntry selectedEntry = (PieEntry) e;
                    float selectedValue = selectedEntry.getY(); // Obtém o valor da fatia selecionada
                    String label = selectedEntry.getLabel(); // Obtém o rótulo da fatia (número/fracao)
                    Toast.makeText(getApplicationContext(), "Valor selecionado: " + selectedValue + ", Rótulo: " + label, Toast.LENGTH_SHORT).show();


                    // Atualize a soma e o TextView
                    sumOfSelectedSlices += selectedValue;
                    updateSumTextView(textViewSum1, sumOfSelectedSlices, selectedDenominator);



                }
            }

            @Override
            public void onNothingSelected() {
                // Ação a ser realizada quando nenhuma fatia está selecionada
            }
        });;

        //LISTENER DO SEGUNDO GRAFICO ----------------------

        graficoPizza2.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                if (isGraph2Editable) {
                    showDivisionInputDialog(graficoPizza2);
                }else {
                    PieEntry selectedEntry = (PieEntry) e;
                    float selectedValue2 = selectedEntry.getY(); // Obtém o valor da fatia selecionada
                    String label = selectedEntry.getLabel(); // Obtém o rótulo da fatia (número/fracao)
                    Toast.makeText(getApplicationContext(), "Valor selecionado: " + selectedValue2 + ", Rótulo: " + label, Toast.LENGTH_SHORT).show();

                    // Atualize a soma e o TextView
                    sumOfSelectedSlices2 += selectedValue2;
                    updateSumTextView(textViewSum2, sumOfSelectedSlices2, selectedDenominator);

                }
            }

            @Override
            public void onNothingSelected() {
                // Ação a ser realizada quando nenhuma fatia está selecionada
            }
        });


        //LISTENER DO SEGUNDO GRAFICO ----------------------



    }



    private void showDivisionInputDialog(PieChart pieChart) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Número de Fatias");
        builder.setMessage("Insira o valor do DENOMINADOR:");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int divisions = Integer.parseInt(input.getText().toString());
                selectedDenominator = divisions;
                selectedNumerator = 1; // Reseta o numerador para 1 ao mudar o denominador
                updateGraphEntries(pieChart, selectedNumerator, selectedDenominator);
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void updateGraphEntries(PieChart pieChart, int numerador, int denominador) {
        ArrayList<PieEntry> entries = new ArrayList<>();

        float valorExibicao = 1f / denominador; // Calcula o valor da fração para cada fatia

        for (int i = 1; i <= denominador; i++) {
            entries.add(new PieEntry(valorExibicao, "1/" + denominador)); // Use a fração fixa como rótulo

        }

        PieDataSet dataSet = new PieDataSet(entries, " ");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.invalidate();
        pieChart.setDescription(null);
        pieChart.setDrawEntryLabels(true);

        data.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                // Calcula o numerador com base no valor da fatia e do denominador
                int numerador = Math.round(value * denominador);
                return numerador + "/" + denominador;
            }
        });
        dataSet.setDrawValues(false); // Ative a exibição de valores nas fatias
        pieChart.setHoleRadius(0);
        pieChart.setTransparentCircleRadius(0);

        // Código para atualizar as entradas do gráfico com base no número de divisões

        if (pieChart == graficoPizza1) {
            isGraph1Editable = false;
        }
        if (pieChart == graficoPizza2) {
            isGraph2Editable = false;

        }
    }

    private void updateSumTextView(TextView textView, float sumOfSelectedSlices, int selectedDenominator) {
        int numerator = Math.round(sumOfSelectedSlices * selectedDenominator);
        String fractionText = "Soma das Fatias: " + numerator + "/" + selectedDenominator;
        textView.setText(fractionText);
    }

    private void setupSliceColors() {
        sliceColors.add(ColorTemplate.rgb("#FF5733")); // Cor padrão para fatias
        sliceColors.add(ColorTemplate.rgb("#66BB6A")); // Cor alterada para fatias selecionadas
        // Adicione mais cores conforme necessário
    }

    private void configurePieChart(PieChart pieChart, ArrayList<PieEntry> entries) {
        pieChart.setTouchEnabled(true);
        PieDataSet dataSet = new PieDataSet(entries, " ");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setDrawValues(false);
        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.invalidate();
        pieChart.setDescription(null);
        pieChart.setDrawEntryLabels(true);
        pieChart.setHoleRadius(0);
        pieChart.setTransparentCircleRadius(0);
        pieChart.getLegend().setEnabled(false);
    }

    private String getRespostaParte1FromDatabase() {
        String respostaParte1 = "";

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT resposta_parte1 FROM pergunta WHERE perguntaPrincipal = ?", new String[]{perguntaPrincipal.getText().toString()});

        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex("resposta_parte1");
            if (columnIndex >= 0) {
                respostaParte1 = cursor.getString(columnIndex);
            }
        }

        cursor.close();
        db.close();

        return respostaParte1;
    }

    //Função para verificar se a resposta está correta



}






