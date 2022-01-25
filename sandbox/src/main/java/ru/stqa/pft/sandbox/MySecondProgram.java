package ru.stqa.pft.sandbox;

public class MySecondProgram {

    public static void main(String[] args) {

        Point p = new Point(4,2,6,8);
        System.out.println("Расстояние между двумя точками A(" + p.Xa + "," + p.Ya + ") и В(" + p.Yx + "," + p.Yb + ") = " + p.distance());

    }

}
