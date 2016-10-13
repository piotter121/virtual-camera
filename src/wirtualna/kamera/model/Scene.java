/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wirtualna.kamera.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Piotr Pyśk
 */
public class Scene {
    private List<Cuboid> cuboids;
    
    public Scene(Cuboid... cuboids) {
        this.cuboids = new ArrayList<>();
        this.cuboids.addAll(Arrays.asList(cuboids));
    }
    
    public static Scene prepareSample() {
        return new Scene();
    }
    
    public void addCuboid(Cuboid cuboid) {
        cuboids.add(cuboid);
    }
    
    public void removeCuboid(Cuboid cuboid) {
        cuboids.remove(cuboid);
    }
    
    public List<Cuboid> getCuboids() {
        return cuboids;
    }
}
