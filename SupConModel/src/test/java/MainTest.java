import java.awt.*;

public class MainTest {

    public static void main(String[] args) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontFamilies = ge.getAvailableFontFamilyNames();
        for (String font : fontFamilies) {
            System.out.println(font);
        }
    }
}