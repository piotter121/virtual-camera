/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtual.camera.model;

/**
 *
 * @author Piotr Pyśk
 */
public class Point {

    public double x, y, z;

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Matrix toMatrix() {
        Matrix m = new Matrix(4, 1);
        m.set(0, 0, x);
        m.set(1, 0, y);
        m.set(2, 0, z);
        m.set(3, 0, 1);
        return m;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

    @Override
    public Point clone() {
        return new Point(x, y, z);
    }

}
