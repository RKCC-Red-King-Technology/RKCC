package editor;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/** Job: assemble the custom title bar (logo, menu, title, window buttons). */
public class TitleBar extends JPanel {

    public TitleBar(EditorMenuBar menuBar,
                    WindowController controller,
                    WindowDragHandler dragHandler) {
        super(new BorderLayout());
        setBackground(Theme.TITLE_BG);
        setPreferredSize(new Dimension(0, Theme.TITLE_HEIGHT));
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Theme.BORDER_COLOR));
        setCursor(Cursor.getDefaultCursor());

        // Left: logo + menu bar
        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        left.setOpaque(false);
        JLabel logo = new JLabel("{ }");
        logo.setFont(new Font("Consolas", Font.BOLD, 15));
        logo.setForeground(Theme.ACCENT);
        logo.setBorder(new EmptyBorder(0, 12, 0, 8));
        left.add(logo);
        left.add(menuBar);

        // Center: title text
        JLabel title = new JLabel("Editor", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        title.setForeground(Theme.FG_MUTED);

        // Right: window buttons
        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        right.setOpaque(false);

        WindowButton minButton = new WindowButton(WindowButton.MIN);
        WindowButton maxButton = new WindowButton(WindowButton.MAX);
        WindowButton closeButton = new WindowButton(WindowButton.CLOSE);
        controller.setMaxButton(maxButton);

        minButton.addActionListener(e -> controller.minimize());
        maxButton.addActionListener(e -> controller.toggleMaximize());
        closeButton.addActionListener(e -> controller.close());

        right.add(minButton);
        right.add(maxButton);
        right.add(closeButton);

        add(left, BorderLayout.WEST);
        add(title, BorderLayout.CENTER);
        add(right, BorderLayout.EAST);

        // Drag + double-click on the empty parts of the bar
        dragHandler.attachTo(this);
        dragHandler.attachTo(title);
        dragHandler.attachTo(left);

        // Explicit cursors so nothing inherits the resize cursor
        for (java.awt.Component c : new java.awt.Component[] {
                title, left, right, minButton, maxButton, closeButton }) {
            c.setCursor(Cursor.getDefaultCursor());
        }
    }
}
