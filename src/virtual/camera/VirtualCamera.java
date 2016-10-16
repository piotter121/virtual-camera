/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtual.camera;

import javax.swing.SwingUtilities;
import virtual.camera.gui.CameraPanel;
import virtual.camera.gui.MainWindow;
import virtual.camera.model.Matrix;
import virtual.camera.model.Scene;

/**
 *
 * @author Piotr Pyśk
 */
public class VirtualCamera {
    private static final int TRANSLATION_UNIT = 3;
    private static final int ROTATION_UNIT = 3;

    private final MainWindow mainWindow;
    private final CameraPanel cameraPanel;
    private Scene scene;

    public VirtualCamera(Scene scene) {
        this.scene = scene;
        this.cameraPanel = new CameraPanel(this.scene);
        this.mainWindow = new MainWindow(this.cameraPanel);
        mainWindow.addKeyListener(new KeyController(this));
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

    void translateLeft() {
        translateX(-1);
    }

    void translateRigth() {
        translateX(1);
    }

    void translateForward() {
        translateZ(1);
    }

    void translateBackward() {
        translateZ(-1);
    }

    void rotateLeft() {
        rotateY(-1);
    }

    void rotateRight() {
        rotateY(1);
    }

    void rotateUp() {
        rotateX(1);
    }

    void rotateDown() {
        rotateX(-1);
    }

    void zoomIn() {
        zoom(true);
    }

    void zoomOut() {
        zoom(false);
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

    private void zoom(boolean in) {
        if (in) {
            cameraPanel.incrementZoom();
        } else {
            cameraPanel.decrementZoom();
        }
    }

    void refreshView() {
        cameraPanel.repaint();
    }

}
