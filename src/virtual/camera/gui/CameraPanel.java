/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtual.camera.gui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Collection;
import javax.swing.JPanel;
import virtual.camera.model.Line;
import virtual.camera.model.Point;
import virtual.camera.model.Scene;

/**
 *
 * @author Piotr Pyśk
 */
public class CameraPanel extends JPanel {

    private static final int ZOOM_STEP = 20;

    private Scene scene;
    private double zoom = 200;
    private Rectangle frame;
    private Graphics graphics;

    public CameraPanel() {
        this.scene = new Scene();
    }

    public CameraPanel(Scene scene) {
        this.scene = scene;
    }

    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs);
        graphics = grphcs;
        paintScene();
    }

    private void paintScene() {
        cutToFrame();
        Collection<Line> linesToDraw = scene.getLines();
        linesToDraw.stream().forEach((line) -> {
            drawLine(line);
        });
    }

    public void incrementZoom() {
        zoom += ZOOM_STEP;
    }

    public void decrementZoom() {
        zoom -= ZOOM_STEP;
    }

    private void cutToFrame() {
        frame = graphics.getClip().getBounds();
    }

    private void drawLine(Line line) {
        graphics.setColor(line.getColor());
        Point start = line.getStart();
        Point end = line.getEnd();
        if (isBehindObserver(start) && isBehindObserver(end)) {
            return;
        }
        int x1, y1, x2, y2;
        x1 = create2DxCoordinate(start);
        y1 = create2DyCoordinate(start);
        x2 = create2DxCoordinate(end);
        y2 = create2DyCoordinate(end);
    }

    private int create2DxCoordinate(Point point) {
        return (int) (point.x * zoom / point.z);
    }

    private int create2DyCoordinate(Point point) {
        return (int) (point.y * zoom / point.z);
    }

    private boolean isBehindObserver(Point point) {
        return point.z <= 0;
    }

}
