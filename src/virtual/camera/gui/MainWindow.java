/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtual.camera.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
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
        pack();
    }

    private void initComponents() {
        infoPanel = new JPanel(new FlowLayout());
        initInfoPanel();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(camera, gbc);
        gbc.weighty = 0;
        gbc.gridy = 1;
        add(infoPanel, gbc);
    }

    private void initInfoPanel() {
        JLabel sterowanieLabel = new JLabel("STEROWANIE:");
        sterowanieLabel.setFont(sterowanieLabel.getFont().deriveFont(Font.BOLD, 14));
        infoPanel.add(sterowanieLabel);
        infoPanel.add(new JLabel("WS - translacja w poziomie"));
        infoPanel.add(new JLabel("AD - translacja w pionie"));
        infoPanel.add(new JLabel("STRZAŁKI - obroty"));
        infoPanel.add(new JLabel("+/- - zoom/oddalanie"));
        infoPanel.setBorder(BorderFactory.createEtchedBorder());
    }

}
