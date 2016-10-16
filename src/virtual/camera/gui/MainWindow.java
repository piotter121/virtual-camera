/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtual.camera.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *
 * @author piotr
 */
public class MainWindow extends JFrame {

    private CameraPanel camera;
    private JPanel infoPanel;

    public MainWindow(CameraPanel camera) {
        super("Wirtualna kamera");
        this.camera = camera;
        initComponents();
    }

    private void initComponents() {
        infoPanel = new JPanel();
        initInfoPanel();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(800, 600));
        setLayout(new BorderLayout());
        add(camera);
        add(infoPanel, BorderLayout.SOUTH);
    }

    private void initInfoPanel() {
        infoPanel.add(new JLabel("WS - translacja w poziomie"));
        infoPanel.add(new JLabel("AD - translacja w pionie"));
        infoPanel.add(new JLabel("STRZAŁKI - obroty"));
        infoPanel.add(new JLabel("+/- - zoom/oddalanie"));
    }

}
