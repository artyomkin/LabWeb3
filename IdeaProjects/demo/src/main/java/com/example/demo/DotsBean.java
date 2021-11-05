package com.example.demo;


import lombok.Getter;
import lombok.Setter;

import javax.faces.application.FacesMessage;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class DotsBean implements Serializable {

    @Setter
    @Getter
    private List<Dot> dots;
    private int len;

    public void create(Dot dot){
        if (this.dots == null){
            this.dots = new ArrayList<>();
        }
        this.dots.add(dot);
        this.len = dots.size();
    }
    public List<Dot> readAll(){
        return this.dots;
    }
    public String foo(){
        return "Hello world";
    }
    public void deleteAll(){
        this.dots.clear();
        this.len = dots.size();
    }
}
