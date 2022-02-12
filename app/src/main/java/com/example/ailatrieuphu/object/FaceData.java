package com.example.ailatrieuphu.object;

import android.content.Context;

import com.example.ailatrieuphu.sql.DBManager;

import java.util.ArrayList;
import java.util.Random;

public class FaceData {
    public FaceData(Context ct){
        takeQuesFromSqlite(ct);
    }

    public void takeQuesFromSqlite(Context ct){
        DBManager d = new DBManager(ct);
        d.open();
        arrQuestion = new ArrayList<>(d.getQuestion());
        d.close();
    }

    public Question createQuest(int level){
        Random r = new Random();
        ArrayList<Question> arr = arrQuestion.get(level-1);
        return arr.get(r.nextInt(arr.size()));
    }
    ArrayList<ArrayList<Question>> arrQuestion = new ArrayList<>();
/*
    public void createQuestion1(){
        ArrayList<Question> arrQuestion1 = new ArrayList<>();
        arrQuestion1.add(createQuest("Điền từ còn thiếu vào câu ca dao: \"Gần mực thì đen gần đèn thì ...\" gì?","Sáng","Chói&Lóa&Tối"));
        arrQuestion1.add(createQuest("Trên mặt thoáng chất lỏng có hai quá trình ngược nhau, đó là sự bay hơi và gì?","Sự ngưng tụ","Sự thăng hoa&Sự đông đặc&Sự kết tủa"));
        arrQuestion.add(arrQuestion1);

        //Question2
        ArrayList<Question> arrQuestion2 = new ArrayList<>();
        arrQuestion2.add(createQuest("Hệ thống đô thị ở Việt Nam được phân thành mấy loại?","6","3&4&5&6"));
        arrQuestion2.add(createQuest("Cầu thủ nào đạt danh hiệu Quả bóng vàng Việt Nam năm 2010?","Minh Phương","Vũ Phong&Vũ Phong&Trọng Hoàng"));
        arrQuestion.add(arrQuestion2);

    }


 */
    private Question createQuest(String s1, String s2, String s3){
        Question q1 = new Question();
        q1.setContent(s1);
        q1.setRightAnswer(s2);
        q1.setArrWrongAnswer(s3);
        return q1;
    }

}
