import java.lang.Math;

public class AVL<E extends Comparable<E>> {
    private Node<E> root;

    public AVL(){
        root = null;
    }

    public Node<E> getRoot(){
        return root;
    }

    public Node<E> find(E data){
        // Return the node that corresponds with the given data
        // Note: No need to worry about duplicate values added to the tree
        Node<E> node = root;
        while (node != null){
            if (data == node.getData()){
                return node;
            } else if (data.compareTo(node.getData()) < 0){
                node = node.getLeftChild();
            } else {
                node = node.getRightChild();
            }
        }
        return null;
    }

    public void insert(E data){
        // Preform a regular insert
        // Check to make sure the tree remains an AVL tree
        if (root == null){
            root = new Node<E>(data);
        } else {
            Node<E> node = root;
            Node<E> parent = node;
            while (node != null) {
                parent = node; // saves parent for later when the new node doesn't know where its parent is to be set
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
            updateAndRebalence(node);
        }
        
    }

    private void updateAndRebalence(Node<E> node){
        // starts from the inserted node or the node that is getting deleted
        updateHeights(node);
        Node<E> unbalencedNode = getUnbalencedNode(node); // check if tree remains balanced by returning the unbalanced node or null
        if (unbalencedNode != null){
            trinodeReorder(unbalencedNode);
            updateHeights(unbalencedNode);
        }
        
    }

    private Node<E> getUnbalencedNode(Node<E> node){
        Node<E> parent = node.getParent();
        while (parent != null){
            if (parent.isUnbalanced()){
                return parent;
            }
            parent = parent.getParent();
        }
        return null;    
    }

    private void updateHeights(Node<E> node){
        while (node != null){
        	if (node.getRightChild() == null && node.getLeftChild() == null){
        		node.setHeight(1);
        		node = node.getParent(); 
        	} else if (node.getRightChild() != null && node.getLeftChild() != null){
                int rightHeight = node.getRightChild().getHeight();
                int leftHeight = node.getLeftChild().getHeight();
                node.setHeight(Math.max(rightHeight, leftHeight) + 1);
                node = node.getParent();
        	} else {	
        		if (node.getRightChild() != null){
        			int rightHeight = node.getRightChild().getHeight();
        			node.setHeight(rightHeight + 1);
        			node = node.getParent();
        		} else { 
        			int leftHeight = node.getLeftChild().getHeight();
        			node.setHeight(leftHeight + 1);
        			node = node.getParent();
        		}
        	}
        }
    }

    private Node<E> getTallestChild(Node<E> node){
        // TODO account for ties and what child was picked previously
        Node<E> right = node.getRightChild();
        Node<E> left = node.getLeftChild();

        if (right != null && left != null){
            if (left.getHeight() > right.getHeight()){
                return left;
            } else {
                return right;
            }
        } else if (right != null){
            return right;
        } else {
            return left;
        }
    }

    private Node<E> findSuccessor(Node<E> nodeToRemove){
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
        // Perform a regular delete
        // Check to make sure the tree remains an AVL tree
        Node<E> nodeToRemove = find(data); 
        if (nodeToRemove != null){

            if (nodeToRemove.getLeftChild() != null && nodeToRemove.getRightChild() != null){           //both children exist
                Node<E> successor = findSuccessor(nodeToRemove);

                Node<E> successorParent = successor.getParent();
                if (successor.getRightChild() != null){ 
                    successorParent.setLeftChild(successor.getRightChild());
                } else {
                    successorParent.setLeftChild(null);
                }
                nodeToRemove.setData(successor.getData());
                updateAndRebalence(successor);
                
            }else if (nodeToRemove.getLeftChild() == null && nodeToRemove.getRightChild() == null){    //no children exist
                parentSetCorrectChildTo(nodeToRemove, null);
                updateAndRebalence(nodeToRemove);

            }else {                                                                                    //only one child exists but we don't know which one
                if (nodeToRemove.getRightChild() != null){
                    parentSetCorrectChildTo(nodeToRemove, nodeToRemove.getRightChild());
                } else {
                    parentSetCorrectChildTo(nodeToRemove, nodeToRemove.getLeftChild());
                }
                updateAndRebalence(nodeToRemove);
            }
        }
    }

    private void trinodeReorder(Node<E> z){
        Node<E> y = getTallestChild(z);
        Node<E> x = getTallestChild(y);
        if (z.getData().compareTo(y.getData()) < 0  && y.getData().compareTo(x.getData()) < 0){            // single rotation z < y < x
            y.setParent(z.getParent());
            if (y.getParent() != null){
                y.getParent().setRightChild(y);           	
            } else {
            	root = y;
            }
            z.setParent(y);

            z.setRightChild(y.getLeftChild());
            if (z.getRightChild() != null){
            	z.getRightChild().setParent(z); //sets z's new child parent to z (not y)
            }
            	
            y.setLeftChild(z);

        } else if (x.getData().compareTo(y.getData()) < 0 && y.getData().compareTo(z.getData()) < 0) {    // single rotation x < y < z
            y.setParent(z.getParent());
            if (y.getParent() != null){
                y.getParent().setLeftChild(y);           	
            } else {
            	root = y;
            }
            z.setParent(y); 

            z.setLeftChild(y.getRightChild());
            if (z.getLeftChild() != null){
            	z.getLeftChild().setParent(z); //sets z's new child parent to z (not y)
            }
            
            y.setRightChild(z);

        } else if (z.getData().compareTo(x.getData()) < 0 && x.getData().compareTo(y.getData()) < 0) {    // double rotation z < x < y
            x.setParent(z.getParent());
            if (x.getParent() != null){
                x.getParent().setRightChild(y);            	
            } else {
            	root = x;
            }
            z.setParent(x);

            y.setLeftChild(x.getRightChild());
            if (y.getLeftChild() != null){
            	y.getLeftChild().setParent(y); //sets y's new child parent to y (not x)
            }
            	
            z.setRightChild(x.getLeftChild());
            if (z.getRightChild() != null){
            	z.getRightChild().setParent(z); //sets z's new child parent to z (not x)
            }
            
            x.setLeftChild(z);
            x.setRightChild(y);

        } else {                                                                                          // double rotation y < x < z
            x.setParent(z.getParent());
            if (x.getParent() != null){
                x.getParent().setLeftChild(y);            	
            } else {
            	root = x;
            }

            z.setParent(x);

            y.setRightChild(x.getLeftChild());
            if (y.getRightChild() != null){
                y.getRightChild().setParent(y); //sets y's new child parent to y (not x)
            }


            z.setLeftChild(x.getRightChild());
            if (z.getLeftChild() != null){
                z.getLeftChild().setParent(z); //sets z's new child parent to z (not x)
            }

            x.setLeftChild(y);
            x.setRightChild(z);
        }
    } 

    private void preorder(Node<E> node){
        if (node != null){
//        	System.out.print(node.getData() + " ");
            System.out.print(node.getData() + "(" + node.getHeight() + ")" + " ");
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
