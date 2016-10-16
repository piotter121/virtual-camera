/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtual.camera.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Piotr Pyśk
 */
public class Scene {

    private final List<Line> lines;

    public Scene(Line... lines) {
        this(Arrays.asList(lines));
    }

    public Scene(List<Line> lines) {
        this.lines = new ArrayList<>(lines.size());
        this.lines.addAll(lines);
    }

    public static Scene prepareSample() {
        List<Line> lines = new ArrayList<>();
        lines.addAll(new Cuboid(new Point(-20, -10, 40), -50, 20, 40).getLines());
        return new Scene(lines);
    }

    public void transform(Matrix tr) {
        lines.stream().forEach((line) -> {
            Point p1 = tr.multiply(line.getStart().toMatrix()).toPoint();
            Point p2 = tr.multiply(line.getEnd().toMatrix()).toPoint();
            line.setStart(p1);
            line.setEnd(p2);
        });
    }

    public List<Line> getLines() {
        return lines;
    }
}
