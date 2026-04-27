import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class BSTExtraTree extends BSTTree {
    public BSTExtraTree(BSTExtraNode root) {
        super(root);
    }

    @Override
    public BSTNode createNewNode(int value) {
        return new BSTExtraNode(value);
    }

    public boolean contains(int value) {
        BSTNode cur = (BSTNode) getRoot();
        while (cur != null) {
            if (value == cur.getInfo()) return true;
            if (value < cur.getInfo()) cur = cur.getLeft();
            else cur = cur.getRight();
        }
        return false;
    }

    @Override
    public boolean add(int value) {
        // no duplicates allowed
        if (contains(value)) return false;

        boolean ok = super.add(value);

        // keep cached info accurate if someone adds after creation
        BSTExtraNode root = (BSTExtraNode) getRoot();
        root.processInfo();
        return ok;
    }

    public static BSTExtraTree createSomeTree() {
        // root near 50
        int value = 50;
        BSTExtraNode root = new BSTExtraNode(value);
        BSTExtraTree tree = new BSTExtraTree(root);

        Random rand = new Random();
        int totalNodes = rand.nextInt(15, 26); // inclusive 15..25
        Set<Integer> used = new HashSet<>();
        used.add(value);

        while (used.size() < totalNodes) {
            int v = rand.nextInt(0, 100);
            if (used.add(v)) {
                tree.add(v); // our add() blocks duplicates anyway
            }
        }

        // Process all cached info in our tree
        root.processInfo();
        return tree;
    }
}