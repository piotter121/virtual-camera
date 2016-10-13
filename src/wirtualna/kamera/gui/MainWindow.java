/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wirtualna.kamera.gui;

import javax.swing.JFrame;

/**
 *
 * @author piotr
 */
public class MainWindow extends JFrame {
    private CameraPanel camera;
    
    
    public MainWindow() {
        super("Wirtualna kamera");
        initComponents();
    }
    
    private void initComponents() {
        camera = new CameraPanel();
    }
    
}
