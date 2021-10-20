package model;

import java.util.ArrayList;

public class Response {
    private ArrayList<Dot> dots;
    private long workingTime;
    private String currentTime;

    public Response(){
        this.dots = new ArrayList<>();
        this.workingTime = 0L;
        this.currentTime = "";
    }

    public Response(ArrayList<Dot> dots, long workingTime, String currentTime){
        this.dots = dots;
        this.workingTime = workingTime;
        this.currentTime = currentTime;
    }
}
