package editor;

import java.awt.Cursor;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/** Job: build the File / Edit / About menus and report Exit clicks. */
public class EditorMenuBar extends JMenuBar {

    private final JMenuItem itemNew = new JMenuItem("New");
    private final JMenuItem itemOpen = new JMenuItem("Open");
    private final JMenuItem itemSave = new JMenuItem("Save");
    private final JMenuItem itemSaveAs = new JMenuItem("Save As");
    private final JMenuItem itemExit = new JMenuItem("Exit");

    private final JMenuItem itemCut = new JMenuItem("Cut");
    private final JMenuItem itemCopy = new JMenuItem("Copy");
    private final JMenuItem itemPaste = new JMenuItem("Paste");

    private final JMenuItem itemAboutUs = new JMenuItem("About Us");

    public EditorMenuBar(Runnable onExit) {
        setBorder(BorderFactory.createEmptyBorder());
        setCursor(Cursor.getDefaultCursor());

        JMenu file = new JMenu("File");
        file.add(itemNew);
        file.add(itemOpen);
        file.add(itemSave);
        file.add(itemSaveAs);
        file.addSeparator();
        file.add(itemExit);

        JMenu edit = new JMenu("Edit");
        edit.add(itemCut);
        edit.add(itemCopy);
        edit.add(itemPaste);

        JMenu about = new JMenu("About");
        about.add(itemAboutUs);

        add(file);
        add(edit);
        add(about);

        itemExit.addActionListener(e -> onExit.run());
    }

    // L&F-independent background painting
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Theme.TITLE_BG);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
