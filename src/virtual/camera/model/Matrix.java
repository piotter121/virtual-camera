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
public class Matrix {

    private int rows, columns;
    private double[][] values;

    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        values = new double[this.rows][this.columns];
        insertZeroes();
    }

    private void insertZeroes() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                values[i][j] = 0;
            }
        }
    }

    public static Matrix translation(double xTrans, double yTrans, double zTrans) {
        Matrix matrix = new Matrix(4, 4);
        double[][] values = {
            {1, 0, 0, xTrans},
            {0, 1, 0, yTrans},
            {0, 0, 1, zTrans},
            {0, 0, 0, 1}
        };
        matrix.values = values;
        return matrix;
    }

    public static Matrix translationOX(double xTrans) {
        return translation(xTrans, 0, 0);
    }

    public static Matrix translationOY(double yTrans) {
        return translation(0, yTrans, 0);
    }

    public static Matrix translationOZ(double zTrans) {
        return translation(0, 0, zTrans);
    }

    public static Matrix rotationOX(double ox) {
        Matrix m = new Matrix(4, 4);
        double[][] data = {
            {1, 0, 0, 0},
            {0, Math.cos(ox), -Math.sin(ox), 0},
            {0, Math.sin(ox), Math.cos(ox), 0},
            {0, 0, 0, 1}
        };
        m.values = data;
        return m;
    }

    public static Matrix rotationOY(double oy) {
        Matrix m = new Matrix(4, 4);
        double[][] data = {
            {Math.cos(oy), 0, Math.sin(oy), 0},
            {0, 1, 0, 0},
            {-Math.sin(oy), 0, Math.cos(oy), 0},
            {0, 0, 0, 1}
        };
        m.values = data;
        return m;
    }

    public static Matrix rotationOZ(double oz) {
        Matrix m = new Matrix(4, 4);
        double[][] data = {
            {Math.cos(oz), -Math.sin(oz), 0, 0},
            {Math.sin(oz), Math.cos(oz), 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1}
        };
        m.values = data;
        return m;
    }

    public void set(int row, int col, double value) {
        values[row][col] = value;
    }

    public double get(int row, int col) {
        return values[row][col];
    }

    @Override
    public String toString() {
        String rep = new String();
        for (double[] row : values) {
            rep += "| ";
            for (double value : row) {
                rep += value + " ";
            }
            rep += " |\n";
        }
        return rep;
    }

    public Matrix multiply(Matrix b) {
        Matrix a = this;
        if (a.columns != b.rows) {
            throw new IllegalArgumentException(a.columns + " != " + b.rows);
        }
        Matrix c = new Matrix(a.rows, b.columns);
        for (int x = 0; x < c.rows; x++) {
            for (int y = 0; y < c.columns; y++) {
                double value = 0;
                for (int i = 0; i < c.rows; i++) {
                    value += a.get(x, i) * b.get(i, y);
                }
                c.set(x, y, value);
            }
        }
        return c;
    }

    public Point toPoint() {
        return new Point(get(0, 0), get(1, 0), get(2, 0));
    }

}
