package com.foo.datastructure;
//pre-order reverse is root left right
public class PreOrderTraverseSample {
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
        private void preOrderReverse(Node node){
            if(node == null) {
                return;
            }
            System.out.println(node.data);
            preOrderReverse(node.left);
            preOrderReverse(node.right);

        }
        public void preOrderReverse() {
            preOrderReverse(this.root);
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
        System.out.println("Binary Tree in preOrderReverse:");
        tree.preOrderReverse();
    }

}
