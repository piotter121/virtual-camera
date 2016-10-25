/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtual.camera.painters.comparators;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import virtual.camera.model.Point;
import virtual.camera.model.Polygon;
import virtual.camera.model.Surface;

/**
 *
 * @author Piotr Pyśk
 */
public class SurfaceComparator implements Comparator<Polygon> {

    enum Position {
        CONTAINS, OPOSITE_SIDES, SAME_SIDE, CROSSING;
    }

    @Override
    public int compare(Polygon p1, Polygon p2) {
        final Point point1 = p1.getLines().get(0).getStart();
        final Point point2 = p1.getLines().get(1).getStart();
        final Point point3 = p1.getLines().get(2).getStart();
        Surface p1Surface = new Surface(point1, point2, point3);
        Position polygonPosition = checkPolygonPosition(p1Surface, p2);
        switch (polygonPosition) {
            case CONTAINS:
                return 0;
            case OPOSITE_SIDES:
                return -1;
            case SAME_SIDE:
                return 1;
            default:
                Comparator<Polygon> com = new COGComparator();
                return com.compare(p1, p2);
        }
    }

    private Position checkPolygonPosition(Surface surface, Polygon polygon) {
        List<Point> points = polygon.getPoints();
        Point originPoint = new Point(0, 0, 0);
        List<Position> positions = new ArrayList<>(points.size());
        points.stream().forEach((point) -> {
            if (surface.areOnTheSameSide(originPoint, point)) {
                positions.add(Position.SAME_SIDE);
            } else if (surface.areOnTheOpositeSides(originPoint, point)) {
                positions.add(Position.OPOSITE_SIDES);
            } else if (surface.contains(point)) {
                positions.add(Position.CONTAINS);
            } else {
                throw new RuntimeException("Coś poszło nie tak!");
            }
        });
        Iterator<Position> it = positions.iterator();
        Position pos = it.next();
        while (it.hasNext()) {
            Position next = it.next();
            if (!pos.equals(next)) {
                return Position.CROSSING;
            }
        }
        return pos;
    }

}
