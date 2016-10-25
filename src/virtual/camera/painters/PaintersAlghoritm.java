/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtual.camera.painters;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import virtual.camera.model.Line;
import virtual.camera.model.Point;
import virtual.camera.model.Polygon;
import virtual.camera.model.Scene;

/**
 *
 * @author piotr
 */
public class PaintersAlghoritm extends Painter {

    private Graphics2D graphics;
    private Rectangle viewport;

    public PaintersAlghoritm(Scene scene) {
        super(scene);
    }

    @Override
    public void paintScene(Graphics2D graphics) {
        this.graphics = graphics;
        this.viewport = this.graphics.getClipBounds();
        List<Polygon> polygons = this.scene.getPolygons();
        Collections.sort(polygons, new DepthComparator());
        polygons.stream().forEach((Polygon polygon) -> {
            if (isVisible(polygon)) {
                drawPolygon(polygon);
            }
        });
    }

    private void drawPolygon(Polygon polygon) {
        graphics.setColor(polygon.getColor());
        java.awt.Polygon polygon2D = new java.awt.Polygon();
        polygon.getLines().stream().forEach((line) -> {
            addLineToPolygon2D(line, polygon2D);
        });
        graphics.fill(polygon2D);
        graphics.setColor(Color.BLACK);
        graphics.draw(polygon2D);
    }

    private void addLineToPolygon2D(Line line, java.awt.Polygon polygon2D) {
        addPointToPolygon2D(line.getStart(), polygon2D);
        addPointToPolygon2D(line.getEnd(), polygon2D);
    }

    private void addPointToPolygon2D(Point point, java.awt.Polygon polygon2D) {
        double xCoordinate = create2DxCoordinate(point);
        double yCoordinate = create2DyCoordinate(point);
        polygon2D.addPoint(convertTo2Dx(xCoordinate), convertTo2Dy(yCoordinate));
    }

    private int convertTo2Dx(double x) {
        return (int) (viewport.getWidth() / 2 + x);
    }

    private int convertTo2Dy(double y) {
        return (int) (viewport.getHeight() / 2 - y);
    }

    private static boolean isVisible(Polygon polygon) {
        return polygon.getLines().stream().allMatch((line) -> (isVisible(line)));
    }

    private static boolean isVisible(Line line) {
        return isVisible(line.getStart()) && isVisible(line.getEnd());
    }

    private static boolean isVisible(Point point) {
        return point.z > 0;
    }

    private static class DepthComparator implements Comparator<Polygon> {

        @Override
        public int compare(Polygon polygon1, Polygon polygon2) {
            Point center1 = polygon1.getCenterPoint();
            Point center2 = polygon2.getCenterPoint();
            double odl1 = Math.sqrt(Math.pow(center1.x, 2) + Math.pow(center1.y, 2) + Math.pow(center1.z, 2));
            double odl2 = Math.sqrt(Math.pow(center2.x, 2) + Math.pow(center2.y, 2) + Math.pow(center2.z, 2));
            return (int) (odl2 - odl1);
        }

    }

}
