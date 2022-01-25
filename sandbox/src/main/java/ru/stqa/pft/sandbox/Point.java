package ru.stqa.pft.sandbox;

public class Point {

    public double Xa;
    public double Ya;
    public double Yx;
    public double Yb;

    public Point(double Xa, double Ya, double Yx, double Yb) {
        this.Xa = Xa;
        this.Ya = Ya;
        this.Yx = Yx;
        this.Yb = Yb;
    }

    public double distance() {
        return Math.sqrt(Math.pow((this.Xa-this.Yx), 2) + Math.pow((this.Ya-this.Yb), 2));
    }

}
