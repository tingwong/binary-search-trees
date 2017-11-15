package com.ting;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BSTIterative {

    private Node root;

    public BSTIterative(Node root) {
        this.root = root;
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

        BSTIterative binarySearchTree = new BSTIterative(firstNode);

        System.out.println("Testing ValueToSearch");
        System.out.println("Expecting: True");
        System.out.println(binarySearchTree.valueToSearch(10));
        System.out.println("Expecting: False");
        System.out.println(binarySearchTree.valueToSearch(-1));
        System.out.println("Expecting: True");
        System.out.println(binarySearchTree.valueToSearch(0));
        System.out.println("===============");
        System.out.println("Testing Insert Value");
        binarySearchTree.insertValue(8);
        binarySearchTree.insertValue(3);
        System.out.println(seventhNode.getLeft().getData());
        System.out.println(fourthNode.getLeft().getData());
        System.out.println("===============");
        System.out.println("Testing PreOrder Traversal");
        binarySearchTree.preOrderTraversal();
        System.out.println();
        System.out.println("===============");
        System.out.println("Testing InOrder Traversal");
        binarySearchTree.inOrderTraversal();
        System.out.println();
        System.out.println("===============");
        System.out.println("Testing postOrder Traversal");
        binarySearchTree.postOrderTraversal();
        System.out.println();
        System.out.println("===============");
        System.out.println("Testing breadthFirst Traversal");
        binarySearchTree.breadthFirstTraversal();
        System.out.println();
        System.out.println("===============");
        System.out.println("Testing height; expecting: 4");
        System.out.println(binarySearchTree.calcHeight());


    }

    public boolean valueToSearch(int value) {

        Node current = root;

        if (current == null) {
            return false;
        }

        while (current != null) {
            if (value == current.getData()) {
                return true;
            } else if (value < current.getData()) {
                current = current.getLeft();
            } else if (value > current.getData()) {
                current = current.getRight();
            }
        }

        return false;

    }

    public void insertValue(int value) {

        Node newNode = new Node(value);
        Node current = root;
        Node parent = root;

        if (root == null) {
            root = newNode;
            return;
        }

        while (current != null) {
            parent = current;
            if (newNode.getData() <= current.getData()) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }

        if (newNode.getData() <= parent.getData()) {
            parent.setLeft(newNode);
        } else {
            parent.setRight(newNode);
        }
    }

    public void preOrderTraversal() {

        if (root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        Node currentNode = root;
        stack.push(currentNode);

        while (stack.empty() == false) {
            currentNode = stack.pop();
            System.out.print(currentNode.getData() + " ");
            if (currentNode.getRight() != null) {
                stack.push(currentNode.getRight());
            }
            if (currentNode.getLeft() != null) {
                stack.push(currentNode.getLeft());
            }
        }
    }

    public void inOrderTraversal() {

        if (root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        Node currentNode = root;

        while (stack.empty() == false || currentNode != null) {
            if (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.getLeft();
            } else if (stack.empty() == false) {
                currentNode = stack.pop();
                System.out.print(currentNode.getData() + " ");
                currentNode = currentNode.getRight();
            }

        }
    }

    public void postOrderTraversal() {

        Stack<Node> stack1 = new Stack();
        Stack<Node> stack2 = new Stack();

        if (root == null) {
            return;
        }

        stack1.push(root);

        while (!stack1.empty()) {
            Node currentNode = stack1.pop();
            stack2.push(currentNode);

            if (currentNode.getLeft() != null) {
                stack1.push(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                stack1.push(currentNode.getRight());
            }
        }

        while (!stack2.empty()) {
            System.out.print(stack2.pop().getData() + " ");
        }

    }

    public void breadthFirstTraversal() {
        Deque<Node> queue = new LinkedList<>();

        if (root == null) {
            return;
        }

        queue.addLast(root);

        while (!queue.isEmpty()) {
            Node currentNode = queue.removeFirst();
            System.out.print(currentNode.getData() + " ");
            if (currentNode.getLeft() != null) {
                queue.addLast(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.addLast(currentNode.getRight());
            }
        }
    }

    public int calcHeight() {

        Deque<Node> queue = new LinkedList<>();

        if (root == null) {
            return 0;
        }

        queue.addLast(root);
        int height = 0;
        int nodeCount = 1;
        int newNodeCount = 0;

        while (nodeCount > 0) {

            height++;

            while (nodeCount > 0) {

                Node currentNode = queue.removeFirst();

                if (currentNode.getLeft() != null) {
                    queue.addLast(currentNode.getLeft());
                    newNodeCount++;
                }

                if (currentNode.getRight() != null) {
                    queue.addLast(currentNode.getRight());
                    newNodeCount++;
                }

                nodeCount--;
            }

            nodeCount = newNodeCount;
            newNodeCount = 0;

        }

        return height;
    }


}
