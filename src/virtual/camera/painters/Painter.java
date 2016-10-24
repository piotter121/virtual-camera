/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtual.camera.painters;

import java.awt.Graphics2D;
import static virtual.camera.VirtualCamera.ZOOM_STEP;
import virtual.camera.model.Point;
import virtual.camera.model.Scene;

/**
 *
 * @author Piotr Pyśk
 */
public abstract class Painter {

    protected final Scene scene;
    protected double zoom = 200;

    protected Painter(Scene scene) {
        this.scene = scene;
    }

    public abstract void paintScene(Graphics2D graphics);

    public void incrementZoom() {
        zoom += ZOOM_STEP;
    }

    public void decrementZoom() {
        if (zoom > ZOOM_STEP) {
            zoom -= ZOOM_STEP;
        }
    }

    protected double create2DxCoordinate(Point point) {
        return point.x * zoom / point.z;
    }

    protected double create2DyCoordinate(Point point) {
        return point.y * zoom / point.z;
    }

    protected static boolean isBehindVieport(Point point) {
        return point.z <= 0;
    }

    protected static Point clipToVisible(Point visible, Point notVisible) {
        Point point = new Point(0, 0, 1);

        double depth = Math.abs(visible.z) + Math.abs(notVisible.z);
        double ratio = (visible.z + 1.0) / (depth);

        point.x = (visible.x + notVisible.x) * ratio;
        point.y = (visible.y + notVisible.y) * ratio;
        return point;
    }
}
