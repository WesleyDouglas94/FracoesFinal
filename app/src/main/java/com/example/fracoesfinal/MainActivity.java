package com.example.fracoesfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<QuestionsIntroducao> questions;
    private int currentQuestionIndex;

    private String nome;
    private String sobrenome;

    private List<String> userAnswers; // Lista para armazenar as respostas dadas pelo usuário
    private int correctAnswersCount; // Contador para rastrear o número de respostas corretas

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nome = getIntent().getStringExtra("nomeUsuario");
        sobrenome = getIntent().getStringExtra("sobrenomeUsuario");

        initializeQuestions();

        userAnswers = new ArrayList<>();
        correctAnswersCount = 0;

        currentQuestionIndex = 0;
        displayQuestion(currentQuestionIndex);


    }

    private void initializeQuestions() {
        questions = new ArrayList<>();

        questions.add(new QuestionsIntroducao(R.drawable.pizza_7_8, Arrays.asList(
                R.drawable.questao1_a,
                R.drawable.questao1_b,
                R.drawable.questao1_c,
                R.drawable.questao1_d
        ), 0));

        // Adicione mais perguntas aqui

        questions.add(new QuestionsIntroducao(R.drawable.pizza_6_8, Arrays.asList(
                R.drawable.questao2_a,
                R.drawable.questao2_b,
                R.drawable.questao2_c,
                R.drawable.questao2_d
        ), 1));
    }

    private void displayQuestion(int questionIndex) {
        QuestionsIntroducao currentQuestion = questions.get(questionIndex);

        ImageView imageViewQuestion = findViewById(R.id.imageViewQuestion);
        imageViewQuestion.setImageResource(currentQuestion.getQuestionImageId());



        List<Integer> optionImageIds = currentQuestion.getOptionImageIds();
        ImageView alternative1ImageView = findViewById(R.id.alternative1ImageView);
        ImageView alternative2ImageView = findViewById(R.id.alternative2ImageView);
        ImageView alternative3ImageView = findViewById(R.id.alternative3ImageView);
        ImageView alternative4ImageView = findViewById(R.id.alternative4ImageView);

        alternative1ImageView.setImageResource(optionImageIds.get(0));
        alternative2ImageView.setImageResource(optionImageIds.get(1));
        alternative3ImageView.setImageResource(optionImageIds.get(2));
        alternative4ImageView.setImageResource(optionImageIds.get(3));

        TextView questionTextView = findViewById(R.id.questionTextView);
        questionTextView.setText("Qual a fração correspondente a imagem exibida? "); // Supondo que você tenha um método getQuestionText() na classe Questions


        setDragListeners();
    }

    private void setDragListeners() {
        ImageView imageViewQuestion = findViewById(R.id.imageViewQuestion);
        imageViewQuestion.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                    v.startDragAndDrop(data, shadowBuilder, v, 0);
                    return true;
                }
                return false;
            }
        });
        // Implemente os OnDragListeners para as opções de resposta aqui, similar ao exemplo anterior

        ImageView alternative1ImageView = findViewById(R.id.alternative1ImageView);
        ImageView alternative2ImageView = findViewById(R.id.alternative2ImageView);
        ImageView alternative3ImageView = findViewById(R.id.alternative3ImageView);
        ImageView alternative4ImageView = findViewById(R.id.alternative4ImageView);

        alternative1ImageView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                int dragAction = event.getAction();
                switch (dragAction) {
                    case DragEvent.ACTION_DROP:
                        // Lógica para verificar se a resposta está correta
                        showFeedback(currentQuestionIndex == 0 && isCorrectAnswer("questao1_a"));
                        break;
                }
                return true;
            }
        });
        // Implementar os outros OnDragListeners para as opções restantes de resposta
        // Você deve configurar os listeners de acordo com a lógica da sua aplicação e as respostas corretas das perguntas.
        alternative2ImageView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                int dragAction = event.getAction();
                switch (dragAction) {
                    case DragEvent.ACTION_DROP:
                        // Lógica para verificar se a resposta está correta
                        showFeedback(currentQuestionIndex == 0 && isCorrectAnswer("questao1_b"));
                        break;
                }
                return true;
            }
        });

        // Implementar os outros OnDragListeners para as opções restantes de resposta

        alternative1ImageView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                int dragAction = event.getAction();
                switch (dragAction) {
                    case DragEvent.ACTION_DROP:
                        // Lógica para verificar se a resposta está correta
                        showFeedback(currentQuestionIndex == 0 && isCorrectAnswer("questao1_a"));
                        break;
                }
                return true;
            }
        });

        alternative2ImageView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                int dragAction = event.getAction();
                switch (dragAction) {
                    case DragEvent.ACTION_DROP:
                        // Lógica para verificar se a resposta está correta
                        showFeedback(currentQuestionIndex == 0 && isCorrectAnswer("questao1_b"));
                        break;
                }
                return true;
            }
        });


        alternative3ImageView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                int dragAction = event.getAction();
                switch (dragAction) {
                    case DragEvent.ACTION_DROP:
                        // Lógica para verificar se a resposta está correta
                        showFeedback(currentQuestionIndex == 0 && isCorrectAnswer("questao1_c"));
                        break;
                }
                return true;
            }
        });
        alternative4ImageView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                int dragAction = event.getAction();
                switch (dragAction) {
                    case DragEvent.ACTION_DROP:
                        // Lógica para verificar se a resposta está correta
                        showFeedback(currentQuestionIndex == 1 && isCorrectAnswer("questao2_d"));
                        break;
                }
                return true;
            }
        });


    }

    private boolean isCorrectAnswer(String selectedAnswer) {
        if (currentQuestionIndex == 0) {
            return selectedAnswer.equals("questao1_a");
        } else if (currentQuestionIndex == 1) {
            return selectedAnswer.equals("questao2_d");
        }
        return false;
    }



    private void showFeedback(boolean isCorrect) {
        if (isCorrect) {
            Toast.makeText(this, "Resposta correta!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Resposta incorreta.", Toast.LENGTH_SHORT).show();
        }

        userAnswers.add(isCorrect ? "Correta" : "Incorreta"); // Adiciona a resposta à lista

        if (isCorrect) {
            correctAnswersCount++; // Incrementa o contador de respostas corretas
        }

        currentQuestionIndex++;
        if (currentQuestionIndex < questions.size()) {
            displayQuestion(currentQuestionIndex);
        } else {
            showQuizResult();
            // Final do quiz
        }


    }

    private void sendEmailWithResult() {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"wesleydouglas94@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Resultado do Quiz do Aluno: " + nome + " " + sobrenome );

        int totalQuestions = questions.size();
        int incorrectAnswersCount = totalQuestions - correctAnswersCount;
        String resultMessage = "Quiz concluído! " +
                "Respostas corretas: " + correctAnswersCount + "\n" +
                "Respostas incorretas: " + incorrectAnswersCount;

        emailIntent.putExtra(Intent.EXTRA_TEXT, resultMessage);

        try {
            startActivity(Intent.createChooser(emailIntent, "Enviar email..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "Nenhum cliente de email instalado.", Toast.LENGTH_SHORT).show();
        }
    }

    private void showQuizResult() {
        int totalQuestions = questions.size();
        int incorrectAnswersCount = totalQuestions - correctAnswersCount;

        String resultMessage = "Quiz concluído!\n" +
                "Respostas corretas: " + correctAnswersCount + "\n" +
                "Respostas incorretas: " + incorrectAnswersCount;

        Toast.makeText(this, resultMessage, Toast.LENGTH_LONG).show();
    }


}
