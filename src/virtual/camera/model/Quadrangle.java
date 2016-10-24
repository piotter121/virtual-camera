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
public class Quadrangle extends Polygon {

    private final List<Line> lines;

    public Quadrangle(Point a, Point b, Point c, Point d, Color color) {
        super(color);
        lines = new ArrayList<>(4);
        lines.add(new Line(a, b));
        lines.add(new Line(b, c));
        lines.add(new Line(c, d));
        lines.add(new Line(d, a));
    }

    @Override
    public List<Line> getLines() {
        return lines;
    }

}
