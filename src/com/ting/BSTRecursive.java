package com.ting;

import java.util.Stack;

public class BSTRecursive {

    private Node root;

    public BSTRecursive(Node root) {
        this.root = root;
    }

    public BSTRecursive() {
        this.root = null;
    }

    public static void main(String[] args) {

        Node firstNode = new Node(4);
        Node secondNode = new Node(2);
        Node thirdNode = new Node(0);
        Node fourthNode = new Node(3);
        Node fifthNode = new Node(7);
        Node sixthNode = new Node(6);
        Node seventhNode = new Node(10);
        firstNode.setLeft(secondNode);
        secondNode.setLeft(thirdNode);
        secondNode.setRight(fourthNode);
        firstNode.setRight(fifthNode);
        fifthNode.setLeft(sixthNode);
        fifthNode.setRight(seventhNode);

        BSTRecursive binarySearchTree = new BSTRecursive(firstNode);

        System.out.println("Expecting: False");
        System.out.println(binarySearchTree.valueToSearch(-1, firstNode));

        System.out.println("Expecting: True");
        System.out.println(binarySearchTree.valueToSearch(10, firstNode));

        System.out.println("Expecting: True");
        System.out.println(binarySearchTree.valueToSearch(4, firstNode));

        binarySearchTree.insertValue(-1, firstNode);
        System.out.print("Inserted -1: ");
        System.out.println(binarySearchTree.valueToSearch(-1, firstNode));
        System.out.println(thirdNode.getLeft().getData());

        binarySearchTree.insertValue(4, firstNode);
        System.out.print("Inserted 4: ");
        System.out.println(binarySearchTree.valueToSearch(4, firstNode));
        System.out.println(fourthNode.getRight().getData());

        binarySearchTree.insertValue(20, firstNode);
        System.out.print("Inserted 20: ");
        System.out.println(binarySearchTree.valueToSearch(4, firstNode));
        System.out.println(seventhNode.getRight().getData());

        System.out.println(binarySearchTree.heightOfBST(firstNode));

        System.out.println("================");
        System.out.println("PreOrder Traversal // Expecting: 4 2 0 -1 3 4 7 6 10 20");
        binarySearchTree.preOrderTraversal(firstNode);
        System.out.println();
        System.out.println("================");
        System.out.println("InOrder Traversal // Expecting: -1 0 2 3 4 4 6 7 10 20");
        binarySearchTree.inOrderTraversal(firstNode);
        System.out.println();
        System.out.println("================");
        System.out.println("PostOrder Traversal // Expecting: -1 0 3 2 6 20 10 7 4 4");
        binarySearchTree.postOrderTraversal(firstNode);
        System.out.println();

        System.out.println("================");
        System.out.println("Breadth First: 4 2 7 0 3 6 10 -1 4 20");
        binarySearchTree.breadthFirstPrint(firstNode);
        System.out.println();
        System.out.println("================");
        binarySearchTree.inOrderTraversal(firstNode);
        System.out.println();
        binarySearchTree.delete(7);
        binarySearchTree.inOrderTraversal(firstNode);

    }

    public boolean valueToSearch(int data, Node root) {

        // If there is only the root node
        if (root == null) {
            return false;
        }

        // If the base case is found
        if (data == root.getData()) {
            return true;
        }

        // Recursive calls to get to the base case
        if (data < root.getData()) {
            return valueToSearch(data, root.getLeft());
        } else {
            return valueToSearch(data, root.getRight());
        }
    }

    public void insertValue(int value, Node current) {

        try {
            Node newNode = new Node(value);

            // TW:  Not sure if this is the edge case of no node
            if (current == null) {
                current = newNode;
                return;
            }

            // Looking at the left sub-tree
            if (value <= current.getData()) {
                // Setting base case
                if (current.getLeft() == null) {
                    current.setLeft(newNode);
                } else {
                    insertValue(value, current.getLeft());
                    return;
                }
            } else {
                // Looking at the right sub-tree
                if (current.getRight() == null) {
                    // Setting base case
                    current.setRight(newNode);
                } else {
                    insertValue(value, current.getRight());
                    return;
                }
            }
        } catch (NullPointerException nullPointerException) {
            nullPointerException.printStackTrace();
        }
    }

