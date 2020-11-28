public class LinkedIntList {
    private LLNode head;
    private LLNode tail;
    private LLNode nodeptr;
    private int size;

    /**All getters and setters*/
    public int getSize(){return this.size;}
    private void incrementSize(boolean bigger){
        if(bigger){
            size++;
        }
        else{
            size--;
        }
    }
    public LLNode getHead(){return this.head;}
    private void setHead(LLNode head){this.head = head;}
    public LLNode getTail(){return this.tail;}
    private void setTail(LLNode tail){this.tail = tail;}

    /**Constructor*/
    public LinkedIntList(){
        head = null;
        tail = null;
        nodeptr = null;
        size = 0;
    }

    /**Reverses the list using recursion.
     * @param root the node whose child needs to be pointed to it.
     * @param rootChild the node whose next element is swapped to its predecessor
     *
     * O(n)
     **/
    public void recursiveReverse(LLNode root, LLNode rootChild){
        if(root != null && rootChild != null){
            if(rootChild.getNext() == null){
                rootChild.setNext(root);
                LLNode temp = getHead();
                setHead(getTail());
                setTail(temp);
            }
            else{
                LLNode rootGrandChild = rootChild.getNext();
                rootChild.setNext(root);
                if(root == getHead()){
                    root.setNext(null);
                }
                recursiveReverse(rootChild, rootGrandChild);
            }
        }
    }

    /**Adds an element to the front of the LinkedIntList
     * @param element the element to be added
     *
     * O(1)
     **/
    public void addToFront(int element){
        if(getSize() == 0){
            setHead(new LLNode(element, null));
            setTail(getHead());
            incrementSize(true);
        }
        else{
            setHead(new LLNode(element, getHead()));
            incrementSize(true);
        }
    }

    /**Prints the linked list using recursion
     *
     * O(n)
     **/
    public void printList(){
        System.out.print('{');
        recursivePrint(getHead());
    }

    /**Recursion helper method for printList()
     * @param root the current node to be printed.
     *
     * O(n)
     **/
    private void recursivePrint(LLNode root){
        if(root.getNext() != null){
            System.out.print(root.getElement() + ", ");
            recursivePrint(root.getNext());
        }
        else{
            System.out.print(root.getElement() + "}");
        }
    }

    /**Class for creating a node to be stored in the LinkedIntList
     **/
    class LLNode {
        private int element;
        private LLNode next;

        /**Constructor*/
        public LLNode(int element, LLNode next){
            this.element = element;
            this.next = next;
        }

        /**Getters and Setters*/
        public LLNode getNext(){return this.next;}
        public int getElement(){return this.element;}
        public void setElement(int element) {this.element = element;}
        public void setNext(LLNode next) {this.next = next;}
    }

    public static void main(String[] args){
        LinkedIntList list = new LinkedIntList();
        for(int n = 0; n < 5; n++){
            list.addToFront((int)Math.floor(Math.random() * 101));
        }
        list.printList();
        list.recursiveReverse(list.getHead(), list.getHead().getNext());
        list.printList();
    }
}
