/** A map backed by a binary search tree. */
public class BstMap<K extends Comparable<K>, V> {

    /** The root node of the binary search tree. */
    private Node<K, V> root = null;

    /** Inserts the given (key, value) pair into the map. */
    public void put(K key, V value) {
        if (this.root == null) {
            this.root = new Node<>(key, value);
        } else {
            this.root.insert(key, value);
        }
    }

    /**
     * Returns the value associated with the given key, or null if the key is
     * not in the map.
     */
    public V get(K key) {
        if (this.root == null) {
            return null;
        } else {
            Node<K, V> found = this.root.find(key);
            return found == null ? null : found.value;
        }
    }

    /** Returns true if the map contains the given key and false otherwise. */
    public boolean containsKey(K key) {
        if (this.root == null) {
            return false;
        } else {
            return this.root.find(key) != null;
        }
    }

    /** Removes the given key from the map. */
    public void remove(K key) {
        if (this.root != null) {
            this.root = this.root.remove(key);
        }
    }

    @Override public String toString() {
        if (this.root == null) {
            return "<empty>";
        } else {
            return this.root.printKeys(10);
        }
    }

    /**
     * A binary search tree node. The binary search invariant is maintained over
     * the keys, which must implement the {@link Comparable} interface.
     */
    static final class Node<K extends Comparable<K>, V> {
        private K key;
        private V value;
        private Node<K, V> left;
        private Node<K, V> right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /** Returns the node with the given key, or null if no such node can be found. */
        public Node<K, V> find(K key) {
            throw new UnsupportedOperationException("not implemented");
        }

        /** Inserts the given (key, value) pair into the binary search tree. */
        public void insert(K key, V value) {
            throw new UnsupportedOperationException("not implemented");
        }

        /**
         * Removes the given key from the binary search tree. Returns the
         * tree without the removed node. Has no effect if the key is not
         * present in the tree.
         */
        public Node<K, V> remove(K key) {
            if (key.compareTo(this.key) < 0) {
                // The key we want to remove is somewhere to the left.
                this.left = this.left == null ? null : this.left.remove(key);
                return this;
            } else if (key.compareTo(this.key) > 0) {
                // The key we want to remove is somewhere to the right.
                this.right = this.right == null ? null : this.right.remove(key);
                return this;
            }

            // We found the key we want to remove.

            // Case 1: No children.
            if (this.left == null && this.right == null) {
                throw new UnsupportedOperationException("not implemented");
            }

            // Case 2: One child.
            else if (this.left == null) {
                throw new UnsupportedOperationException("not implemented");
            } else if (this.right == null) {
                throw new UnsupportedOperationException("not implemented");
            }

            // Case 3: Two children.
            else {
                throw new UnsupportedOperationException("not implemented");
            }
        }

        /**
         * Returns a string representation of the tree structure up to some
         * maximum depth. Entries are rendered according to the {@code toString}
         * method on their keys. Null trees are rendered as underscores.
         */
        public String printKeys(int maxDepth) {
            if (maxDepth == 0) return "";
            if (this.left == null && this.right == null) {
                return key.toString();
            }
            String leftStr = this.left == null ? "_" : this.left.printKeys(maxDepth - 1);
            String rightStr = this.right == null ? "_" : this.right.printKeys(maxDepth - 1);
            return "(" + key.toString() + " " + leftStr + " " + rightStr + ")";
        }
    }
}
