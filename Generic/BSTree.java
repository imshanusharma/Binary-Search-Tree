/* Generic Binary Search Tree with CRUD Operations */

package BinarySearchTree;

public class BSTree<K extends Comparable<K>, V> {
    private Node root;

    // InOrder traversal
    public void inOrder() {
        inOrder(root);
    }
    public void inOrder(Node node) {
        if(node == null)
            return;
        inOrder(node.left);
        System.out.print(node.key + " ");
        inOrder(node.right);
    }
    // preOrder traversal
    public void preOrder() {
        preOrder(root);
    }
    public void preOrder(Node node) {
        if(node == null)
            return;
        System.out.print(node.key + " ");
        preOrder(node.left);
        preOrder(node.right);
    }
    // postOrder traversal
    public void postOrder() {
        postOrder(root);
    }
    public void postOrder(Node node) {
        if(node == null)
            return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.key + " ");
    }
    // Height of Tree
    public int height() {
        return height(root);
    }
    private int height(Node node) {
        if(node == null)
            return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }
    // Deletion
    public void delete(K key) {
        root = delete(key, root);
    }
    private Node delete(K key, Node node) {
        if(node == null)
            return null;
        int cmp = key.compareTo(node.key);
        if(cmp > 0) {
            return delete(key, node.right);
        }
        else if(cmp < 0) {
            return delete(key, node.left);
        }
        if(node.right == null) {
            return node.left;
        }
        else if(node.left == null) {
            return node.right;
        }
        Node x = min(node.right);
        x.right = removeMin(node.right);
        x.left = node.left;
        return x;
    }
    // Max element
    public K max() {
        return max(root).key;
    }
    private Node max(Node node) {
        if(node.right == null)
            return node;
        return max(node.right);
    }
    // Remove Max element
    public void removeMax() {
        root = removeMax(root);
    }
    private Node removeMax(Node node) {
        if(node.right == null)
            return node.left;
        node.right = removeMax(node.right);
        node.size = 1 + sizeOf(node.right) + sizeOf(node.left);
        return node;
    }
    // Min element
    public K min() {
        return min(root).key;
    }
    private Node min(Node node) {
        if(node.left == null)
            return node;
        return min(node.left);
    }
    // Remove Min element
    public void removeMin() {
        root = removeMin(root);
    }
    private Node removeMin(Node node) {
        if(node.left == null)
            return node.right;
        node.left = removeMin(node.left);
        node.size = 1 + sizeOf(node.left) + sizeOf(node.right);
        return node;
    }
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
