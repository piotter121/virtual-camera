/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtual.camera.painters;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import virtual.camera.model.Line;
import virtual.camera.model.Point;
import virtual.camera.model.Polygon;
import virtual.camera.model.Scene;

/**
 *
 * @author Piotr Pyśk
 */
public class SimpleProjection extends Painter {

    private Graphics2D graphics;
    private Rectangle viewport;

    public SimpleProjection(Scene scene) {
        super(scene);
    }

    @Override
    public void paintScene(Graphics2D graphics) {
        this.graphics = graphics;
        cutToViewport();
        Collection<Polygon> polygons = scene.getPolygons();
        List<Line> linesToDraw = new ArrayList<>(polygons.size() * 4);
        polygons.stream().forEach((polygon) -> {
            linesToDraw.addAll(polygon.getLines());
        });
        linesToDraw.stream().forEach((line) -> {
            drawLine(line);
        });
    }

    private void cutToViewport() {
        viewport = graphics.getClipBounds();
    }

    private void drawLine(Line line) {
        graphics.setColor(line.getColor());
        Point start = line.getStart();
        Point end = line.getEnd();
        if (isBehindVieport(start) && isBehindVieport(end)) {
            return;
        }
        double x1, y1, x2, y2;

        if (!isVisible(start)) {
            start = clipToVisible(end, start);
        }

        if (!isVisible(end)) {
            end = clipToVisible(start, end);
        }

        x1 = create2DxCoordinate(start);
        y1 = create2DyCoordinate(start);
        x2 = create2DxCoordinate(end);
        y2 = create2DyCoordinate(end);

        if (isVisible(start) && isVisible(end)) {
            Point2D point1 = convertToPoint2D(x1, y1);
            Point2D point2 = convertToPoint2D(x2, y2);
            Line2D line2d = new Line2D.Double(point1, point2);
            graphics.draw(line2d);
        }
    }

    private static boolean isVisible(Point point) {
        return point.z >= 1;
    }

    private Point2D convertToPoint2D(double x, double y) {
        return new Point2D.Double(viewport.getWidth() / 2 + x, viewport.getHeight() / 2 - y);
    }
}