    public int heightOfBST(Node root) {

        // Null case
        if (root == null) {
            return 0;
        }

        int leftHeight = heightOfBST(root.getLeft());
        int rightHeight = heightOfBST(root.getRight());

        if (leftHeight < rightHeight) {
            return 1 + rightHeight;
        }

        return 1 + leftHeight;
    }

    public void preOrderTraversal(Node current) {

        if (current == null) {
            return;
        }

        System.out.print(current.getData() + " ");

        preOrderTraversal(current.getLeft());
        preOrderTraversal(current.getRight());
    }

    public void inOrderTraversal(Node current) {

        if (current == null) {
            return;
        }

        inOrderTraversal(current.getLeft());
        System.out.print(current.getData() + " ");
        inOrderTraversal(current.getRight());
    }

    public void postOrderTraversal(Node current) {
        if (current == null) {
            return;
        }

        postOrderTraversal(current.getLeft());
        postOrderTraversal(current.getRight());

        System.out.print(current.getData() + " ");
    }

    public void printGivenLevel(Node current, int level) {
        if (current == null) {
            return;
        }

        if (level == 1) {
            System.out.print(current.getData() + " ");
        } else if (level > 1) {
            printGivenLevel(current.getLeft(), level - 1);
            printGivenLevel(current.getRight(), level - 1);
        }
    }

    public void breadthFirstPrint(Node current) {

        if (current == null) {
            return;
        }

        int height = heightOfBST(current);

        for (int i = 0; i < height; i++) {
            printGivenLevel(current, i + 1);
        }
    }

    public void delete(int value) {
        if (root == null) {
            return;
        }
        delete(value, root, null);
    }

    private Node min(Node curNode) {
        if (curNode.getLeft() == null) {
            return curNode;
        } else {
            return min(curNode);
        }
    }

    private Node max(Node curNode) {
        if (curNode.getRight() == null) {
            return curNode;
        } else {
            return max(curNode);
        }
    }

    private void delete(int value, Node curNode, Node parentNode) {
        if (value == curNode.getData()) {
            // The node to replace this node with.
            Node newValue;

            if (curNode.getLeft() == null && curNode.getRight() == null) {
                // Leaf node
                newValue = null;
            } else if (curNode.getLeft() != null && curNode.getRight() == null) {
                // Only left.
                newValue = curNode.getLeft();
            } else if (curNode.getRight() != null && curNode.getLeft() == null) {
                // Only right;
                newValue = curNode.getRight();
            } else {
                // Both right and left.  Search for incremental next. Set the value,
                // not the reference, then get out.
                Node nextNode = min(curNode.getRight());
                curNode.setData(nextNode.getData());
                delete(nextNode.getData(), curNode.getRight(), curNode);
                // Return, because in this case we don't want node assignment.
                return;
            }

            if (parentNode == null) {
                // delete the root
                root = newValue;
            } else if (parentNode.getRight() == curNode) {
                parentNode.setRight(newValue);
            } else {
                parentNode.setLeft(newValue);
            }
        } else if (value < curNode.getData()) {
            if (curNode.getLeft() != null) {
                delete(value, curNode.getLeft(), curNode);
            } else {
                // Node isn't in tree.
                return;
            }
        } else if (value > curNode.getData()) {
            if (curNode.getRight() != null) {
                delete(value, curNode.getRight(), curNode);
            } else {
                // Node isn't in tree
                return;
            }
        }
    }

    /**
     * Return the lowest valued Node in a tree.  The Node might be stored directly
     * in the stack or it might be a child of a Node (or a child of a child, etc). All
     * Nodes are part of the same binary search tree. Make sure to remove the Node you
     * return from the stack. The nodes are in sorted order on the stack. Every Node in
     * the tree must be on the stack or a right descendent of a node on the stack.
     * @param nodes a stack of nodes
     * @return the least node
     */
    public static Node leastNode(Stack<Node> nodes) {

        Node topElement = nodes.peek();
        Node currentNode = topElement;

        while (currentNode.getLeft() != null) {

            currentNode = currentNode.getLeft();

        }

        if (currentNode.getRight() != null) {
            nodes.push(currentNode.getRight());
        }

        return currentNode;

    }



}
