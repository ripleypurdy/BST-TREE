import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
public class Main extends JFrame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;
    private TreePanel treePanel = null;
    private JScrollPane scroll = null;
    enum TreeType {
        BASE, BST, BST_EXTRA, TICTACTOE_TREE
    };
    public static void main(String[] args) throws InterruptedException {
        Main theGUI = new Main();
        // Starts the UI Thread and creates the the UI in that thread.
        // Uses a Lambda Expression to call the createFrame method.
        // Use theGUI as the semaphore object
        SwingUtilities.invokeLater(() -> theGUI.createFrame(theGUI));
    }

    /**
     * Create the main JFrame and all animation JPanels.
     *
     * @param semaphore The object to notify when complete
     */
    public void createFrame(Object semaphore) {
        // Sets the title found in the Title Bar of the JFrame
        this.setTitle("Tree");
        // Sets the size of the main Window
        this.setSize(WIDTH, HEIGHT);
        // Allows the application to properly close when the
        // user clicks on the Red-X.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addMenuBar();
        // Add the Tree panel first
        treePanel = new TreePanel();
        // Set the size depending on the supposed Tree size
        treePanel.setSize(WIDTH * 2, HEIGHT * 4);
        // allow scroll panel to manage visibility of the Tree panel
        treePanel.setVisible(true);
        scroll = new JScrollPane(treePanel);
        scroll.setBounds(0, 0, WIDTH, HEIGHT);
        scroll.setVisible(true);
        add(scroll);
        // don't pack. Leave the main frame at its original size.
        // Packing causes the main JFrame to be larger/smaller depending
        // on the size of its children components.
        // this.pack();
        // create a Tree to show in the Tree Panel by
        // setting the root of the treePanel
        setTree(TreeType.BASE);
        // Set the current frame and this JFrame to be visible
        this.setVisible(true);
    }

    private void setTree(TreeType type) {
        Tree result = null;
        switch (type) {
            case BASE:
            default:
                result = Tree.createSomeTree();
                break;
            case BST:
                result = BSTTree.createSomeTree();
                break;
            case BST_EXTRA:
                result = BSTExtraTree.createSomeTree();
                break;
                // case TICTACTOE_TREE:
                // result = TicTacToeTree.createSomeTree();
                // break;
        }
        treePanel.setRoot(result.getRoot());
    }

    /**
     * Add some menu options to control the experience.
     */
    private void addMenuBar() {
        JMenuBar bar = new JMenuBar();
        // Add the menu bar to the JFrame
        this.setJMenuBar(bar);
        // Add Canvas Top-Level menu
        JMenu menu = new JMenu("Canvas");
        menu.setMnemonic('C');
        bar.add(menu);
        // add all submenu items to the Canvas
        JMenuItem item = new JMenuItem("Increase Tree Canvas", 'I');
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS,
                InputEvent.CTRL_DOWN_MASK));
        item.addActionListener(e -> treePanel.changeSize(1));
        menu.add(item);
        item = new JMenuItem("Decrease Tree Canvas", 'd');
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS,
                InputEvent.CTRL_DOWN_MASK));
        item.addActionListener(e -> treePanel.changeSize(-1));
        menu.add(item);
        // Add Tree Top-Level menu
        menu = new JMenu("Tree");
        menu.setMnemonic('T');
        bar.add(menu);
        // add all submenu items to the Canvas
        ButtonGroup treeGroup = new ButtonGroup();
        item = new JRadioButtonMenuItem("Base", true);
        item.setMnemonic('B');
        item.addActionListener(e -> setTree(TreeType.BASE));
        treeGroup.add(item);
        menu.add(item);
        item = new JRadioButtonMenuItem("BST");
        item.setMnemonic('S');
        item.addActionListener(e -> setTree(TreeType.BST));
        treeGroup.add(item);
        menu.add(item);
        item = new JRadioButtonMenuItem("BST Extra");
        item.setMnemonic('E');
        item.addActionListener(e -> setTree(TreeType.BST_EXTRA));
        treeGroup.add(item);
        menu.add(item);
        // item = new JRadioButtonMenuItem("TicTacToe");
        // item.setMnemonic('T');
        // item.addActionListener(e -> setTree(TreeType.TICTACTOE_TREE));
        // treeGroup.add(item);
        // menu.add(item);
        // Add Test Top-Level menu
        menu = new JMenu("Test");
        menu.setMnemonic('S');
        bar.add(menu);
        // item = new JMenuItem("TicTacToe");
        // item.addActionListener(e -> TestStudent.testTTTTree());
        // menu.add(item);
        item = new JMenuItem("BST Extra");
        item.addActionListener(e -> TestStudent.testBSTTree());
        menu.add(item);
    }
}
