package editor;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

/** Job: install the theme into UIManager (must run BEFORE any component is created). */
public final class ThemeInstaller {

    private ThemeInstaller() { }

    public static void install() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Font uiFont = new Font("Segoe UI", Font.PLAIN, 13);

        UIManager.put("MenuBar.background", Theme.TITLE_BG);
        UIManager.put("MenuBar.foreground", Theme.FG);
        UIManager.put("MenuBar.border", BorderFactory.createEmptyBorder());
        UIManager.put("MenuBar.font", uiFont);

        UIManager.put("Menu.background", Theme.TITLE_BG);
        UIManager.put("Menu.foreground", Theme.FG);
        UIManager.put("Menu.selectionBackground", Theme.HOVER_BG);
        UIManager.put("Menu.selectionForeground", Color.WHITE);
        UIManager.put("Menu.font", uiFont);
        UIManager.put("Menu.border", new EmptyBorder(8, 10, 8, 10));
        UIManager.put("Menu.opaque", true);

        UIManager.put("MenuItem.background", Theme.POPUP_BG);
        UIManager.put("MenuItem.foreground", Theme.FG);
        UIManager.put("MenuItem.selectionBackground", Theme.HOVER_BG);
        UIManager.put("MenuItem.selectionForeground", Color.WHITE);
        UIManager.put("MenuItem.acceleratorForeground", Theme.FG_MUTED);
        UIManager.put("MenuItem.acceleratorSelectionForeground", Color.WHITE);
        UIManager.put("MenuItem.font", uiFont);
        UIManager.put("MenuItem.border", new EmptyBorder(6, 14, 6, 26));
        UIManager.put("MenuItem.opaque", true);

        UIManager.put("PopupMenu.background", Theme.POPUP_BG);
        UIManager.put("PopupMenu.foreground", Theme.FG);
        UIManager.put("PopupMenu.border", BorderFactory.createLineBorder(Theme.BORDER_COLOR));
        UIManager.put("Separator.foreground", Theme.BORDER_COLOR);
        UIManager.put("Separator.background", Theme.POPUP_BG);
    }
}
