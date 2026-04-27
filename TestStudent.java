import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class TestStudent {
    public static int testsPassed = 0;
    public static int testsFailed = 0;
    public static void main(String[] args) {
        testBSTTree();
        System.out.println("\n\n **SUMMARY**");
        System.out.printf("PASSED: %d FAILED: %d\n", testsPassed, testsFailed);
    }

    public static int countChildren(Node node) {
        List<Node> children = node.getChildren();
        int count = children.size();
        for (Node child : children) {
            count += countChildren(child);
        }
        return count;
    }

    public static void testBSTTree() {
        verifySizesRepeats();
        // repeat tree creation and that trees are different
        verifyDifferent();
        // print student's post-order traversal
        BSTExtraTree tree = BSTExtraTree.createSomeTree();
        BSTExtraNode root = (BSTExtraNode) tree.getRoot();
        System.out.println("Printing in-order: Need to visually inspect for correctness...");
        root.clickEvent(0);
        verifyNodes(root);
    }

    public static int sum(BSTNode node) {
        if (node == null) {
            return 0;
        }
        return node.getInfo() + sum(node.getLeft()) + sum(node.getRight());
    }

    public static void verifyNodes(BSTExtraNode root) {
        System.out.println("Testing that a node prints itself nicely.");
        int count = count(root);
        int height = height(root);
        int value = root.getInfo();
        int sum = sum(root);
        System.out.printf("Expected: Value: %d, Count: %d\nHeight: %d, Total: %d\n",
            value, count, height, sum);
        System.out.printf("Actual: %s\n\n", root.toString());
        // verify contains. travel to right most.
        BSTExtraNode cur = root;
        while (cur.getRight() != null) {
            cur = (BSTExtraNode) cur.getRight();
        }
        boolean positiveContains = root.contains(cur);
        if (positiveContains) {
            testsPassed++;
        } else {
            testsFailed++;
        }
        System.out.printf("%s: Contains right most\n", root.contains(cur) ? "PASS" :
            "FAIL");
        BSTExtraNode out = new BSTExtraNode(98987);
        if (positiveContains) {
            System.out.printf("%s: Does not Contain\n", root.contains(out) ? "FAIL" :
                "PASS");
            if (root.contains(out)) {
                testsFailed++;
            } else {
                testsPassed++;
            }
        } else {
            System.out.println("Skipping negative contains test since positive case failed.");
        }
    }

    public static int height(BSTExtraNode node) {
        // default to height of zero. We are a leaf.
        int height = 0;
        // Find the MAX height of our children and then we are +1 to that.
        // This node may have no non-null children. So, start at -1
        // as the max which would put this at height 0 for no non-null children.
        int maxHeight = -1;
        for (Node child : node.getChildren()) {
            BSTExtraNode enode = (BSTExtraNode) child;
            if (enode != null) {
                // have all children get their height first
                int childHeight = height(enode);
                if (childHeight > maxHeight) {
                    maxHeight = childHeight;
                }
            }
        }
        // This is 1 higher than its max child's height.
        height = maxHeight + 1;
        return height;
    }

    public static void verifySizesRepeats() {
        boolean passed2 = true;
        int passed1 = 0;
        System.out.println("Verifying sizes and no repeats...");
        for (int times = 0; times < 8; times++) {
            BSTExtraTree tree = BSTExtraTree.createSomeTree();
            Set<Integer> set = new HashSet<Integer>();
            int count = count(tree.getRoot());
            // verify size is okay
            if (count < 15 || count > 25) {
                System.out.println("FAILED: improper size");
                passed2 = false;
                continue;
            }
            // verify size is okay and has no repeats
            if (testRepeats(set, (BSTExtraNode) tree.getRoot())) {
                passed1++;
            } else {
                System.out.println("FAILED: Found repeat");
            }
        }
        if (passed1 == 8) {
            testsPassed++;
        } else {
            testsFailed++;
        }
        if (passed2) {
            testsPassed++;
        } else {
            testsFailed++;
        }
    }

    public static void verifyDifferent() {
        BSTExtraTree tree1 = BSTExtraTree.createSomeTree();
        BSTExtraTree tree2 = BSTExtraTree.createSomeTree();
        boolean diff = verifyDifferent((BSTNode) tree1.getRoot(), (BSTNode)
                tree2.getRoot());
        System.out.println("Testing that two trees are created randomly and differently.");
        System.out.printf("%s: Different\n", diff ? "PASS" : "FAIL");
        if (diff) {
            testsPassed++;
        } else {
            testsFailed++;
        }
    }

    public static boolean verifyDifferent(BSTNode n1, BSTNode n2) {
        if (n1 == null && n2 == null) {
            // same
            return false;
        }
        if (n1 == null || n2 == null) {
            // different
            return true;
        }
        if (n1.compareTo(n2) == 0) {
            // so far the same. We are different if either
            // the left or right are different.
            return verifyDifferent(n1.getLeft(), n2.getLeft()) ||
            verifyDifferent(n1.getRight(), n2.getRight());
        }
        // different
        return true;
    }

    public static int count(Node node) {
        if (node == null) {
            return 0;
        }
        int count = 1;
        for (Node child : node.getChildren()) {
            count += count(child);
        }
        return count;
    }

    public static boolean testRepeats(Set<Integer> set, BSTExtraNode node) {
        if (set.contains(node.getInfo())) {
            return false;
        }
        set.add(node.getInfo());
        if (node.getLeft() != null) {
            if (!testRepeats(set, (BSTExtraNode) node.getLeft())) {
                return false;
            }
        }
        if (node.getRight() != null) {
            if (!testRepeats(set, (BSTExtraNode) node.getRight())) {
                return false;
            }
        }
        return true;
}
}
