import java.util.LinkedList;
import java.util.List;

/**An object representing a Binary Search Tree*/

public class BinarySearchTree {

    //Fields
    private Node root;
    private LinkedList<Node> listInOrder;

    //Getters and setters
    public Node getRoot(){return root;}
    public void setRoot(Node root){this.root = root;}
    public LinkedList<Node> getListInOrder(){return listInOrder;}

    /**Method helping kthSmallesthelper
     * Effectively builds a linked list with the items in order.
     * Then the kth smallest node may be found.*/
    public void addToListInOrder(Node node){
        if(listInOrder.getFirst() != null){
            listInOrder.addLast(node);
        }
        else{
            listInOrder.add(node);
        }

    }

    //Constructor
    public BinarySearchTree(){
        root = null;
        listInOrder = new LinkedList<>();
    }


    /**Inserts a node into the BST.
     * @param key the key of the new node
     *
     * O(logn)*/
    public void insert(int key){
        if(getRoot() == null){                          // Initializes the root of the BST
            setRoot(new Node(key));
        }
        else{
            Node temp = parentSearch(getRoot(), key);
            if(key < temp.getKey()){                    // Inserts the node to the right and sets its parent
                temp.setLeft(new Node(key));
                temp.getLeft().setParent(temp);
            }
            else{                                       // Inserts the node to the left and sets its parent
                temp.setRight(new Node(key));
                temp.getRight().setParent(temp);
            }
        }
    }

    /**Helper method for insert().
     * Similar to search but returns the address of the leaf node that would be a parent to the key
     * @param root the root of the subtree.
     * @param key the key that is being looked for
     * @return the parent of the node that will be inserted.
     *
     * O(logn)*/
    private Node parentSearch(Node root, int key){
        if(root == null){                                       // Checks whether the parameter was null
            return null;
        }
        else if(key < root.getKey()){                           // Gate whether or not to go left
            if(root.getLeft() != null){
                return parentSearch(root.getLeft(), key);       // Recursive call if not a leaf node
            }
            else{
                return root;
            }
        }
        else{                                                   // Whether or not to go right
            if(root.getRight() != null){
                return parentSearch(root.getRight(), key);      // Recursive call if not a leaf node
            }
            else{
                return root;
            }
        }
    }

    /**Searches the data structure for a particular key recursively.
     * @param key the key of the node being searched for.
     * @param root the root of the BST.
     * @return the node corresponding to the search key.
     *
     * O(logn)*/
    public static Node search(Node root, int key){
        if(root == null){
            return null;
        }
        else if(key == root.getKey()){
            return root;                            //Found the node
        }
        else if(key < root.getKey()){
            return search(root.getLeft(), key);     //Recursive call on the left subtree
        }
        else {
            return search(root.getRight(), key);    //Recursive call on the right subtree
        }
    }

    /**Deletes a Node from the data structure.
     * @param key the key of the target node to delete.
     * @param root the root of the BST.
     * @return the node that was deleted.
     *
     * O(logn)*/
    public Node delete(Node root, int key){
        Node temp = search(root, key);
        if(temp == null){
            return null;
        }
        if(temp.getLeft() == null && temp.getRight() == null){      // Case 1: node has no children.
            if(temp.getKey() < temp.getParent().getKey()){          // If node was left node, delete its parent's left.
                temp.getParent().setLeft(null);
            }
            else{                                                   // If node was right node, delete its parent's right.
                temp.getParent().setRight(null);
            }
            return temp;
        }
        else if(temp.getRight() != null && temp.getLeft() != null){
            /* Algorithm
            *  1. find the successor to the target node
            *  2. make a copy of the successor
            *  3. Set the copy's parent, right and left identically to the target
            *  4. Run delete on the successor.
            *  5. Connect parent to the copy.
            *  5. Return the target.*/


            Node successor = findSuccessor(temp);               //1
            Node successorCopy = new Node(successor.getKey());  //2
            successorCopy.setLeft(temp.getLeft());              //3
            successorCopy.setRight(temp.getRight());
            successorCopy.setParent(temp.getParent());

            if(temp.getParent() != null){
                if(temp.getKey() < temp.getParent().getKey()){
                    temp.getParent().setLeft(successorCopy);
                }
                else{
                    temp.getParent().setRight(successorCopy);
                }
            }
            else{
                setRoot(successorCopy);
            }

            delete(successor, successor.getKey());              //4
            return temp;
        }
        else{
            if(temp.getParent() != null){
                if(temp.getRight() != null){
                    if(temp.getKey() < temp.getParent().getKey()){
                        temp.getParent().setLeft(temp.getRight());
                    }
                    else{
                        temp.getParent().setRight(temp.getRight());
                    }
                }
                else{
                    if(temp.getKey() < temp.getParent().getKey()){
                        temp.getParent().setLeft(temp.getLeft());
                    }
                    else{
                        temp.getParent().setRight(temp.getLeft());
                    }
                }
            }
            else{
                if(temp.getRight() != null){
                    setRoot(temp.getRight());
                }
                else{
                    setRoot(temp.getLeft());
                }
            }


            return temp;
        }
    }

    /**Finds the smallest Node in the right subtree.
     * @param root the root of the tree.
     *
     * O(logn)*/
    public Node findSuccessor(Node root){
        Node pointer = root.getRight();

        while(pointer.getLeft() != null){
            pointer = pointer.getLeft();
        }

        return pointer;
    }

    /**Traverses the tree inorder and prints it recursively.
     * @param root the root of the BST
     *
     * O(n)*/
    public void inorderRec(Node root){
        if(root != null){

            if(root.getLeft() != null){
                inorderRec(root.getLeft());
            }
            System.out.print(root.getKey() + " ");
            if(root.getRight() != null){
                inorderRec(root.getRight());
            }
        }
    }

    /**Finds the kth smallest element of the BST
     * @param root the root of the BST.
     * @param k the index of the element in the sorted tree to return.
     * @return the kth smallest element of the BST.
     * O(n + k)*/
    public Node kthSmallest(Node root, int k){
        listInOrder.clear();
        kthSmallesthelper(root);
        return getListInOrder().get(k);
    }

    /**Helper method for kthSmallest, performs the recursion
     *
     * O(n)*/
    private void kthSmallesthelper(Node root){
        if(root != null){
            if(root.getLeft() != null){
                kthSmallesthelper(root.getLeft());
            }
            addToListInOrder(root);
            if(root.getRight() != null){
                kthSmallesthelper(root.getRight());
            }
        }
    }

    /**A class setting up a node for the BST*/
    public class Node{
        private int key;
        private Node parent;
        private Node left;
        private Node right;

        //Getters and setters
        public int getKey(){return key;}
        public Node getLeft(){return left;}
        public Node getRight(){return right;}
        public Node getParent(){return parent;}

        public void setLeft(Node left){this.left = left;}
        public void setRight(Node right){this.right = right;}
        public void setParent(Node parent){this.parent = parent;}

        public Node(int key){
            this.key = key;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }
}
