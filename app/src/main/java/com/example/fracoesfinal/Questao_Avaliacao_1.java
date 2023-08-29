package com.example.fracoesfinal;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
import java.util.HashSet;


public class Questao_Avaliacao_1 extends AppCompatActivity {

    // Declare suas variáveis PieChart
    private PieChart graficoPizza1;
    private PieChart graficoPizza2;
    private PieChart graficoPizza3;
    private int selectedDenominator = 1;
    private int selectedNumerator = 1;
    private boolean isGraph1Editable = true;
    private boolean isGraph2Editable = true;
    private boolean isGraph3Editable = true;

    private PieEntry lastSelectedSlice = null;

    private String respostaCorreta1 = "2/4"; // Substitua pelo valor correto que você está esperando
    private String respostaCorreta2 = "1/4"; // Substitua pelo valor correto que você está esperando

    private String respostaCorreta3 = "3/4";

    // Declare outras variáveis e views necessárias

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questao_avaliacao1);

        View primeiroEstagioLayout = findViewById(R.id.primeiro_estagio);
        View segundoEstagioLayout = findViewById(R.id.segundo_estagio);



        // Inicialize suas variáveis PieChart
        graficoPizza1 = findViewById(R.id.grafico_pizza1);
        graficoPizza2 = findViewById(R.id.grafico_pizza2);
        graficoPizza3 = findViewById(R.id.grafico_pizza3);

        //GRAFICO 1 ---------------------------------------------------------
        ArrayList<PieEntry> entradasGrafico1 = new ArrayList<>();
        graficoPizza1.setTouchEnabled(true);
        entradasGrafico1.add(new PieEntry(1f, "1"));
        PieDataSet dataSetGrafico1 = new PieDataSet(entradasGrafico1, " ");
        dataSetGrafico1.setColors(ColorTemplate.COLORFUL_COLORS); // Cores predefinidas
        dataSetGrafico1.setDrawValues(false);
        PieData dataGrafico1 = new PieData(dataSetGrafico1);
        graficoPizza1.setData(dataGrafico1);
        graficoPizza1.setDescription(null);
        graficoPizza1.invalidate(); // Atualiza o gráfico
        graficoPizza1.setDrawEntryLabels(true);
        graficoPizza1.setHoleRadius(0); // Define o valor do buraco como 0 para preencher toda a área central com as cores das fatias
        graficoPizza1.setTransparentCircleRadius(0); // Define o valor do círculo transparente como 0 para remover o círculo transparente no meio do gráfico
        graficoPizza1.getLegend().setEnabled(false); // Desativa a legenda do gráfico 1


        // GRAFICO 2 ----------------------------------------------------------

        // Configurar dados para o segundo gráfico de pizza
        graficoPizza2.setTouchEnabled(true);
        ArrayList<PieEntry> entradasGrafico2 = new ArrayList<>();
        entradasGrafico2.add(new PieEntry(1f, "1"));
        PieDataSet dataSetGrafico2 = new PieDataSet(entradasGrafico2, " ");
        dataSetGrafico2.setColors(ColorTemplate.COLORFUL_COLORS); // Cores predefinidas
        PieData dataGrafico2 = new PieData(dataSetGrafico2);
        graficoPizza2.setData(dataGrafico2);
        graficoPizza2.invalidate(); // Atualiza o gráfico
        graficoPizza2.setDescription(null);
        graficoPizza2.setDrawEntryLabels(true);
        dataSetGrafico2.setDrawValues(false);
        graficoPizza2.setHoleRadius(0); // Define o valor do buraco como 0 para preencher toda a área central com as cores das fatias
        graficoPizza2.setTransparentCircleRadius(0); // Define o valor do círculo transparente como 0 para remover o círculo transparente no meio do gráfico
        graficoPizza2.getLegend().setEnabled(false); // Desativa a legenda do gráfico 2


        //GRAFICO 3 ---------------------------------------------------------
        ArrayList<PieEntry> entradasGrafico3 = new ArrayList<>();
        graficoPizza3.setTouchEnabled(true);
        entradasGrafico3.add(new PieEntry(1f, "1"));
        PieDataSet dataSetGrafico3 = new PieDataSet(entradasGrafico1, " ");
        dataSetGrafico1.setColors(ColorTemplate.COLORFUL_COLORS); // Cores predefinidas
        dataSetGrafico1.setDrawValues(false);
        PieData dataGrafico3 = new PieData(dataSetGrafico1);
        graficoPizza3.setData(dataGrafico1);
        graficoPizza3.setDescription(null);
        graficoPizza3.invalidate(); // Atualiza o gráfico
        graficoPizza3.setDrawEntryLabels(true);
        graficoPizza3.setHoleRadius(0); // Define o valor do buraco como 0 para preencher toda a área central com as cores das fatias
        graficoPizza3.setTransparentCircleRadius(0); // Define o valor do círculo transparente como 0 para remover o círculo transparente no meio do gráfico
        graficoPizza3.getLegend().setEnabled(false); // Desativa a legenda do gráfico 1



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

                    if (label.equals(respostaCorreta1)) {
                        Toast.makeText(getApplicationContext(), "Resposta correta!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Resposta incorreta.", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onNothingSelected() {
                // Ação a ser realizada quando nenhuma fatia está selecionada
            }
        });

        //LISTENER DO SEGUNDO GRAFICO ----------------------

        graficoPizza2.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                if (isGraph2Editable) {
                    showDivisionInputDialog(graficoPizza2);
                }else {
                    PieEntry selectedEntry = (PieEntry) e;
                    float selectedValue = selectedEntry.getY(); // Obtém o valor da fatia selecionada
                    String label = selectedEntry.getLabel(); // Obtém o rótulo da fatia (número/fracao)
                    Toast.makeText(getApplicationContext(), "Valor selecionado: " + selectedValue + ", Rótulo: " + label, Toast.LENGTH_SHORT).show();

                    if (label.equals(respostaCorreta2)) {
                        Toast.makeText(getApplicationContext(), "Resposta correta!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Resposta incorreta.", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onNothingSelected() {
                // Ação a ser realizada quando nenhuma fatia está selecionada
            }
        });


        //LISTENER DO SEGUNDO GRAFICO ----------------------

        graficoPizza3.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                if (isGraph2Editable) {
                    showDivisionInputDialog(graficoPizza3);
                }else {
                    PieEntry selectedEntry = (PieEntry) e;
                    float selectedValue = selectedEntry.getY(); // Obtém o valor da fatia selecionada
                    String label = selectedEntry.getLabel(); // Obtém o rótulo da fatia (número/fracao)
                    Toast.makeText(getApplicationContext(), "Valor selecionado: " + selectedValue + ", Rótulo: " + label, Toast.LENGTH_SHORT).show();

                    if (label.equals(respostaCorreta3)) {
                        Toast.makeText(getApplicationContext(), "Resposta correta!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Resposta incorreta.", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onNothingSelected() {
                // Ação a ser realizada quando nenhuma fatia está selecionada
            }
        });


        //BOTAO DE IR PARA A SEGUNDA ETAPA DA QUESTAO
        Button btnProximaEtapa = findViewById(R.id.btnProxima_etapa);
        btnProximaEtapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    primeiroEstagioLayout.setVisibility(View.GONE); // Esconde o layout do primeiro estágio
                    segundoEstagioLayout.setVisibility(View.VISIBLE); // Exibe o layout do segundo estágio

            }
        });

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
        if (pieChart == graficoPizza3) {
            isGraph3Editable = false;
        }
    }




}