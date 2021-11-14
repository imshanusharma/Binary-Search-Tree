package Red_Black_Tree;

public class RBT<K extends Comparable<K>,V> {

    private Node root;
    final private boolean RED = false;
    final private boolean BLACK = true;

    public void insert(K key, V data) {
        root = insert(key, data, root);
        root.color = BLACK;
    }

    private Node insert(K key, V data, Node node) {
        if(node == null)
            return new Node(key, data);
        int cmp = key.compareTo(node.key);
        if(cmp > 0) {
            node.right = insert(key, data, node.right);
        }
        else if(cmp < 0) {
            node.left = insert(key, data, node.left);
        }
        else if(cmp == 0) {
            node.data = data;
        }

        // VIOLATION RULE NUMBER 1
        if(colorOf(node.left) == BLACK && colorOf(node.right) == RED) {
            node = leftRotate(node);
        }

        //VIOLATION RULE NUMBER 2
        if(colorOf(node.left) == RED && colorOf(node.left.left) == RED) {
            node = rightRotate(node);
        }

        // VIOLATION RULE NUMBER 3
        if(colorOf(node.left) == RED && colorOf(node.right) == RED) {
            node = flipColor(node);
        }

        node.size = 1 + sizeOf(node.left) + sizeOf(node.right);
        return node;
    }

    private Node flipColor(Node node) {
        System.out.println("Violation of Rule number 3 : Flipping the colors");
        node.left.color = node.color;
        node.right.color = node.color;
        node.color = RED;
        return node;
    }

    private Node rightRotate(Node node) {
        System.out.println("Violation of Rule number 2 : Doing Right Rotate");
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = RED;
        x.size = node.size;
        node.size = 1 + sizeOf(node.left) + sizeOf(node.right);
        return x;
    }

    private Node leftRotate(Node node) {
        System.out.println("Violation of Rule number 1 : Doing Left Rotate");
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = RED;
        x.size = node.size;
        node.size = 1 + sizeOf(node.left) + sizeOf(node.right);
        return x;
    }

    private boolean colorOf(Node node) {
        if(node == null)
            return BLACK;
        return node.color;
    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if(node == null)
            return;
        inOrder(node.left);
        System.out.println(node.key);
        inOrder(node.right);
    }

    public int size() {
        return sizeOf(root);
    }

    private int sizeOf(Node node) {
        return node == null ? 0 : node.size;
    }

    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if(node == null)
            return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public class Node {
        private K key;
        private V data;
        private Node left;
        private Node right;
        private boolean color;
        private int size;

        public Node(K key, V data) {
            this.key = key;
            this.data = data;
            this.left = null;
            this.right = null;
            this.color = RED;
            this.size = 1;
        }
    }
}
