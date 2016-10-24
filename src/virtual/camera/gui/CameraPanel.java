/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtual.camera.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import virtual.camera.painters.Painter;

/**
 *
 * @author Piotr Pyśk
 */
public class CameraPanel extends JPanel {

    private Painter painter;

    public CameraPanel(Painter painter) {
        this.painter = painter;
    }

    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs);
        Graphics2D graphics = (Graphics2D) grphcs;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        painter.paintScene(graphics);
    }

    public void setPainter(Painter painter) {
        this.painter = painter;
    }

}
