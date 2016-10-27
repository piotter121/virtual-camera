/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtual.camera.painters.comparators;

import java.awt.Color;
import org.junit.*;
import static org.junit.Assert.*;
import virtual.camera.model.*;

/**
 *
 * @author piotr
 */
public class SurfaceComparatorTest {

    private SurfaceComparator comparator;

    @Before
    public void setUp() {
        comparator = new SurfaceComparator();
    }

    @Test
    public void testIfInFrontCointains() {
        Point start = new Point(20, -10, 100);
        Point a = start.clone();
        Point b = new Point(start.x + 40, start.y, start.z);
        Point c = new Point(start.x + 40, start.y + 50, start.z);
        Surface surface = new Surface(a, b, c);
        Quadrangle q = new Quadrangle(a, b, c, c, Color.yellow);
        assertFalse(SurfaceComparator.testIfInFront(surface, q));
    }

    @Test
    public void testReturnEQ() {
        Polygon p1 = new Quadrangle(
                new Point(30, 40, 50),
                new Point(20, 34, 50),
                new Point(50, 20, 50),
                new Point(33, 23, 50),
                Color.BLACK
        );
        Polygon p2 = new Quadrangle(
                new Point(30, 40, 50),
                new Point(20, 34, 50),
                new Point(50, 20, 50),
                new Point(33, 23, 50),
                Color.BLACK
        );
        assertEquals(0, this.comparator.compare(p1, p2));
    }

    @Test
    public void testReturnGT() {
        Polygon p1 = new Quadrangle(
                new Point(30, 40, 49),
                new Point(20, 34, 49),
                new Point(50, 20, 49),
                new Point(33, 23, 49),
                Color.BLACK
        );
        Polygon p2 = new Quadrangle(
                new Point(30, 40, 50),
                new Point(20, 34, 50),
                new Point(50, 20, 50),
                new Point(33, 23, 50),
                Color.BLACK
        );
        assertEquals(1, this.comparator.compare(p1, p2));
    }

    @Test
    public void testReturnLT() {
        Polygon p1 = new Quadrangle(
                new Point(30, 40, 49),
                new Point(20, 34, 49),
                new Point(50, 20, 49),
                new Point(33, 23, 49),
                Color.BLACK
        );
        Polygon p2 = new Quadrangle(
                new Point(30, 40, 50),
                new Point(20, 34, 50),
                new Point(50, 20, 50),
                new Point(33, 23, 50),
                Color.BLACK
        );
        assertEquals(-1, this.comparator.compare(p2, p1));
    }

}
