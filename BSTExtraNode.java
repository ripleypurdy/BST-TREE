public class BSTExtraNode extends BSTNode {
    // cached instance fields
    private int totalCount;   // number of nodes in this subtree (including this)
    private int height;       // leaf = 0
    private int totalSum;     // sum of values in this subtree (including this)

    public BSTExtraNode(int value) {
        super(value);
        totalCount = 1;
        height = 0;
        totalSum = value;
    }

    @Override
    public String toString() {
        // Tests print "Expected: Value: ..., Count: ...\nHeight: ..., Total: ..."
        // so match that formatting.
        return String.format("Value: %d, Count: %d\nHeight: %d, Total: %d",
            getInfo(), totalCount, height, totalSum);
    }

    public void processInfo() {
        // compute and cache all values for THIS subtree
        this.totalCount = countNodes();
        this.height = calculateHeight();
        this.totalSum = sumTotal();
    }

    public boolean contains(BSTExtraNode other) {
        if (other == null) return false;

        if (this.compareTo(other) == 0) {
            return true;
        }

        // Use BST property to prune search
        if (other.compareTo(this) < 0) {
            return (getLeft() != null) && ((BSTExtraNode) getLeft()).contains(other);
        } else {
            return (getRight() != null) && ((BSTExtraNode) getRight()).contains(other);
        }
    }

    // Recursively count nodes in this subtree
    private int countNodes() {
        int leftCount = 0;
        int rightCount = 0;

        if (getLeft() != null) {
            leftCount = ((BSTExtraNode) getLeft()).countNodes();
        }
        if (getRight() != null) {
            rightCount = ((BSTExtraNode) getRight()).countNodes();
        }

        this.totalCount = 1 + leftCount + rightCount;
        return this.totalCount;
    }

    // Recursively calculate height (leaf = 0)
    private int calculateHeight() {
        int leftHeight = -1;
        int rightHeight = -1;

        if (getLeft() != null) {
            leftHeight = ((BSTExtraNode) getLeft()).calculateHeight();
        }
        if (getRight() != null) {
            rightHeight = ((BSTExtraNode) getRight()).calculateHeight();
        }

        this.height = 1 + Math.max(leftHeight, rightHeight);
        return this.height;
    }

    // Recursively calculate sum of subtree
    private int sumTotal() {
        int leftSum = 0;
        int rightSum = 0;

        if (getLeft() != null) {
            leftSum = ((BSTExtraNode) getLeft()).sumTotal();
        }
        if (getRight() != null) {
            rightSum = ((BSTExtraNode) getRight()).sumTotal();
        }

        this.totalSum = getInfo() + leftSum + rightSum;
        return this.totalSum;
    }
}
