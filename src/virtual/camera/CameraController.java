/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtual.camera;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Collection;
import virtual.camera.model.Line;
import virtual.camera.model.Point;
import virtual.camera.model.Scene;

/**
 *
 * @author piotr
 */
public class CameraController {

    private static final int ZOOM_STEP = 20;

    private Scene scene;
    private double zoom = 200;
    private Graphics2D graphics;
    private Rectangle viewport;

    CameraController(Scene scene) {
        this.scene = scene;
    }

    public void paintScene(Graphics2D graphics) {
        this.graphics = graphics;
        cutToViewport();
        Collection<Line> linesToDraw = scene.getLines();
        linesToDraw.stream().forEach((line) -> {
            drawLine(line);
        });
    }

    public void incrementZoom() {
        zoom += ZOOM_STEP;
    }

    public void decrementZoom() {
        if (zoom > ZOOM_STEP) {
            zoom -= ZOOM_STEP;
        }
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

    private double create2DxCoordinate(Point point) {
        return point.x * zoom / point.z;
    }

    private double create2DyCoordinate(Point point) {
        return point.y * zoom / point.z;
    }

    private static boolean isBehindVieport(Point point) {
        return point.z <= 0;
    }

    private static Point clipToVisible(Point visible, Point notVisible) {
        Point point = new Point(0, 0, 1);

        double depth = Math.abs(visible.z) + Math.abs(notVisible.z);
        double ratio = (visible.z + 1.0) / (depth);

        point.x = (visible.x + notVisible.x) * ratio;
        point.y = (visible.y + notVisible.y) * ratio;
        return point;
    }

    private static boolean isVisible(Point point) {
        return point.z >= 1;
    }

    private Point2D convertToPoint2D(double x, double y) {
        return new Point2D.Double(viewport.getWidth() / 2 + x, viewport.getHeight() / 2 - y);
    }
}
