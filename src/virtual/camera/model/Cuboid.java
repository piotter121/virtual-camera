/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtual.camera.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Piotr Pyśk
 */
public class Cuboid {

    private Point start;
    private double xDim, yDim, zDim;
    private Color edgeColor;
    private List<Line> lines = new ArrayList<>(12);

    public Cuboid(Point start, double xDim, double yDim, double zDim, Color edgeColor) {
        this.start = start;
        this.xDim = xDim;
        this.yDim = yDim;
        this.zDim = zDim;
        this.edgeColor = edgeColor;
        createLines();
    }

    public Cuboid(Point start, double xDim, double yDim, double zDim) {
        this(start, xDim, yDim, zDim, Color.BLACK);
    }

    private void createLines() {
        //  ^ y      _
        //  |         /| z
        //    H----G
        //  D----C |  
        //  | |  | |  
        //  | E--|-F
        //  A----B     -> x
        Point a, b, c, d, e, f, g, h;

        a = start.clone();
        b = new Point(start.x + xDim, start.y, start.z);
        c = new Point(start.x + xDim, start.y + yDim, start.z);
        d = new Point(start.x, start.y + yDim, start.z);
        e = new Point(start.x, start.y, start.z + zDim);
        f = new Point(start.x + xDim, start.y, start.z + zDim);
        g = new Point(start.x + xDim, start.y + yDim, start.z + zDim);
        h = new Point(start.x, start.y + yDim, start.z + zDim);

        lines.add(new Line(a, b, edgeColor));
        lines.add(new Line(b, c, edgeColor));
        lines.add(new Line(c, d, edgeColor));
        lines.add(new Line(d, a, edgeColor));

        lines.add(new Line(e, f, edgeColor));
        lines.add(new Line(f, g, edgeColor));
        lines.add(new Line(g, h, edgeColor));
        lines.add(new Line(h, e, edgeColor));

        lines.add(new Line(a, e, edgeColor));
        lines.add(new Line(b, f, edgeColor));
        lines.add(new Line(c, g, edgeColor));
        lines.add(new Line(d, h, edgeColor));

    }

    public List<Line> getLines() {
        return lines;
    }

    public void transform(Matrix tr) {
        lines.stream().forEach((line) -> {
            Point p1 = tr.multiply(line.getStart().toMatrix()).toPoint();
            Point p2 = tr.multiply(line.getEnd().toMatrix()).toPoint();
            line.setStart(p1);
            line.setEnd(p2);
        });
    }
}
