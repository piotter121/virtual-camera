/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtual.camera.painters.comparators;

import java.awt.Color;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import virtual.camera.model.Line;
import virtual.camera.model.Point;
import virtual.camera.model.Polygon;
import virtual.camera.model.Quadrangle;

/**
 *
 * @author piotr
 */
public class DepthComparatorTest {

    private DepthComparator comparator;

    @Before
    public void setUp() {
        comparator = new DepthComparator();
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
