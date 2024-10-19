package com.guuri11;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.NativeInputEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import java.awt.*;
import java.awt.event.InputEvent;

/**
 * Hello world!
 */
public class App implements NativeKeyListener {
    private static final int VELOCITY = 6;
    private static boolean ctrlPressed = false;
    private static boolean mayusPressed = false;
    private static boolean endPressed = false;
    private static boolean moveUp = false;
    private static boolean moveDown = false;
    private static boolean moveLeft = false;
    private static boolean moveRight = false;


    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        if (e.getKeyCode() == NativeKeyEvent.VC_CONTROL) {
            ctrlPressed = true;
        }
        if (e.getKeyCode() == NativeKeyEvent.VC_SHIFT) {
            mayusPressed = true;
        }

        if (e.getKeyCode() == NativeKeyEvent.VC_UP) {
            moveUp = true;
        }
        if (e.getKeyCode() == NativeKeyEvent.VC_LEFT) {
            moveLeft = true;
        }
        if (e.getKeyCode() == NativeKeyEvent.VC_DOWN) {
            moveDown = true;
        }
        if (e.getKeyCode() == NativeKeyEvent.VC_RIGHT) {
            moveRight = true;
        }

        if (e.getKeyCode() == NativeKeyEvent.VC_END) {
            endPressed = true;
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {

        if (e.getKeyCode() == NativeKeyEvent.VC_CONTROL) {
            ctrlPressed = false;
        }
        if (e.getKeyCode() == NativeKeyEvent.VC_SHIFT) {
            mayusPressed = false;
        }

        if (e.getKeyCode() == NativeKeyEvent.VC_UP) {
            moveUp = false;
        }
        if (e.getKeyCode() == NativeKeyEvent.VC_LEFT) {
            moveLeft = false;
        }
        if (e.getKeyCode() == NativeKeyEvent.VC_DOWN) {
            moveDown = false;
        }
        if (e.getKeyCode() == NativeKeyEvent.VC_RIGHT) {
            moveRight = false;
        }
        if (e.getKeyCode() == NativeKeyEvent.VC_END) {
            endPressed = false;
        }

    }

    public static void main(String[] args) throws AWTException {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        GlobalScreen.addNativeKeyListener(new App());

        Robot robot = new Robot();

        new Thread(() -> {
            while (true) {
                if (ctrlPressed && mayusPressed) {
                    Point p = MouseInfo.getPointerInfo().getLocation();
                    int targetX = p.x;
                    int targetY = p.y;

                    if (moveUp) {
                        targetY -= VELOCITY;
                    }
                    if (moveDown) {
                        targetY += VELOCITY;
                    }
                    if (moveLeft) {
                        targetX -= VELOCITY;
                    }
                    if (moveRight) {
                        targetX += VELOCITY;
                    }
                    robot.mouseMove(targetX, targetY);

                    if (endPressed) {
                        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        endPressed = false;
                    }
                }
                try {
                    Thread.sleep(10); // avoid cpu overloading
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                    System.exit(1);
                }
            }
        }).start();
    }
}
