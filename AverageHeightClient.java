import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class AverageHeightClient {

    /* different sizes to add */
    private static final int MIN_SIZE = 1000;
    private static final int MAX_SIZE = 1000000;

    /* number of cycles to find average */
    private static final int CYCLES = 10;

    /* runner */
    public static void main(String[] args) {
        StdOut.println("- Comparing the average height for RedBlackBST vs BST");

        StdOut.println("Items:\tBST Height:\tRBBST Height:");
        for (int j = MIN_SIZE; j <= MAX_SIZE; j *= 10) {
            int count = 0;
            int count2 = 0;
            for (int i = 0; i < CYCLES; i++) {
                count += BSTRandomPut(j);
                count2 += RBBSTRandomPut(j);
            }
            StdOut.println(j + "\t" + (double) ((count) / CYCLES) + "\t" + (double) ((count2) / CYCLES));
        }
    }

    /* returns height after randomly putting in size number of integers for BST */
    private static int BSTRandomPut(int size) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        int[] array = new int[size];

        /* shuffle array containing integers from 0 to size -1 */
        for (int i = 0; i < size; i++)
            array[i] = i;
        StdRandom.shuffle(array);

        /* put them into bst */
        for (int i = 0; i < size; i++)
            bst.put(array[i], array[i]);

        /* put into height */
        return bst.height();
    }


    /* returns height after randomly putting in size number of integers for RBBST */
    private static int RBBSTRandomPut(int size) {
        RedBlackBST<Integer> rbbst = new RedBlackBST<>();
        int[] array = new int[size];

        /* shuffle array containing integers from 0 to size -1 */
        for (int i = 0; i < size; i++)
            array[i] = i;
        StdRandom.shuffle(array);

        /* put them into rbbst */
        for (int i = 0; i < size; i++)
            rbbst.put(array[i], array[i]);

        /* put into height */
        return rbbst.height();
    }
}
