package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistanceEqually() {
        Point p = new Point(4,2,6,8);
        Assert.assertEquals(p.distance(), 6.324555320336759);
    }

    @Test
    public void testDistanceRounding() {
        Point p = new Point(8.14,10.15,20.12,25.11);
        double scale = Math.pow(10, 2);
        Assert.assertEquals(Math.ceil(p.distance() * scale) / scale, 19.17);
    }

    @Test
    public void testDistancePositive() {
        Point p = new Point(2,6,4.8,8.6);
        Assert.assertEquals(p.distance() > 0, true);
    }

}
