public class BST<E extends Comparable<E>> {
    private Node<E> root;

    public BST(){
        root = null;
    }

    public Node<E> getRoot(){
        return root;
    }

    public void insert(E data){
        // Find the right spot in the tree for the new node
        // Make sure to check if anything is in the tree
        // Hint: if a node n is null, calling n.getData() will cause an error
        if (root == null){
            root = new Node<E>(data);
        } else {
            Node<E> node = root;
            Node<E> parent = node;
            while (node != null) {
                parent = node;
                if (data.compareTo(node.getData()) < 0){
                    node = node.getLeftChild();
                } else {
                    node = node.getRightChild();
                }
            }
            node = new Node<E>(data);
            node.setParent(parent);
            if (data.compareTo(parent.getData()) < 0){
                parent.setLeftChild(node);
            } else {
                parent.setRightChild(node);
            }
        }
    }

    public Node<E> find(E data){
        // Search the tree for a node whose data field is equal to data
        // Returns the node or null
        Node<E> node = root;
        while (node != null){
            if (data == node.getData()){
                return node;
            }
            else if (data.compareTo(node.getData()) < 0){
                node = node.getLeftChild();
            } else {
                node = node.getRightChild();
            }
        }
        return null;
    }

    private Node<E> findMin(Node<E> nodeToRemove){
        // Returns the node that is least greater than nodeToRemove
        Node<E> successor = nodeToRemove.getRightChild();
        while (successor.getLeftChild() != null){
            successor = successor.getLeftChild();
        }
        return successor;
    }

    private void parentSetCorrectChildTo(Node<E> node, Node<E> child){
        // Sets the correct child of node.parent to child  
        Node<E> parent = node.getParent();
        if (parent.getRightChild() == node){
            parent.setRightChild(child);
        } else {
            parent.setLeftChild(child);
        }
    }

    public void delete(E data){
        // Find the right node to be deleted
        // If said node has no children, simply remove it by setting its parent to point to null instead of it.
        // If said node has one child, delete it by making its parent point to its child.
        // If said node has two children, then replace it with its successor,
        //          and remove the successor from its previous location in the tree.
        // The successor of a node is the left-most node in the node's right subtree.
        // If the value specified by delete does not exist in the tree, then the structure is left unchanged.
        // Hint: You may want to implement a findMin() method to find the successor when there are two children

        Node<E> nodeToRemove = find(data); 
        if (nodeToRemove != null){

            if (nodeToRemove.getLeftChild() != null && nodeToRemove.getRightChild() != null){           //both children exist
                Node<E> successor = findMin(nodeToRemove);

                Node<E> successor_parent = successor.getParent();
                if (successor.getRightChild() != null){ 
                    successor_parent.setLeftChild(successor.getRightChild());
                } else {
                    successor_parent.setLeftChild(null);
                }
                nodeToRemove.setData(successor.getData());
                
            } else if (nodeToRemove.getLeftChild() == null && nodeToRemove.getRightChild() == null){    //no children exist
                parentSetCorrectChildTo(nodeToRemove, null);

            } else {                                                                                    //only one child exists but we don't know which one
                if (nodeToRemove.getRightChild() != null){
                    parentSetCorrectChildTo(nodeToRemove, nodeToRemove.getRightChild());
                } else {
                    parentSetCorrectChildTo(nodeToRemove, nodeToRemove.getLeftChild());
                }
            }
        }
    }

    private void preorder(Node<E> node){
        if (node != null){
            System.out.print(node.getData() + " ");
            preorder(node.getLeftChild());
            preorder(node.getRightChild());
        }
    }

    private void inorder(Node<E> node){
        if (node != null){
            inorder(node.getLeftChild());
            System.out.print(node.getData() + " ");
            inorder(node.getRightChild());
        }
    }

    private void postorder(Node<E> node){
        if (node != null){
            postorder(node.getLeftChild());
            postorder(node.getRightChild());
            System.out.print(node.getData() + " ");
        }
    }

    public void traverse(String order, Node<E> top) {
        if (top != null){
            switch (order) {
                case "preorder":
                    preorder(top);
                    System.out.print("\n");
                    break;

                case "inorder":
                    inorder(top);
                    System.out.print("\n");
                    break;

                case "postorder":
                    postorder(top);
                    System.out.print("\n");
                    break;
            }
        }
    }
}
