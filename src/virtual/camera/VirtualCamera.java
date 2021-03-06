/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtual.camera;

import java.awt.event.ActionEvent;
import virtual.camera.painters.SimpleProjection;
import javax.swing.SwingUtilities;
import virtual.camera.gui.CameraPanel;
import virtual.camera.gui.MainWindow;
import virtual.camera.model.Matrix;
import virtual.camera.model.Scene;
import virtual.camera.painters.Painter;
import virtual.camera.painters.PaintersAlghoritm;

/**
 *
 * @author Piotr Pyśk
 */
public class VirtualCamera {

    private static final double TRANSLATION_UNIT = 0.25;
    private static final double ROTATION_UNIT = Math.toRadians(0.5);
    public static final int ZOOM_STEP = 20;

    private final MainWindow mainWindow;
    private final CameraPanel cameraPanel;
    private Painter controller;
    private final Scene scene;

    public VirtualCamera(Scene scene) {
        this.scene = scene;
        this.controller = new PaintersAlghoritm(this.scene);
        this.cameraPanel = new CameraPanel(controller);
        this.mainWindow = new MainWindow(this.cameraPanel);
        mainWindow.addKeyListener(new KeyController(this));
        mainWindow.getSimpleProjectionMenuItem().addActionListener((ae) -> {
            changePainter(new SimpleProjection(scene));
            refreshView();
        });
        mainWindow.getPainterAlghoritmMenuItem().addActionListener((ae) -> {
            changePainter(new PaintersAlghoritm(scene));
            refreshView();
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scene sampleScene = Scene.prepareSample();
        VirtualCamera virtualCamera = new VirtualCamera(sampleScene);
        virtualCamera.show();
    }

    private void show() {
        SwingUtilities.invokeLater(() -> {
            mainWindow.setVisible(true);
        });
    }

    private void changePainter(Painter painter) {
        this.controller = painter;
        cameraPanel.setPainter(painter);
    }

    void translateLeft() {
        translateX(1);
    }

    void translateRigth() {
        translateX(-1);
    }

    void translateForward() {
        translateZ(-1);
    }

    void translateBackward() {
        translateZ(1);
    }

    void rotateLeft() {
        rotateY(1);
    }

    void rotateRight() {
        rotateY(-1);
    }

    void rotateUp() {
        rotateX(1);
    }

    void rotateDown() {
        rotateX(-1);
    }

    void zoomIn() {
        controller.incrementZoom();
    }

    void zoomOut() {
        controller.decrementZoom();
    }

    private void translateX(int direction) {
        Matrix tr = Matrix.translationOX(direction * TRANSLATION_UNIT);
        scene.transform(tr);
    }

    private void translateZ(int direction) {
        Matrix tr = Matrix.translationOZ(direction * TRANSLATION_UNIT);
        scene.transform(tr);
    }

    private void rotateY(int direction) {
        Matrix tr = Matrix.rotationOY(direction * ROTATION_UNIT);
        scene.transform(tr);
    }

    private void rotateX(int direction) {
        Matrix tr = Matrix.rotationOX(direction * ROTATION_UNIT);
        scene.transform(tr);
    }

    void refreshView() {
        cameraPanel.repaint();
    }

}
