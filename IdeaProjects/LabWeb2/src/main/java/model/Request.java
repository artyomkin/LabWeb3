package model;

public class Request {

    private Double[] x;
    private Double[] y;
    private Double[] R;
    private boolean async;

    public Request(Double[] x, Double[] y, Double[] r, boolean async){
        this.x = x;
        this.y = y;
        this.R = r;
        this.async = async;
    }

    public Double[] getX() {
        return x;
    }

    public Double[] getY() {
        return y;
    }

    public Double[] getR(){
        return R;
    }

    public boolean isAsync() {return async;}

}
