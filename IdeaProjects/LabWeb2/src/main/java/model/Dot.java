package model;

public class Dot {

    private double x;
    private double y;
    private double R;
    private boolean hit;

    public Dot(double x, double y, double R, boolean hit){
        this.hit = hit;
        this.x = x;
        this.y = y;
        this.R = R;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return R;
    }

    public void setR(int r) {
        R = r;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }
}
