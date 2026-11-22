package editor;

import java.awt.Cursor;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/** Job: own the window state (maximize / restore) and nothing else. */
public class WindowController {

    private final JFrame frame;
    private final JPanel rootPanel;
    private WindowButton maxButton;
    private boolean maximized = false;
    private Rectangle restoreBounds;

    public WindowController(JFrame frame, JPanel rootPanel) {
        this.frame = frame;
        this.rootPanel = rootPanel;
    }

    public void setMaxButton(WindowButton maxButton) {
        this.maxButton = maxButton;
    }

    public boolean isMaximized() {
        return maximized;
    }

    public void minimize() {
        frame.setState(JFrame.ICONIFIED);
    }

    public void close() {
        frame.dispose();
        System.exit(0);
    }

    public void toggleMaximize() {
        if (maximized) {
            frame.setBounds(restoreBounds);
            maximized = false;
        } else {
            restoreBounds = frame.getBounds();
            // respects the taskbar
            frame.setBounds(GraphicsEnvironment
                .getLocalGraphicsEnvironment().getMaximumWindowBounds());
            maximized = true;
        }
        if (maxButton != null) {
            maxButton.setRestoreIcon(maximized);
        }
        int edge = maximized ? 0 : Theme.RESIZE_EDGE;
        rootPanel.setBorder(new EmptyBorder(edge, edge, edge, edge));
        rootPanel.revalidate();

        // The resize zone changed, so always reset the cursor
        rootPanel.setCursor(Cursor.getDefaultCursor());
    }
}
