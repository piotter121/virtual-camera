/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtual.camera.painters.comparators;

import virtual.camera.model.Point;
import virtual.camera.model.Polygon;
import virtual.camera.model.Surface;

import java.util.Comparator;

/**
 *
 * @author Piotr Pyśk
 */
public class SurfaceComparator implements Comparator<Polygon> {

    @Override
    public int compare(final Polygon p1, final Polygon p2) {
        Point point1 = p1.getLines().get(0).getStart();
        Point point2 = p1.getLines().get(1).getStart();
        Point point3 = p1.getLines().get(2).getStart();
        Surface p1Surface = new Surface(point1, point2, point3);
        if (testIfInFront(p1Surface, p2)) {
            return -1;
        }
        point1 = p2.getLines().get(0).getStart();
        point2 = p2.getLines().get(1).getStart();
        point3 = p2.getLines().get(2).getStart();
        Surface p2Surface = new Surface(point1, point2, point3);
        if (testIfInFront(p2Surface, p1)) {
            return 1;
        }
        return 0;
    }

    public static boolean testIfInFront(Surface surface, Polygon polygon) {
        return polygon.getPoints().stream()
                .allMatch(point -> surface.areOnTheSameSide(point, new Point(0, 0, 0)));
    }

}
