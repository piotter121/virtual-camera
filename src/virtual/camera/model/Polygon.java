/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtual.camera.model;

import java.awt.Color;
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

    public double getMinZCoordinate() {
        List<Line> lines = getLines();
        double minZ = Double.MAX_VALUE;
        for (Line line : lines) {
            double lineMinZ = line.getMinZCoordinate();
            if (lineMinZ < minZ) {
                minZ = lineMinZ;
            }
        }
        return minZ;
    }

    public Point getCenterPoint() {
        Set<Point> points = new HashSet<>(getLines().size());
        getLines().stream().forEach((line) -> {
            points.add(line.getStart());
            points.add(line.getEnd());
        });
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

}
