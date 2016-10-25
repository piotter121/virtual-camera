/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtual.camera.painters.comparators;

import java.util.Comparator;
import virtual.camera.model.Point;
import virtual.camera.model.Polygon;

/**
 *
 * @author piotr
 */
public class COGComparator implements Comparator<Polygon> {

    @Override
    public int compare(Polygon polygon1, Polygon polygon2) {
        Point center1 = polygon1.getCOGPoint();
        Point center2 = polygon2.getCOGPoint();
        double odl1 = Math.sqrt(Math.pow(center1.x, 2) + Math.pow(center1.y, 2) + Math.pow(center1.z, 2));
        double odl2 = Math.sqrt(Math.pow(center2.x, 2) + Math.pow(center2.y, 2) + Math.pow(center2.z, 2));
        return (int) (odl2 - odl1);
    }

}
