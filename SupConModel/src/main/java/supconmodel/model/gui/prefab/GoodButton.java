package supconmodel.model.gui.prefab;

import javax.swing.*;

public class GoodButton extends JButton {
    public String name;
    public GoodButton(String name) {
        this.name = name;
        setText(name);
    }


}
