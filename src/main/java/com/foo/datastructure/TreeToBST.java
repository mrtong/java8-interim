package com.foo.datastructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreeToBST {
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
        public Node sortedArrayToBST(int arr[], int start, int end) {
            if (start > end) {
                return null;
            }
            int mid = (start + end) / 2;
            Node node = new Node(arr[mid]);
            node.left = sortedArrayToBST(arr, start, mid - 1);
            node.right = sortedArrayToBST(arr, mid + 1, end);

            return node;
        }

        public void preOrder(Node node) {
            if (node == null) {
                return;
            }
            System.out.println(node.data);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(31);
        list.add(4);
        list.add(51);
        list.add(6);
        list.add(7);

        Collections.sort(list);
        System.out.println(list);
        int arr[] = list.stream().mapToInt(Integer::intValue).toArray();
//        int arr[] = new int[]{1, 2, 3, 4, 5, 6, 7};
        BinaryTree tree = new BinaryTree();
        int n = arr.length;
        Node node = tree.sortedArrayToBST(arr, 0, n - 1);
        tree.preOrder(node);
    }

}
