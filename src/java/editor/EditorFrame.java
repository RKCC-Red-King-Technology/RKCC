package editor;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/** Job: wire all the parts together into the application window. */
public class EditorFrame {

    private final JFrame frame = new JFrame("Editor");

    public EditorFrame() {
        frame.setUndecorated(true); // we draw our own colored title bar

        JPanel rootPanel = new JPanel(new BorderLayout());
        rootPanel.setBackground(Theme.TITLE_BG);
        rootPanel.setBorder(new EmptyBorder(
            Theme.RESIZE_EDGE, Theme.RESIZE_EDGE, Theme.RESIZE_EDGE, Theme.RESIZE_EDGE));

        WindowController controller = new WindowController(frame, rootPanel);
        WindowDragHandler dragHandler = new WindowDragHandler(frame, controller);
        EditorMenuBar menuBar = new EditorMenuBar(frame::dispose);

        rootPanel.add(new TitleBar(menuBar, controller, dragHandler), BorderLayout.NORTH);
        rootPanel.add(new EditorPane(), BorderLayout.CENTER);
        new ResizeHandler(frame, rootPanel, controller).attach();

        frame.setContentPane(rootPanel);
        frame.setMinimumSize(new Dimension(500, 350));
        frame.setSize(1000, 650);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        controller.toggleMaximize(); // start maximized
    }
}
