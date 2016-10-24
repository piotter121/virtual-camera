/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtual.camera.model;

import java.awt.Color;
import static java.awt.Color.BLACK;

/**
 *
 * @author Piotr Pyśk
 */
public class Line {

    private Point start, end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    public Color getColor() {
        return BLACK;
    }

    void transform(Matrix transformation) {
        start.transform(transformation);
        end.transform(transformation);
    }

    double getMinZCoordinate() {
        return start.z < end.z ? start.z : end.z;
    }

}
