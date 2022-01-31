package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistanceEqually() {
        Point p1 = new Point(4,2);
        Point p2 = new Point(6,8);
        Assert.assertEquals(p1.distance(p2), 6.324555320336759);
    }

    @Test
    public void testDistanceModule() {
        Point p1 = new Point(-4,-2);
        Point p2 = new Point(-6,-8);
        Assert.assertEquals(p1.distance(p2), 6.324555320336759);
    }

    @Test
    public void testDistancePositive() {
        Point p1 = new Point(4,2);
        Point p2 = new Point(6,8);
        Assert.assertEquals(p1.distance(p2) > 0, true);
    }

    @Test
    public void testDistanceRounding() {
        Point p1 = new Point(8.14,10.15);
        Point p2 = new Point(20.12,25.11);
        double scale = Math.pow(10, 2);
        Assert.assertEquals(Math.ceil(p1.distance(p2) * scale) / scale, 19.17);
    }

}
