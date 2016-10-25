/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtual.camera.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Piotr Pyśk
 */
public abstract class Polygon {

    protected Color color;

    protected Polygon(Color color) {
        this.color = color;
    }

    public abstract List<Line> getLines();

    public Color getColor() {
        return color;
    }

    public void transform(Matrix transformation) {
        getLines().stream().forEach((line) -> {
            line.transform(transformation);
        });
    }

    public Point getCOGPoint() {
        List<Point> points = getPoints();
        double centerX, centerY, centerZ;
        centerX = centerY = centerZ = 0;
        for (Point point : points) {
            centerX += point.x;
            centerY += point.y;
            centerZ += point.z;
        }
        centerX /= points.size();
        centerY /= points.size();
        centerZ /= points.size();
        return new Point(centerX, centerY, centerZ);
    }

    public List<Point> getPoints() {
        Set<Point> points = new HashSet<>(getLines().size());
        getLines().stream().forEach((line) -> {
            points.add(line.getStart());
            points.add(line.getEnd());
        });
        return new ArrayList(points);
    }

}
