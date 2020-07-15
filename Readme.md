## Red Black Binary Search Tree

The assignment was to create a left-leaning red black binary search tree. Project files include: `BinarySearchTree.java`, `RedBlackBST.java`, `RBTestClient.java`, `AverageHeightClient.java`, and `BSTvsRBBST.java`.

**Quick overview of problem-solving:**
To break this problem into smaller subsets, we both looked at the special cases for tree operations:

Case 1 - the one node tree<br>
Case 2 - a two node at the bottom of a tree<br>
Case 3 - a three node at the bottom of the tree

We solved this problem together by mapping out the solution steps (using the rotation utility functions). We determined that to perform all operations while maintaining balance, we needed to more functions in addition to the standard binary search tree ones:

```
Node checkCases(Node n) 	// checks three aforementioned special cases to form red-black left leaning tree
boolean red(Node n)			// if the given node is red
Node rotateLeft(Node n)		// rotates left at current node
Node rotateRight(Node n)	// roatates right at current node

```

## BinarySearchTree.java and RedBlackBST.java

These are the data structure files that are relevant for this project. `BinarySearchTree.java` was turned in in the previous assignment and is used in the testing clients mentioned below. `RedBlackBST.java` was the main focus of this project.

We followed the standard API for both data structures:

```
boolean contains(Key key)			// Does this symbol table contain the given key?
void delete(Key key)				// Removes the specified key and its associated value from this symbol table (if the key is in this symbol table).
int height()						// Returns the height of the BST (for debugging)
boolean isEmpty()					// Returns true if this symbol table is empty.
Key max()							// Returns the largest key in the symbol table.
Key min()							// Returns the smallest key in the symbol table.
void levelorder()					// traverse (prints out) the keys as levels, left to right.
void inorder()						// traverse (prints out) the keys in inorder order.
void postorder()					// traverses (prints out) the keys in postorder order.
void preorder()						// traverses (prints out) the keys in preorder order
void put(Key key, Value val)		// Inserts the specified key-value pair into the symbol table, overwriting the old value with the new value if the symbol table already contains the specified key.
int size()							// Returns the number of key-value pairs in this symbol table.

```

## RBTestClient.java

This is the visualization test client for `RedBlackBST.java`. It will highlight all the colors in the search tree (red or black) and also has a variety of operations for testing and visualization. Click run to begin.

Options:

```
Options:
[0]: Print
[1]: Randomly put N integers
[2]: Put integer
[3]: Delete a key
[4]: Contains a key
[5]: Min
[6]: Max
[7]: Height
[8]: Size
[9]: Is Empty
[Other]: Exit
```

## AverageHeightClient.java

This class finds and compares the average height for `RedBlackBST.java` and `BinarySearchTree.java`. It does so by inserting a shuffled and specified number of integers inside the binary search tree and measuring the resulting height. We can observe that the Red Black BST is clearly much more efficient in terms of searching because the height is much lowerer (and will thus require less recursive calls to find an item in the worst case).

Please edit the number of cycles and size (instance variables) accordingly to compare differences.

## BSTvsRBBST.java

This class compares the timing of the worst and average case for operations for both `RedBlackBST.java` and `BinarySearchTree.java`. It computes worst case by creating a degenerate tree and timing the time it takes to reach to the bottom of the tree. The client computes the average case by calling the `get()` function for all the items in the tree and reports the average time. We find that the time for the Red Black BST is much lower than that of the standard BST's.

Please edit the number of cycles and size (instance variables) accordingly to compare differences.
