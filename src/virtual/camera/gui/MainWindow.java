/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtual.camera.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.WindowConstants;

/**
 *
 * @author piotr
 */
public class MainWindow extends JFrame {

    private CameraPanel camera;
    private JPanel infoPanel;
    private JRadioButtonMenuItem simpleProjectionMenuItem;
    private JRadioButtonMenuItem painterAlghoritmMenuItem;

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
        JMenuBar mb = new JMenuBar();
        JMenu optionsMenu = new JMenu("Opcje");
        simpleProjectionMenuItem = new JRadioButtonMenuItem("Bez usuwania powierzchni załsoniętych");
        painterAlghoritmMenuItem = new JRadioButtonMenuItem("Algorytm malarski");
        optionsMenu.add(simpleProjectionMenuItem);
        optionsMenu.add(painterAlghoritmMenuItem);
        ButtonGroup paintersButtonGroup = new ButtonGroup();
        paintersButtonGroup.add(simpleProjectionMenuItem);
        paintersButtonGroup.add(painterAlghoritmMenuItem);
        painterAlghoritmMenuItem.setSelected(true);
        mb.add(optionsMenu);
        setJMenuBar(mb);
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

    public JMenuItem getSimpleProjectionMenuItem() {
        return simpleProjectionMenuItem;
    }

    public JMenuItem getPainterAlghoritmMenuItem() {
        return painterAlghoritmMenuItem;
    }

}
