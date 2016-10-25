/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtual.camera.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Piotr Pyśk
 */
public class Scene {

    private final List<Polygon> polygons;

    public Scene(Polygon... polygons) {
        this(Arrays.asList(polygons));
    }

    public Scene(List<Polygon> polygons) {
        this.polygons = new ArrayList<>(polygons.size());
        this.polygons.addAll(polygons);
    }

    public static Scene prepareSample() {
        List<Polygon> polygons = new ArrayList<>();
        Cuboid cuboid1 = new Cuboid(new Point(-20, -10, 40), -50, 20, 40, Color.CYAN);
        Cuboid cuboid2 = new Cuboid(new Point(-20, -10, 100), -50, 50, 20, Color.BLUE);
        Cuboid cuboid3 = new Cuboid(new Point(20, -10, 40), 50, 50, 45, Color.GREEN);
        Cuboid cuboid4 = new Cuboid(new Point(20, -10, 100), 35, 55, 50, Color.RED);
        cuboid2.transform(Matrix.rotationOY(45));
        cuboid3.transform(Matrix.rotationOY(60));
        cuboid3.transform(Matrix.translationOZ(20));
        polygons.addAll(cuboid1.getWalls());
        polygons.addAll(cuboid2.getWalls());
        polygons.addAll(cuboid3.getWalls());
        polygons.addAll(cuboid4.getWalls());
        return new Scene(polygons);
    }

    public void transform(Matrix tr) {
        polygons.stream().forEach((polygon) -> {
            polygon.transform(tr);
        });
    }

    public List<Polygon> getPolygons() {
        return polygons;
    }
}
