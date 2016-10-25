/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtual.camera.model;

/**
 *
 * @author piotr
 */
public class Surface {

    private final double a, b, c, d;

    public Surface(Point a, Point b, Point c) {
        Point abc = (b.substract(a)).vectorProduct(c.substract(a));
        this.a = abc.x;
        this.b = abc.y;
        this.c = abc.z;
        this.d = -(this.a * a.x + this.b * a.y + this.c * a.z);
    }

    public double evaluate(Point p) {
        return a * p.x + b * p.y + c * p.z + d;
    }

    public boolean areOnTheSameSide(Point point1, Point point2) {
        return evaluate(point2) * evaluate(point1) > 0;
    }

    public boolean areOnTheOpositeSides(Point point1, Point point2) {
        return evaluate(point2) * evaluate(point1) < 0;
    }

    public boolean contains(Point point) {
        return evaluate(point) == 0;
    }

}
