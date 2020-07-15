import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.awt.*;

public class RBTestClient {

    /* range of integer generation for put */
    private static final int RANGE = 1000;

    /* graphics constants */
    private static final int CIRCLE_MAX_LEVEL = 7;
    private static final int CANVAS_SIZE = 800;
    private static final double INITIAL_Y = 0.9;
    private static final double INITIAL_X = 0.5;

    /* rbbst */
    private static RedBlackBST<Integer> rbbst = new RedBlackBST<>();

    /* main */
    public static void main(String[] args) {

        /* instructions */
        StdOut.println("- Testing client for red-black binary search tree");

        /* graphics buffering and canvas size */
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(CANVAS_SIZE, CANVAS_SIZE);

        /* user input */
        int input;
        do {
            drawTree();
            StdDraw.show();

            StdOut.println("\nOptions:");
            StdOut.println("[0]: Print");
            StdOut.println("[1]: Randomly put N integers");
            StdOut.println("[2]: Put integer");
            StdOut.println("[3]: Delete a key");
            StdOut.println("[4]: Contains a key");
            StdOut.println("[5]: Min");
            StdOut.println("[6]: Max");
            StdOut.println("[7]: Height");
            StdOut.println("[8]: Size");
            StdOut.println("[9]: Is Empty");
            StdOut.println("[Other]: Exit");

            StdOut.print("\nEnter option: ");
            input = StdIn.readInt();

            operate(input);
        } while (input >= 0 && input <= 9);

        StdOut.println("\nThanks!");
        System.exit(1);
    }

    /* prints */
    private static void printTree() {
        System.out.print("\nIn-order:\t\t");
        rbbst.inOrder();
        System.out.print("\nLevel-order:\t");
        rbbst.levelOrder();
        System.out.print("\nPost-order:\t\t");
        rbbst.postOrder();
        System.out.print("\nPre-order:\t\t");
        rbbst.preOrder();
        rbbst.visualizeTree();
    }

    /* putting a certain amounts of integers */
    private static void putRandom() {
        StdOut.println("\nIntegers from 1 and " + RANGE + " will be generated.");
        StdOut.println("Note that there may be repeats in put(), and keys will be replaced if they are repeated.");
        StdOut.print("Enter number of integers to generate to put: ");

        /* puts random integers for certain amount of times */
        int cycles = StdIn.readInt();
        for (int i = 0; i < cycles; i++) {
            int random = (int) (Math.random() * RANGE);
            rbbst.put(random, random);
        }
        printTree();
    }

    /* put function */
    private static void put() {
        StdOut.print("\nEnter integers to put, 0 to stop: ");

        /* cycles until zero is inputted */
        int item;
        do {
            item = StdIn.readInt();
            rbbst.put(item, item);
        } while (item != 0);
//        rbbst.put(item, item);
        printTree();
    }

    /* delete function */
    private static void delete() {
        StdOut.print("\nEnter integer to delete: ");
        int delete = StdIn.readInt();
        rbbst.delete(delete);
        printTree();
    }

    /* contains function */
    private static void contains() {
        StdOut.print("\nEnter integer to find if it is within tree: ");
        int find = StdIn.readInt();
        StdOut.println(rbbst.contains(find));
    }

    /* min function */
    private static void min() {
        StdOut.println("\nMin (will return 0 if no nodes): " + rbbst.min());
    }

    /* max function */
    private static void max() {
        StdOut.println("\nMax (will return 0 if no nodes): " + rbbst.max());
    }

    /* height function */
    private static void height() {
        StdOut.println("\nHeight: " + rbbst.height() + " levels");
    }

    /* size function */
    private static void size() {
        StdOut.println("\nSize: " + rbbst.size() + " keys");
    }

    /* isEmpty */
    private static void isEmpty() {
        StdOut.println("\nisEmpty: " + rbbst.isEmpty());
    }

    /* operation function */
    private static void operate(int op) {
        switch (op) {
            case 0:
                printTree();
                break;
            case 1:
                putRandom();
                break;
            case 2:
                put();
                break;
            case 3:
                delete();
                break;
            case 4:
                contains();
                break;
            case 5:
                min();
                break;
            case 6:
                max();
                break;
            case 7:
                height();
                break;
            case 8:
                size();
                break;
            case 9:
                isEmpty();
                break;
            default:
                break;
        }
    }

    /* draws tree on canvas */
    private static void drawTree() {
        /* reset canvas */
        StdDraw.clear();
        /* dynamic font changing */
        Font TEXT_FONT = new Font("Heiti SC", Font.PLAIN, Math.min(50, (int) (CANVAS_SIZE * (INITIAL_Y / (rbbst.height() - 1)) / 10)));
        StdDraw.setFont(TEXT_FONT);
        /* calls recursive tree drawing helper function */
        drawTree(rbbst.root, INITIAL_X, INITIAL_Y + (1 - INITIAL_Y) / 2, INITIAL_X / 2, INITIAL_Y / (rbbst.height() - 1), INITIAL_X, INITIAL_Y + (1 - INITIAL_Y) / 2 + 2 * Math.min(INITIAL_Y / (rbbst.height()) / 8, INITIAL_Y / (rbbst.height() - 1) / 8), 1);
    }

    /* recursive tree drawing helper function */
    private static void drawTree(RedBlackBST.Node n, double x, double y, double xIncrement, double heightIncrement, double prevX, double prevY, int level) {
        /* end of tree */
        if (n == null)
            return;

        /* draws and calls level + 1 */
        else {
            double length = Math.min(INITIAL_Y / 16, INITIAL_Y / (rbbst.height()) / 8);
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.line(x, y + length, prevX, prevY - length);

            /* color of node */
            if(n.isRed)
                StdDraw.setPenColor(Color.RED);

            /* remove circles after certain levels */
            if (level < CIRCLE_MAX_LEVEL)
                StdDraw.circle(x, y, length);

            /* draws value */
            StdDraw.text(x, y, String.valueOf(n.element));

            /* recursive calls for children */
            drawTree(n.left, x - xIncrement, y - heightIncrement, xIncrement / 2, heightIncrement, x, y, level + 1);
            drawTree(n.right, x + xIncrement, y - heightIncrement, xIncrement / 2, heightIncrement, x, y, level + 1);
        }
    }
}
