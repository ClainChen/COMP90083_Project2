package supconmodel.model.gui.prefab;

import javax.swing.tree.DefaultMutableTreeNode;
import supconmodel.model.agents.Good;

public class GoodTreeNode extends DefaultMutableTreeNode {
    public Good good;
    public GoodTreeNode(Good good) {
        super(good.name);
        this.good = good;
    }
}
