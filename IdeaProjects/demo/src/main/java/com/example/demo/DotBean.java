package com.example.demo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.faces.application.FacesMessage;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class DotBean implements Serializable {

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
    private boolean isHit;
    @Inject
    private DotsBean dotsBean;

    public void hitDot(){
        this.isHit = HitChecker.checkHit(this);
        dotsBean.create(new Dot(x,y,r,isHit));
    }
}
