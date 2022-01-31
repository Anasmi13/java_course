package ru.stqa.pft.sandbox;

public class MySecondProgram {

    public static void main(String[] args) {

        Point p1 = new Point(4,2);
        Point p2 = new Point(6,8);

        System.out.println("Расстояние между двумя точками A(" + p1.x + "," + p1.y + ") и В(" + p2.x + "," + p2.y + ") = " + p1.distance(p2));

    }

}
