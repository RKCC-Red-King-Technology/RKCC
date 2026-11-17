package editor;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/** Job: the styled text area wrapped in a themed scroll pane. */
public class EditorPane extends JScrollPane {

    private final JTextArea textArea = new JTextArea();

    public EditorPane() {
        super(VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setViewportView(textArea);

        textArea.setBackground(Theme.EDITOR_BG);
        textArea.setForeground(Theme.FG);
        textArea.setCaretColor(Theme.CARET);
        textArea.setSelectionColor(Theme.SELECTION_BG);
        textArea.setSelectedTextColor(Color.WHITE);
        textArea.setFont(new Font("Consolas", Font.PLAIN, 16));
        textArea.setTabSize(4);
        textArea.setBorder(new EmptyBorder(10, 14, 10, 14));

        setBorder(BorderFactory.createEmptyBorder());
        getViewport().setBackground(Theme.EDITOR_BG);

        getVerticalScrollBar().setUI(new FlatScrollBarUI());
        getHorizontalScrollBar().setUI(new FlatScrollBarUI());
        getVerticalScrollBar().setPreferredSize(new Dimension(12, 0));
        getHorizontalScrollBar().setPreferredSize(new Dimension(0, 12));

        JPanel corner = new JPanel();
        corner.setBackground(Theme.EDITOR_BG);
        setCorner(LOWER_RIGHT_CORNER, corner);

        // Children must not inherit the resize cursor from the root panel
        textArea.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        setCursor(Cursor.getDefaultCursor());
        getViewport().setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        getVerticalScrollBar().setCursor(Cursor.getDefaultCursor());
        getHorizontalScrollBar().setCursor(Cursor.getDefaultCursor());
    }

    public JTextArea getTextArea() {
        return textArea;
    }
}
