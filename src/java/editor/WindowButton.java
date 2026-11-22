package editor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;

/** Job: draw one title-bar button (minimize, maximize/restore or close). */
public class WindowButton extends JButton {

    public static final int MIN = 0, MAX = 1, CLOSE = 2;

    private final int type;
    private boolean restoreIcon = false;

    public WindowButton(int type) {
        this.type = type;
        setPreferredSize(new Dimension(46, Theme.TITLE_HEIGHT - 1));
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setRolloverEnabled(true);
        setFocusable(false);
    }

    public void setRestoreIcon(boolean restore) {
        this.restoreIcon = restore;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        boolean hover = getModel().isRollover();
        if (hover) {
            g2.setColor(type == CLOSE ? Theme.CLOSE_HOVER : Theme.HOVER_BG);
            g2.fillRect(0, 0, getWidth(), getHeight());
        }

        g2.setColor(hover && type == CLOSE ? Color.WHITE : Theme.FG);
        g2.setStroke(new BasicStroke(1.2f));
        int cx = getWidth() / 2;
        int cy = getHeight() / 2;

        switch (type) {
            case MIN:
                g2.drawLine(cx - 5, cy, cx + 5, cy);
                break;
            case MAX:
                if (restoreIcon) {
                    g2.drawRect(cx - 5, cy - 2, 7, 7);
                    g2.drawLine(cx - 3, cy - 4, cx + 5, cy - 4);
                    g2.drawLine(cx + 5, cy - 4, cx + 5, cy + 3);
                } else {
                    g2.drawRect(cx - 5, cy - 5, 10, 10);
                }
                break;
            default:
                g2.drawLine(cx - 5, cy - 5, cx + 5, cy + 5);
                g2.drawLine(cx - 5, cy + 5, cx + 5, cy - 5);
                break;
        }
        g2.dispose();
    }
}
