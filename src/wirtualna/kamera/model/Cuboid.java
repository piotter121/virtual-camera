/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wirtualna.kamera.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Piotr Pyśk
 */
public class Cuboid {

    private static final int NUMBER_OF_EDGES = 12;
    private static final int NUMBER_OF_CORNERS = 8;

    private Point startPoint, size;
    private Color edgeColor;

    public Cuboid(Point start, Point size) {
        this.startPoint = start;
        this.size = size;
        edgeColor = Color.BLACK;
    }

    public void setEdgeColor(Color edgeColor) {
        this.edgeColor = edgeColor;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getSize() {
        return size;
    }

    public Color getEdgeColor() {
        return edgeColor;
    }

    public List<Line> getEdges() {
        List<Line> edges = new ArrayList<>(NUMBER_OF_EDGES);
        Map<Character, Point> corners = getMapOfCorners();
        // podstawa dolna
        edges.add(new Line(corners.get('A'), corners.get('B')));
        edges.add(new Line(corners.get('A'), corners.get('E')));
        edges.add(new Line(corners.get('E'), corners.get('F')));
        edges.add(new Line(corners.get('F'), corners.get('B')));
        // podstawa górna
        edges.add(new Line(corners.get('D'), corners.get('C')));
        edges.add(new Line(corners.get('C'), corners.get('G')));
        edges.add(new Line(corners.get('G'), corners.get('H')));
        edges.add(new Line(corners.get('H'), corners.get('D')));
        // boczne krawędzie 
        edges.add(new Line(corners.get('A'), corners.get('D')));
        edges.add(new Line(corners.get('B'), corners.get('C')));
        edges.add(new Line(corners.get('E'), corners.get('H')));
        edges.add(new Line(corners.get('F'), corners.get('G')));
        return edges;
    }

    //  ^ y      _
    //  |         /| z
    //    H----G
    //  D----C |  
    //  | |  | |  
    //  | E--|-F
    //  A----B     -> x
    Map<Character, Point> getMapOfCorners() {
        Map<Character, Point> corners = new HashMap<>(NUMBER_OF_CORNERS);
        corners.put('A', startPoint.clone());
        corners.put('B', new Point(startPoint.x + size.x, startPoint.y, startPoint.z));
        corners.put('C', new Point(startPoint.x + size.x, startPoint.y + size.y, startPoint.z));
        corners.put('D', new Point(startPoint.x, startPoint.y + size.y, startPoint.z));
        corners.put('E', new Point(startPoint.x, startPoint.y, startPoint.z + size.z));
        corners.put('F', new Point(startPoint.x + size.x, startPoint.y, startPoint.z + size.z));
        corners.put('G', new Point(startPoint.x + size.x, startPoint.y + size.y, startPoint.z + size.z));
        corners.put('H', new Point(startPoint.x, startPoint.y + size.y, startPoint.z + size.z));
        return corners;
    }

    @Override
    public String toString() {
        return "Cuboid{" + "startPoint=" + startPoint + ", size=" + size + ", edgeColor=" + edgeColor + '}';
    }

}
