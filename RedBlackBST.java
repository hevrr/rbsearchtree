import edu.princeton.cs.algs4.StdOut;

import java.awt.*;

public class RedBlackBST<T> {

    /* root */
    Node root;

    /* constructor */
    public RedBlackBST() {
        root = null;
    }

    /* inserts the specified key-value pair into the symbol table, overwriting the old value with the new value if the symbol table already contains the specified key */
    public void put(int key, T element) {
        root = put(root, key, element);
        root.isRed = false;
    }

    /* recursive put function */
    private Node put(Node node, int key, T element) {
        /* base case: returns new node */
        if (node == null)
            return new Node(key, element, true);
        /* if current node is greater than, go to left child node */
        if (key < node.key)
            node.left = put(node.left, key, element);
            /* if current node is less than, go to right child node */
        else if (key > node.key)
            node.right = put(node.right, key, element);
            /* replace element if value already exists */
        else
            node.element = element;

        /* returns current node after three special cases have been checked */
        return checkCases(node);
    }

    /* three special cases to form red-black left leaning bs tree */
    private Node checkCases(Node node) {
        /* case where right is red and left is black */
        /* rotates left */
        if (red(node.right) && !red(node.left))
            node = rotateLeft(node);

        /* case where both left children are red */
        /* rotates right */
        if (red(node.left) && red(node.left.left))
            node = rotateRight(node);

        /* flips color if children are both red */
        if (red(node.left) && red(node.right))
            flipColors(node);

        return node;
    }

    /* gets value at given key */
    public T get(int key) {
        return get(root, key);
    }

    /* recursive get function */
    private T get(Node node, int key) {
        if (node == null)
            return null;
        if (key < node.key)
            return get(node.left, key);
        else if (key > node.key)
            return get(node.right, key);
        else
            return node.element;
    }

    /* removes the specified key and its associated value from this symbol table (if the key is in this symbol table) */
    public void delete(int key) {
        if (contains(key)) {
            if (!red(root.left) && !red(root.right))
                root.isRed = true;

            root = delete(root, key);
            if (root != null)
                root.isRed = false;
        }
    }

    /* recursive delete function */
    private Node delete(Node node, int key) {
        /* if key is left of current node */
        if (key < node.key)  {
            /* both nodes to the left are black */
            if (!red(node.left) && !red(node.left.left)) {
                /* flip current node's color */
                flipColors(node);
                /* current node's right's left is red */
                if (red(node.right.left)) {
                    /* rotate right right node*/
                    node.right = rotateRight(node.right);
                    /* rotate left current node */
                    node = rotateLeft(node);
                    /* swap colors */
                    flipColors(node);
                }
            }
            /* delete left node  */
            node.left = delete(node.left, key);
        }
        /* if key is right of current node */
        else {
            /* if node to left is red */
            if (red(node.left))
                node = rotateRight(node);
            /* if right doesn't exist and key is current */
            if (node.right == null && key == node.key)
                return null;
            /* if right is black and right's left is also black */
            if (!red(node.right) && !red(node.right.left)) {
                /* swap colors */
                flipColors(node);
                /* if left's left is red */
                if (red(node.left.left)) {
                    /* rotate right */
                    node = rotateRight(node);
                    /* swap colors */
                    flipColors(node);
                }
            }
            /* if  current node is the key */
            if (node.key == key) {
                /* get the minimum and swap */
                Node temp = min(node.right);
                node.key = temp.key;
                node.element = temp.element;
                node.right = deleteMin(node.right);
            }
            /*  recursively delete key */
            else {
                node.right = delete(node.right, key);
            }
        }
        /* balance */
        return checkCases(node);
    }

    /* utility function for checking if node exists and is red */
    private boolean red(Node n) {
        return n != null && n.isRed == true;
    }

    /* flips colors */
    private void flipColors(Node n) {
        n.isRed = !n.isRed;
        n.right.isRed = !n.right.isRed;
        n.left.isRed = !n.left.isRed;
    }

    /* shifts to right of node */
    private Node rotateRight(Node node) {
        /* moving pointers */
        Node left = node.left;
        node.left = left.right;
        left.right = node;

        left.isRed = node.isRed;
        left.right.isRed = true;
        return left;
    }

