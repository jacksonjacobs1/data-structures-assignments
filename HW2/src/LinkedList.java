public class LinkedList<J> {
    private LLNode<J> head;
    private LLNode<J> tail;
    private int size;


    public int getSize(){return this.size;}
    private void incrementSize(boolean bigger){
        if(bigger){
            size++;
        }
        else{
            size--;
        }
    }
    public LLNode<J> getHead(){return this.head;}
    private void setHead(LLNode<J> head){this.head = head;}
    public LLNode<J> getTail(){return this.tail;}
    private void setTail(LLNode<J> tail){this.tail = tail;}

    /**
     * The Constructor
     **/
    public LinkedList(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void addToFront(LLNode<J> newNode){
        newNode.setNext(getHead());
        setHead(newNode);
        incrementSize(true);
    }

    public void reverseList(){
        // base case: next is null
        // approaching the bass case
        LLNode<J> temp1 = getHead();
        LLNode<J> temp2 = getHead().getNext();

    }

    private void recursiveCall(LLNode<J> root){
        if(root != null && root.getNext() != null){
            if(root.getNext() == null){
                root.getNext().setNext(root);
            }
            else{
                LLNode<J> rootChild = root.getNext();
                root.getNext().setNext(root);
                recursiveCall(rootChild);
            }
        }
    }

    public void printList(){
        System.out.print('{');
        recursivePrint(getHead());
    }

    private void recursivePrint(LLNode<J> root){
        if(root.getNext() != null){
            System.out.print(root.getElement() + ", ");
            recursivePrint(root.getNext());
        }
        else{
            System.out.print(root.getElement() + "}");
        }
    }

    class LLNode<T> {
        private T element;
        private LLNode<T> next;

        public LLNode(T element, LLNode<T> next){
            this.element = element;
            this.next = next;
        }

        public LLNode<T> getNext(){return this.next;}
        public T getElement(){return this.element;}

        public void setElement(T element) {this.element = element;}
        public void setNext(LLNode<T> next) {this.next = next;}
    }
    public static void main(String[] args){
        java.util.LinkedList<String> list1 = new java.util.LinkedList<>();
        list1.add("hello");
        System.out.println(list1.getFirst());

    }
}
