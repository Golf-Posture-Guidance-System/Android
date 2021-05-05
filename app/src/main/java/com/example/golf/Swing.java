package com.example.golf;

public class Swing { //사용자의 스윙을 서버에서 저장해서 불러오는 걸로,,..
    //스윙 영상 하나 분석 결과를 저장하는 객체배열로 생성
    private int swing_num ;
    private int user_id;
    private String advice ;
    private String fname;

    public Swing(int swing_num ,int user_id,String fname) {
        this.user_id = user_id;
        this.swing_num = swing_num;
        this.fname = fname;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getFname() {
        return fname;
    }
}
