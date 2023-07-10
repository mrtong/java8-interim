package com.foo.datastructure;

import java.util.ArrayList;
import java.util.List;
//in-order reverse is left root right
public class InOrderReverseSample {

    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static class BinaryTree {
        Node root;

        public BinaryTree() {
            this.root = null;
        }

        private List<Integer> inOrderReverse(Node node) {
            List<Integer> list = new ArrayList<>();
            if(node == null) {
                return list;
            }
            list.addAll(inOrderReverse(node.left));
            list.add(node.data);
            list.addAll(inOrderReverse(node.right));
            return list;
        }

        public void inOrderReverse() {
            inOrderReverse(this.root);
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        // Create a sample binary tree
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);

        // Print the binary tree in reverse in-order traversal order
        System.out.println("Binary Tree in Reverse In-order:");
        tree.inOrderReverse();
    }
}
