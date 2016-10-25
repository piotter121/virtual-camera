/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtual.camera.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author piotr
 */
public class PointTest {

    @Test
    public void testVectorProduct() {
        Point a = new Point(1, 2, 3);
        Point b = new Point(4, 5, 6);
        Point c = new Point(-3, 6, -3);
        assertEquals(c, a.vectorProduct(b));
    }

}
