/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtual.camera;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_A;
import static java.awt.event.KeyEvent.VK_D;
import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_EQUALS;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_MINUS;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_S;
import static java.awt.event.KeyEvent.VK_UP;
import static java.awt.event.KeyEvent.VK_W;

/**
 *
 * @author piotr
 */
public class KeyController extends KeyAdapter {

    private final VirtualCamera virtualCamera;

    public KeyController(VirtualCamera virtualCamera) {
        this.virtualCamera = virtualCamera;
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        switch (ke.getKeyCode()) {
            case VK_A:
                virtualCamera.translateLeft();
                break;
            case VK_D:
                virtualCamera.translateRigth();
                break;
            case VK_W:
                virtualCamera.translateForward();
                break;
            case VK_S:
                virtualCamera.translateBackward();
                break;
            case VK_LEFT:
                virtualCamera.rotateLeft();
                break;
            case VK_RIGHT:
                virtualCamera.rotateRight();
                break;
            case VK_UP:
                virtualCamera.rotateUp();
                break;
            case VK_DOWN:
                virtualCamera.rotateDown();
                break;
            case VK_EQUALS:
                virtualCamera.zoomIn();
                break;
            case VK_MINUS:
                virtualCamera.zoomOut();
                break;
        }
        virtualCamera.refreshView();
    }

}
