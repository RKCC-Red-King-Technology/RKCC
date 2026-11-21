package editor;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

/** Job: edge resizing for the undecorated frame. */
public class ResizeHandler extends MouseAdapter {

    private static final int LEFT = 1, RIGHT = 2, TOP = 4, BOTTOM = 8;

    private final JFrame frame;
    private final JPanel rootPanel;
    private final WindowController controller;

    private int dir = 0;
    private Point pressScreen;
    private Rectangle startBounds;

    public ResizeHandler(JFrame frame, JPanel rootPanel, WindowController controller) {
        this.frame = frame;
        this.rootPanel = rootPanel;
        this.controller = controller;
    }

    public void attach() {
        rootPanel.addMouseListener(this);
        rootPanel.addMouseMotionListener(this);
    }

    private int hit(MouseEvent e) {
        int w = e.getComponent().getWidth();
        int h = e.getComponent().getHeight();
        int d = 0;
        if (e.getX() < Theme.RESIZE_EDGE) d |= LEFT;
        if (e.getX() >= w - Theme.RESIZE_EDGE) d |= RIGHT;
        if (e.getY() < Theme.RESIZE_EDGE) d |= TOP;
        if (e.getY() >= h - Theme.RESIZE_EDGE) d |= BOTTOM;
        return d;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (controller.isMaximized()) {
            rootPanel.setCursor(Cursor.getDefaultCursor());
            return;
        }
        int d = hit(e);
        int cursor = Cursor.DEFAULT_CURSOR;
        if (d == (LEFT | TOP))          cursor = Cursor.NW_RESIZE_CURSOR;
        else if (d == (RIGHT | TOP))    cursor = Cursor.NE_RESIZE_CURSOR;
        else if (d == (LEFT | BOTTOM))  cursor = Cursor.SW_RESIZE_CURSOR;
        else if (d == (RIGHT | BOTTOM)) cursor = Cursor.SE_RESIZE_CURSOR;
        else if ((d & LEFT) != 0)       cursor = Cursor.W_RESIZE_CURSOR;
        else if ((d & RIGHT) != 0)      cursor = Cursor.E_RESIZE_CURSOR;
        else if ((d & TOP) != 0)        cursor = Cursor.N_RESIZE_CURSOR;
        else if ((d & BOTTOM) != 0)     cursor = Cursor.S_RESIZE_CURSOR;

        rootPanel.setCursor(cursor == Cursor.DEFAULT_CURSOR
            ? Cursor.getDefaultCursor()
            : Cursor.getPredefinedCursor(cursor));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (dir == 0) {
            rootPanel.setCursor(Cursor.getDefaultCursor());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        dir = controller.isMaximized() ? 0 : hit(e);
        pressScreen = e.getLocationOnScreen();
        startBounds = frame.getBounds();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        dir = 0;
        rootPanel.setCursor(Cursor.getDefaultCursor());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (dir == 0) return;
        int dx = e.getXOnScreen() - pressScreen.x;
        int dy = e.getYOnScreen() - pressScreen.y;
        Rectangle r = new Rectangle(startBounds);
        Dimension min = frame.getMinimumSize();

        if ((dir & RIGHT) != 0)  r.width  = Math.max(min.width,  startBounds.width + dx);
        if ((dir & BOTTOM) != 0) r.height = Math.max(min.height, startBounds.height + dy);
        if ((dir & LEFT) != 0) {
            r.width = Math.max(min.width, startBounds.width - dx);
            r.x = startBounds.x + startBounds.width - r.width;
        }
        if ((dir & TOP) != 0) {
            r.height = Math.max(min.height, startBounds.height - dy);
            r.y = startBounds.y + startBounds.height - r.height;
        }
        frame.setBounds(r);
    }
}
