/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wirtualna.kamera.gui;

import java.awt.Graphics;
import javax.swing.JPanel;
import wirtualna.kamera.model.Scene;

/**
 *
 * @author Piotr Pyśk
 */
public class CameraPanel extends JPanel {
    
    private Scene scene;
    
    public CameraPanel() {
        this.scene = new Scene();
    }
    
    public CameraPanel(Scene scene) {
        this.scene = scene;
    }

    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs);
        
    }
    
    
    
}
