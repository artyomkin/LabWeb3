package com.example.demo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class Dot implements Serializable {

    @Getter
    @Setter
    private Double x;
    @Getter
    @Setter
    private Double y;
    @Getter
    @Setter
    private Double r;
    @Getter
    @Setter
    private boolean hit;

    public Dot() {
        this.x = 0D;
        this.y = 0D;
        this.r = 0D;
        this.hit = false;
    }

    public Dot(Double x, Double y, Double r, boolean hit) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.hit = hit;
    }


}
