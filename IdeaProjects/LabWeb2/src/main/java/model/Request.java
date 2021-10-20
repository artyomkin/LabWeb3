package model;

public class Request {

    private Double[] x;
    private Double[] y;
    private Double[] R;

    public Request(Double[] x, Double[] y, Double[] r){
        this.x = x;
        this.y = y;
        this.R = r;
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

    @Override
    public String toString(){
        String res = "";
        for(double xval : x){
            res += xval + "\n";
        }
        for(double yval : y){
            res += yval + "\n";
        }
        for(double rval : R){
            res += rval + "\n";
        }
        return res;
    }
}
