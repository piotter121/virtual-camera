/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtual.camera.painters.comparators;

import virtual.camera.model.Point;
import virtual.camera.model.Polygon;

/**
 *
 * @author piotr
 */
public class DepthComparator extends COGComparator {

    @Override
    public int compare(Polygon p1, Polygon p2) {
        if (findMaxZ(p1) < findMinZ(p2)) {
            return 1;
        } else {
            return super.compare(p1, p2);
        }
    }
    
    private static double findMinZ(Polygon p) {
        double minZ = Double.MAX_VALUE;
        for (Point point : p.getPoints()) {
            if (point.z < minZ) {
                minZ = point.z;
            }
        }
        return minZ;
    }
    
    private static double findMaxZ(Polygon p) {
        double maxZ = Double.MIN_VALUE;
        for (Point point : p.getPoints()) {
            if (point.z > maxZ) {
                maxZ = point.z;
            }
        }
        return maxZ;
    }

}
