package com.ting;

public class Node {

    private int data;
    private Node left;
    private Node right;

    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getData() {

        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }
}
