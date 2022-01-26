package ru.stqa.pft.sandbox;

public class Point {

    public double Xa;
    public double Ya;
    public double Xb;
    public double Yb;

    public Point(double Xa, double Ya, double Xb, double Yb) {
        this.Xa = Xa;
        this.Ya = Ya;
        this.Xb = Xb;
        this.Yb = Yb;
    }

    public double distance() {
        return Math.sqrt(Math.pow((this.Xa-this.Xb), 2) + Math.pow((this.Ya-this.Yb), 2));
    }

}
