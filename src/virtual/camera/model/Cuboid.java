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

    private final Color color;
    private final List<Quadrangle> walls;

    public Cuboid(Point start, double xDim, double yDim, double zDim, Color color) {
        this.walls = new ArrayList<>(6);
        this.color = color;
        createLines(start, xDim, yDim, zDim);
    }

    private void createLines(Point start, double xDim, double yDim, double zDim) {
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

        walls.add(new Quadrangle(a, b, c, d, color));
        walls.add(new Quadrangle(a, b, f, e, color));
        walls.add(new Quadrangle(e, f, g, h, color));
        walls.add(new Quadrangle(d, c, g, h, color));
        walls.add(new Quadrangle(b, c, g, f, color));
        walls.add(new Quadrangle(a, e, h, d, color));
    }

    public List<Quadrangle> getWalls() {
        return walls;
    }

    public void transform(Matrix tr) {
        walls.stream().forEach((wall) -> {
            wall.transform(tr);
        });
    }
}
