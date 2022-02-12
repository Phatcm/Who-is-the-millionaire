package com.example.ailatrieuphu.object;

import java.util.ArrayList;

public class Question {
    private String content, rightAnswer;
    private ArrayList<String> arrWrongAnswer;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public ArrayList<String> getArrWrongAnswer() {
        return arrWrongAnswer;
    }

    public void setArrWrongAnswer(ArrayList<String> arrWrongAnswer) {
        this.arrWrongAnswer = arrWrongAnswer;
    }

    public void setArrWrongAnswer(String wrongAnswer) {//dapan1&dapan2&dapan3
        String arrW[] = wrongAnswer.split("&");
        this.arrWrongAnswer = new ArrayList<>();
        for (String w : arrW){
            arrWrongAnswer.add(w);
        }
    }
}