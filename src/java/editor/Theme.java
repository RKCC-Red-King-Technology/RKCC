package editor;

import java.awt.Color;

/** Job: hold every color and size constant used by the UI. */
public final class Theme {

    public static final Color TITLE_BG     = new Color(0x16161E);
    public static final Color EDITOR_BG    = new Color(0x1A1B26);
    public static final Color POPUP_BG     = new Color(0x1F2335);
    public static final Color HOVER_BG     = new Color(0x2F3A5F);
    public static final Color BORDER_COLOR = new Color(0x2A2E45);
    public static final Color FG           = new Color(0xC0CAF5);  // Text Color
    public static final Color FG_MUTED     = new Color(0x7A83B0);
    public static final Color ACCENT       = new Color(0x7AA2F7);
    public static final Color CARET        = new Color(0xFF9E64); // Color Of Cursor
    public static final Color SELECTION_BG = new Color(0x33467C);
    public static final Color SCROLL_THUMB = new Color(0x3B4261);
    public static final Color SCROLL_HOVER = new Color(0x545C8A);
    public static final Color CLOSE_HOVER  = new Color(0xE5484D);

    public static final int TITLE_HEIGHT = 34;
    public static final int RESIZE_EDGE  = 5;

    private Theme() { }
}
