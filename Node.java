/*
 * This is the Base Node class.
 * This class has a list of children.
 * This class prints its adress since it has no information.
 *
 * Derived classes should:
 * - have some information.
 * - override toString()
 */
import java.util.ArrayList;
import java.util.List;
public class Node {
    private List<Node> children;
    public Node() {
        children = new ArrayList<>();
    }

    public void addChild(Node node) {
        children.add(node);
    }

    public List<Node> getChildren() {
        return children;
    }
    // children should override
    public void clickEvent(int modifiers) {
        System.out.println("That tickled!");
    }

    public String getNodeDisplay() {
        return "A";
    }
}