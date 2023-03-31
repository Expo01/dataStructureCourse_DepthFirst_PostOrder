package datastructures.binarysearchtree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class BinarySearchTree {

    public Node root;

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        private Node(int value) {
            this.value = value;
        }
    }

    public boolean insert(int value) {
        Node newNode = new Node(value);
        if (root == null) {
            root = newNode;
            return true;
        }
        Node temp = root;
        while (true) {
            if (newNode.value == temp.value) return false;
            if (newNode.value < temp.value) {
                if (temp.left == null) {
                    temp.left = newNode;
                    return true;
                }
                temp = temp.left;
            } else {
                if (temp.right == null) {
                    temp.right = newNode;
                    return true;
                }
                temp = temp.right;
            }
        }
    }

    public boolean contains(int value) {
        if (root == null) return false;
        Node temp = root;
        while (temp != null) {
            if (value < temp.value) {
                temp = temp.left;
            } else if (value > temp.value) {
                temp = temp.right;
            } else {
                return true;
            }
        }
        return false;
    }


    public ArrayList<Integer> DFSPostOrder() { //this is exactly the same code as pre-order depth first, but value
        // of traversed node added at end, not beginning, so the root will be the last one added
        ArrayList<Integer> results = new ArrayList<>();

        class Traverse { // traverse class created to utilize constructor as method in encompassing method. nested method
            Traverse(Node currentNode) {
                if (currentNode.left != null) { //items added to call stack, traversing and recursively calling Traverse
                    // constructor until leaf found which is base case
                    new Traverse(currentNode.left);
                }
                if (currentNode.right != null) {
                    new Traverse(currentNode.right);
                }
                results.add(currentNode.value); // base casee found, neither left nor right, so add value of leaf
                // pop it off call stack to preceding node which then eplores to the right, finds leaf, etc, and works
                // its way back up thee branch to root as only remaining method instance in call stack, explores right
                // half of tree, comes back to root and finally adds root value and call stack emptied
            }
        }

        new Traverse(root);
        return results;
    }

}

