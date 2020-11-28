public class Runner {
    public static void main(String[] args){
        // testing that insertion sort works
        int[] arr = Sort.RandomArray(20,-100,100);
        Sort.printArray(arr);
        Sort.InsertionSort(arr);
        System.out.println();
        Sort.printArray(arr);
        // test: SUCCESS

        // time the insertion sort for various n
        System.out.println("insertion sort times:");
        System.out.println(Runner.timer("insertion", 5));
        System.out.println(Runner.timer("insertion", 10));
        System.out.println(Runner.timer("insertion", 15));
        System.out.println(Runner.timer("insertion", 20));
        System.out.println(Runner.timer("insertion", 1000));
        System.out.println(Runner.timer("insertion", 2000));
        System.out.println(Runner.timer("insertion", 4000));
        System.out.println(Runner.timer("insertion", 8000));



        /*// testing that quick sort works
        int[] arr1 = Sort.RandomArray(20, -100,100);
        Sort.printArray(arr1);
        Sort.QuickSort(arr1);
        System.out.println();
        Sort.printArray(arr1);
        // time the quick sort for various n
        System.out.println("quick sort times:");
        System.out.println("   n=5: " + Runner.timer("quick", 5));
        System.out.println("  n=10: " + Runner.timer("quick", 10));
        System.out.println("  n=15: " + Runner.timer("quick", 15));
        System.out.println("  n=20: " + Runner.timer("quick", 20));
        System.out.println("n=1000: " + Runner.timer("quick", 1000));
        System.out.println("n=2000: " + Runner.timer("quick", 2000));
        System.out.println("n=4000: " + Runner.timer("quick", 4000));
        System.out.println("n=8000: " + Runner.timer("quick", 8000));*/





        // time the merge sort for various n
        System.out.println("merge sort times:");
        System.out.println(Runner.timer("merge", 5));
        System.out.println(Runner.timer("merge", 10));
        System.out.println(Runner.timer("merge", 15));
        System.out.println(Runner.timer("merge", 20));
        System.out.println(Runner.timer("merge", 1000));
        System.out.println(Runner.timer("merge", 2000));
        System.out.println(Runner.timer("merge", 4000));
        System.out.println(Runner.timer("merge", 8000));




    }

    /**Method to test how long it takes a specified type of sort to run
     * @param sortType the type of sort to be timed.
     *                 Inputs can be: "merge", "quick", or "insertion"
     * @param n the number of elements to be sorted
     * @return the time it took to sort.
     * */
    public static long timer(String sortType, int n){
        String type = sortType.toLowerCase();
        if(type.equals("merge") || type.equals("mergesort")){
            int[] array = Sort.RandomArray(n, -10000, 10000);   // creates a random array between -10000 and 10000
            long initialTime = System.nanoTime();       // the starting time
            Sort.MergeSort(array);                      // calls the sort
            long finalTime = System.nanoTime();         // the ending time
            return finalTime - initialTime;
        }
        else if(type.equals("quick") || type.equals("quicksort")){
            int[] array = Sort.RandomArray(n, -10000, 10000);
            long initialTime = System.nanoTime();
            Sort.QuickSort(array);
            long finalTime = System.nanoTime();
            return finalTime - initialTime;
        }
        else if(type.equals("insertion") || type.equals("insertionsort")){
            int[] array = Sort.RandomArray(n, -10000, 10000);
            long initialTime = System.nanoTime();
            Sort.InsertionSort(array);
            long finalTime = System.nanoTime();
            return finalTime - initialTime;
        }
        else{
            return 0;          // returns this if the input does not match one of the valid types
        }
    }
}
