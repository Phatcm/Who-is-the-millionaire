package com.example.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


import com.example.ailatrieuphu.adapter.RewardAdapter;
import com.example.ailatrieuphu.object.FaceData;
import com.example.ailatrieuphu.object.Question;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity2 extends AppCompatActivity {
ListView lsvReward;
RewardAdapter rewardAdapter;
ArrayList<String> arrReward;
Question question;

int posQuest = 1;
View.OnClickListener listener;
TextView txvQuestion, txvAnswer1, txvAnswer2, txvAnswer3, txvAnswer4, txvLoser;
ArrayList<TextView> arrTxvAnswer;


    String answer;
    FaceData faceData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();
        mapping();
        setUp();
        setClick();
    }
    public void init(){
        arrReward = new ArrayList<>();

        arrReward.add("1000000");
        arrReward.add("500000");
        arrReward.add("250000");
        arrReward.add("125000");
        arrReward.add("64000");
        arrReward.add("32000");
        arrReward.add("16000");
        arrReward.add("8000");
        arrReward.add("4000");
        arrReward.add("2000");
        arrReward.add("1000");
        arrReward.add("500");
        arrReward.add("300");
        arrReward.add("200");
        arrReward.add("100");

        rewardAdapter = new RewardAdapter(this,0,arrReward);

        question = new Question();

        arrTxvAnswer = new ArrayList<>();

        faceData = new FaceData(this);

    }
    public void mapping(){
        lsvReward = findViewById(R.id.lsvReward);
        txvQuestion = findViewById(R.id.txvQuestion);
        txvAnswer1 = findViewById(R.id.txvAnswer1);
        txvAnswer2 = findViewById(R.id.txvAnswer2);
        txvAnswer3 = findViewById(R.id.txvAnswer3);
        txvAnswer4 = findViewById(R.id.txvAnswer4);
        txvLoser = findViewById(R.id.txvLoser);

        arrTxvAnswer.add(txvAnswer1);
        arrTxvAnswer.add(txvAnswer2);
        arrTxvAnswer.add(txvAnswer3);
        arrTxvAnswer.add(txvAnswer4);

    }
    public void setUp(){
        txvLoser.setVisibility(View.GONE);
        lsvReward.setAdapter(rewardAdapter);
        showQuestion();
    }
    public void setClick(){
        listener = view -> checkAnswer(((TextView)view));
        for(TextView t: arrTxvAnswer){
            t.setOnClickListener(listener);
        }
    }
    public void checkAnswer(final TextView txv) {
        answer = txv.getText().toString();
        txv.setBackgroundResource(R.drawable.bg_chosen);

        new CountDownTimer(2000, 100){

            @Override
            public void onTick(long l) {
            }
            @Override
            public void onFinish() {
                for(TextView t: arrTxvAnswer){
                    String s = t.getText().toString();
                    if(s.equals(question.getRightAnswer())){
                        t.setBackgroundResource(R.drawable.bg_right_answer);
                        break;
                    }
                }
                new CountDownTimer(2000, 100){
                    @Override
                    public void onTick(long l) {
                    }
                    @Override
                    public void onFinish() {
                        if (answer.equals(question.getRightAnswer())) {
                            posQuest++;
                            if (posQuest > 15) {
                                posQuest = 15;
                                //int posReward = (posQuest/5)*5;
                                txvLoser.setVisibility(View.VISIBLE);
                                txvLoser.setText("Congratulations you received reward is \n" + arrReward.get(0) + "$");
                                return;
                            }
                            showQuestion();
                        } else {
                            txvLoser.setVisibility(View.VISIBLE);
                            int posReward = (posQuest/5)*5;
                            if(posReward != 0){
                                posReward = posReward - 1;
                            }
                            txvLoser.setText("You will leave with your reward is \n" + arrReward.get(14-posReward) + "$");
                        }
                    }
                }.start();
            }
        }.start();

    }

    public void setQuestion(){
        question = faceData.createQuest(posQuest);
    }
    public void showQuestion(){
        setQuestion();
        txvQuestion.setText(question.getContent());
        ArrayList<String> arrAnswer = new ArrayList<>(question.getArrWrongAnswer());
        arrAnswer.add(question.getRightAnswer());

        Random r = new Random();
        for(int i=0; i<10; i++){
            int position1 = r.nextInt(arrAnswer.size());
            int position2 = r.nextInt(arrAnswer.size());
            String a = arrAnswer.get(position1);
            arrAnswer.set(position1, arrAnswer.get(position2));
            arrAnswer.set(position2,a);
        }
        for(int i=0; i<arrTxvAnswer.size();i++){
            arrTxvAnswer.get(i).setOnClickListener(listener);
            arrTxvAnswer.get(i).setVisibility(View.VISIBLE);
            arrTxvAnswer.get(i).setBackgroundResource(R.drawable.bg_btn);
            arrTxvAnswer.get(i).setText(arrAnswer.get(i));
        }
        rewardAdapter.setPosQuest(posQuest);

    }

    boolean support5050 = true;
    public void support5050(View view) {
        if(support5050 == false){
            return;
        }
        Random r = new Random();
        int numVisibleAnswer = 2;
        do{
            int posVisibleAnswer = r.nextInt(4);
            TextView t = arrTxvAnswer.get(posVisibleAnswer);
            if(t.getVisibility() == View.VISIBLE && t.getText().toString().equals(question.getRightAnswer())==false){
                t.setVisibility(View.INVISIBLE);
                t.setOnClickListener(null);
                numVisibleAnswer --;
            }
        }while ( numVisibleAnswer > 0);
        support5050 = false;
    }
    boolean ask_the_expert = true;
    public void ask_the_expert(View view) {
        if (ask_the_expert == false){
            return;
        }
        for(int i= 0; i<arrTxvAnswer.size();i++){
            TextView t = arrTxvAnswer.get(i);
            if(t.getText().toString().equals(question.getRightAnswer())){
                new DialogExpertChoice(this, i).show();
                break;
            }
        }
        ask_the_expert = false;
    }

    boolean changeQuestion = true;
    public void changeQuestion(View view) {
        if(changeQuestion == false){
            return;
        }
        showQuestion();
        changeQuestion = false;
    }
    boolean endBtn = true;
    public void endBtn(View view){
        if (endBtn == false){
            return;
        }else{
            txvLoser.setVisibility(View.VISIBLE);
            if(posQuest <= 15 && posQuest >1){
                posQuest = posQuest - 1;
            }
            txvLoser.setText("You will leave with your reward is \n" + arrReward.get(15-posQuest) + "$");
        }
        endBtn = false;
    }
}