package com.example.fracoesfinal;


import android.widget.TextView;

import java.util.List;

public class QuestionsIntroducao {
    private TextView questionTextView;
    private int questionImageId;
    private List<Integer> optionImageIds;
    private int correctOptionIndex;

    public QuestionsIntroducao(int questionImageId, List<Integer> optionImageIds, int correctOptionIndex) {
        this.questionImageId = questionImageId;
        this.optionImageIds = optionImageIds;
        this.correctOptionIndex = correctOptionIndex;
        this.questionTextView = questionTextView;
    }

    public int getQuestionImageId() {
        return questionImageId;
    }

    public List<Integer> getOptionImageIds() {
        return optionImageIds;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }

    public TextView getQuestionTextView() {return questionTextView; }
}


