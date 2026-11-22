package editor;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;

/** Job: drag the window by the title bar and maximize on double-click. */
public class WindowDragHandler extends MouseAdapter {

    private final JFrame frame;
    private final WindowController controller;
    private Point pressScreen;
    private Point frameStart;

    public WindowDragHandler(JFrame frame, WindowController controller) {
        this.frame = frame;
        this.controller = controller;
    }

    /** Attach this handler to a component. */
    public void attachTo(JComponent c) {
        c.addMouseListener(this);
        c.addMouseMotionListener(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        pressScreen = e.getLocationOnScreen();
        frameStart = frame.getLocation();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            controller.toggleMaximize();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (pressScreen == null) return;

        if (controller.isMaximized()) {
            // restore, keeping the cursor at the same relative spot
            double ratio = (double) e.getXOnScreen() / frame.getWidth();
            controller.toggleMaximize();
            int newX = e.getXOnScreen() - (int) (ratio * frame.getWidth());
            frame.setLocation(newX, e.getYOnScreen() - 15);
            pressScreen = e.getLocationOnScreen();
            frameStart = frame.getLocation();
            return;
        }

        Point now = e.getLocationOnScreen();
        frame.setLocation(
            frameStart.x + now.x - pressScreen.x,
            frameStart.y + now.y - pressScreen.y
        );
    }
}
