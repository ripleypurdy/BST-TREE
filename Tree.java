import java.util.Random;

//Implements a Tree that acts like a BST (Binary Search Tree)
public class Tree {
    private Node root;
    // Note that we have no Default constructor
    public Tree(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    /**
     * Add this specific node to the tree in a random way.
     *
     * @param node The node to add
     * @return true if successful
     */
    
    
    public boolean add(Node nodeToAdd) {
         Node node = getRoot();
        boolean done = false;
    do {
        done = true;
        if (node.getChildren().size() > 0) {
            if (Math.random() > 0.3) {
                int ichild = (int) (Math.random() * node.getChildren().size());
                 node = node.getChildren().get(ichild);
                 done = false;
            }
        } 
    } while (!done);
    
        node.addChild(nodeToAdd);
        return false;
    }

    /**
     * Create a node and add it randomly to the Tree.
     * @return true if successful
     */
    public boolean add() {
        return add(new Node());
    }

    public static Tree createSomeTree() {
        // create an empty tree with only a root
        Random rand = new Random();
        Tree tree = new Tree(new Node());
        
        int count = rand.nextInt(10,31);
        for (int i =0; i < count; i++) {
            tree.add();
        }

        return tree;
    }
}