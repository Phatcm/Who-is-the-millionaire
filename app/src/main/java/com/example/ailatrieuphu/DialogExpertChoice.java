package com.example.ailatrieuphu;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.Random;

public class DialogExpertChoice extends Dialog {

    public DialogExpertChoice(@NonNull Context context, int rightAnsLoc) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_ask_the_expert);
        TextView txvAnswerA, txvAnswerB, txvAnswerC, txvAnswerD;
        txvAnswerA = findViewById(R.id.txvAnswerA);
        txvAnswerB = findViewById(R.id.txvAnswerB);
        txvAnswerC = findViewById(R.id.txvAnswerC);
        txvAnswerD = findViewById(R.id.txvAnswerD);

        Random r = new Random();
        int arrAnswer[] = new int[]{0,0,0,0};

        int Remain= 0;


        arrAnswer[rightAnsLoc] = r.nextInt(50 - 25) + 25;
        arrAnswer[rightAnsLoc] = arrAnswer[rightAnsLoc] + 25;
        Remain = 100 - arrAnswer[rightAnsLoc];

        for (int i = 0; i<arrAnswer.length; i++){
            if( i != rightAnsLoc) {
                arrAnswer[i] = r.nextInt(Remain - 0) +0;
                Remain = Remain - arrAnswer[i];
            }
        }

        int minLoc = 0;
        int min = 1000;
        for (int i = 0; i < arrAnswer.length; i++) {
            if (arrAnswer[i] < min) {
                min = arrAnswer[i];
                minLoc = i;
            }
        }

        if(Remain == 0){
            return;
        }else{
            arrAnswer[minLoc] = arrAnswer[minLoc] + Remain;
        }

        txvAnswerA.setText("A : "+(arrAnswer[0])+"%");
        txvAnswerB.setText("B : "+(arrAnswer[1])+"%");
        txvAnswerC.setText("C : "+(arrAnswer[2])+"%");
        txvAnswerD.setText("D : "+(arrAnswer[3])+"%");

    }
}
