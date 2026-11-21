package editor;

/** Job: application entry point. */
public class Main {

    public static void main(String[] args) {
        ThemeInstaller.install();
        new EditorFrame();
    }
}
