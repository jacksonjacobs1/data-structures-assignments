public class Test {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(2);
        bst.insert(1);
        bst.insert(4);
        bst.insert(5);
        bst.insert(9);
        bst.insert(3);
        bst.insert(6);
        bst.insert(7);
        bst.insert(10);
        bst.insert(12);
        bst.insert(11);

        bst.inorderRec(bst.getRoot());
        bst.delete(bst.getRoot(), 4);
        bst.delete(bst.getRoot(), 9);
        System.out.println();
        bst.inorderRec(bst.getRoot());
        System.out.println();
        System.out.println(BinarySearchTree.search(bst.getRoot(), 12).getKey());
        //System.out.println(BinarySearchTree.search(bst.getRoot(), 4).getKey());
        //the above line search call returns null and so an error is thrown at .getKey()
        System.out.println(bst.kthSmallest(bst.getRoot(), 3).getKey());
    }
}
