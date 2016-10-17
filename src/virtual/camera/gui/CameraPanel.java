/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtual.camera.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import virtual.camera.CameraController;

/**
 *
 * @author Piotr Pyśk
 */
public class CameraPanel extends JPanel {

    private CameraController controller;

    public CameraPanel(CameraController controller) {
        this.controller = controller;
        setBackground(Color.WHITE);
    }

    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs);
        Graphics2D graphics = (Graphics2D) grphcs;
        controller.paintScene(graphics);
    }

}
