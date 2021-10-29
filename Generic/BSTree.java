/* Generic Binary Search Tree with CRUD Operations */

package BinarySearchTree;

public class BSTree<K extends Comparable<K>, V> {
    private Node root;
    // Ranking
    public int rank(K key) {
        if(search(key) == null)
            return -1;
        return rank(key, root);
    }
    private int rank(K key, Node node) {
        if(node == null)
            return -1;
        int cmp = key.compareTo(node.key);
        if(cmp < 0) {
            return rank(key, node.left);
        }
        else if(cmp > 0)
        {
            return 1 + sizeOf(node.left) + rank(key, node.right);
        }
        return sizeOf(node.left);
    }
    // Searching
    public V search(K key) {
        Node res = search(key, root);
        if(res == null) {
            return null;
        }
        return res.data;
    }
    private Node search(K key, Node node) {
        if(node == null)
        {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if(cmp < 0)
            return search(key, node.left);
        else if(cmp > 0)
            return search(key, node.right);
        return node;
    }
    // Inserting
    public void insert(K key, V data) {
        root = insert(key, data, root);
    }
    private Node insert(K key, V data, Node node) {
        if(node == null)
        {
            return new Node(key,data);
        }
        int cmp = key.compareTo(node.key);
        if(cmp == 0)
            node.data = data;
        else if(cmp < 0) {
            node.left = insert(key, data, node.left);
        }
        else {
            node.right = insert(key, data, node.right);
        }
        node.size = 1 + sizeOf(node.left) + sizeOf(node.right);
        return node;
    }
    // Size Calculation
    public int size() {
        return sizeOf(root);
    }
    private int sizeOf(Node node) {
        return node == null ? 0 : node.size;
    }
    // Skeleton
    private class Node {
        private K key;
        private V data;
        private Node left;
        private Node right;
        private int size;
        // Constructor
        public Node(K key, V data) {
            this.key = key;
            this.data = data;
            this.size = 1;
            this.left = null;
            this.right = null;
        }
    }
}