    /* shifts to left of node */
    private Node rotateLeft(Node node) {
        /* moving pointers */
        Node right = node.right;
        node.right = right.left;
        right.left = node;

        right.isRed = node.isRed;
        right.left.isRed = true;
        return right;
    }

    /* deletes minimum */
    private Node deleteMin(Node node) {
        if (node.left == null)
            return null;

        if (!red(node.left) && !red(node.left.left)) {
            flipColors(node);
            if (red(node.right.left)) {
                node.right = rotateRight(node.right);
                node = rotateLeft(node);
                flipColors(node);
            }
        }

        node.left = deleteMin(node.left);
        return checkCases(node);
    }

    /* returns the largest key in the symbol table */
    public int max() {
        if (root == null)
            return 0;
        return max(root).key;
    }

    /* helper function */
    private Node max(Node node) {
        if (node.right == null)
            return node;
        else
            return max(node.right);
    }

    /* returns the smallest key in the symbol table */
    public int min() {
        if (root == null)
            return 0;
        return min(root).key;
    }

    /* helper function */
    private Node min(Node node) {
        if (node.left == null)
            return node;
        else
            return min(node.left);
    }

    /* checks whether tree contains given key */
    public boolean contains(int key) {
        return get(key) != null;
    }

    /* returns height of tree*/
    public int height() {
        return height(root) + 1;
    }

    /* helper function */
    private int height(Node node) {
        if (node == null)
            return -1;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    /* returns the number of key-value pairs in this symbol table */
    public int size() {
        return size(root);
    }

    /* helper function */
    private int size(Node node) {
        if (node == null)
            return 0;
        return size(node.left) + size(node.right) + 1;
    }

    /* returns whether tree is empty */
    public boolean isEmpty() {
        return root == null;
    }

    /* in order traversal wrapper function */
    public void levelOrder() {
        int height = height();
        for (int i = 1; i <= height; i++)
            levelOrder(root, i);
        StdOut.println();
    }

    /* level order traversal helper function */
    private void levelOrder(Node root, int level) {
        if (root == null)
            return;
        if (level == 1)
            StdOut.print(root.element + " ");
        else if (level > 1) {
            levelOrder(root.left, level - 1);
            levelOrder(root.right, level - 1);
        }
    }

    /* in order traversal wrapper function */
    public void inOrder() {
        inOrder(root);
        StdOut.println();
    }

    /* in order traversal helper function */
    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            StdOut.print(node.element + " ");
            inOrder(node.right);
        }
    }

    /* post order traversal wrapper function */
    public void postOrder() {
        postOrder(root);
        StdOut.println();
    }

    /* post order traversal helper function */
    private void postOrder(Node node) {
        if (node == null)
            return;
        /* recursion of left node */
        postOrder(node.left);
        /* recursion of right node */
        postOrder(node.right);
        /* of current node */
        StdOut.print(node.element + " ");
    }

    /* pre order traversal wrapper function */
    public void preOrder() {
        preOrder(root);
        StdOut.println();
    }

    /* pre order traversal helper function */
    private void preOrder(Node node) {
        if (node == null)
            return;
        /* of current node */
        StdOut.print(node.element + " ");
        /* recursion of left node */
        preOrder(node.left);
        /* recursion of right node */
        preOrder(node.right);
    }

    /* recursive visualizeTree */
    public String visualizeTree() {
        return visualizeTree("", true, "", root);
    }

    private String visualizeTree(String prev, boolean tail, String s, Node n) {
        if (n == null)
            return "";

        if (n.right != null) {
            String temp = "";
            temp += prev;
            if (tail)
                temp += "│   ";
            else
                temp += "    ";
            s = visualizeTree(temp, false, s, n.right);
        }

        s += prev;
        if (tail)
            s += "└─ ";
        else
            s += "┌─ ";
        s += n.element.toString() + "\n";

        if (n.left != null) {
            String temp = "";
            temp += prev;
            if (tail)
                temp += "    ";
            else
                temp += "│   ";
            s = visualizeTree(temp, true, s, n.left);
        }
        return s;
    }

    /* node class */
    public class Node {
        /* elements */
        int key;
        T element;
        boolean isRed;
        Node left, right;

        /* constructor */
        public Node(int key, T element, boolean isRed) {
            this.key = key;
            this.element = element;
            this.isRed = isRed;
            this.left = this.right = null;
        }

    }
}
