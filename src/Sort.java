public class Sort {
    /**A method implementing the InsertionSort algorithm
     * @param A the array to be sorted
     **/
    public static void InsertionSort(int[] A){
        for(int i = 1; i < A.length; i++){      // outer loop to get the element to sift
            int toInsert = A[i];
            int j = i;
            while(j > 0 && toInsert < A[j-1]){  // inner while loop to sift backwards
                A[j] = A[j-1];
                j--;
            }
            A[j] = toInsert;            // insert the target value
        }
    }

    /**A method implementing the QuickSort algorithm
     * @param A the array to be sorted
     **/
    public static void QuickSort(int[] A){
        myQuickSort(A,0,A.length - 1);
    }

    private static void myQuickSort(int[] arr, int start, int end){
        if(start >= end){return;}
        int partition = partition(arr, start, end);

        myQuickSort(arr, start, partition);             // recursive call on left sub-array
        myQuickSort(arr, partition + 1, end);     // recursive call on right sub-array
    }

    /**A helper method for QuickSort. Swaps elements so that their position is on the correct side of a given pivot.
     * @param arr the entire array on which the partition will occur
     * @param first the beginning of the subarray
     * @param last the end of the subarray*/
    private static int partition(int[] arr, int first, int last){
        int i = first;
        int j = last;
        int pivot = arr[last - 1]; //choosePivot(arr,first,last,3);

        while(i < j){
            while(arr[i] < pivot && i < last){i++;}
            while(arr[j] > pivot && j > 0){j--;}

            if(arr[i] > arr[j]){                // means the items at indices i and j should be swapped
                int placeholder = arr[i];
                arr[i] = arr[j];
                arr[j] = placeholder;
            }
        }
        return i;
    }

    /**Helper method for partition that chooses a pivot point. Better than a randomly chosen pivot point.
     * @param arr the array from which a pivot will be chosen
     * @param first the beginning of the sub-array
     * @param last the end of the sub-array
     * @param degree the size of the array of potential pivots
     * @return the median of the array of potential pivots*/
    private static int choosePivot(int[] arr, int first, int last, int degree){
        int[] randomItems = new int[degree];        // arr to be filled with an amount of random items
        for(int i = 0; i < randomItems.length; i++){
            int randomIndexInRange = (int)((Math.random() * (last-first + 2)) + first - 1); // finds a random index
            randomItems[i] = arr[randomIndexInRange];                                       // in the range [first, last]
        }
        InsertionSort(randomItems);        // quickly sorts the array of *degree* items
        return randomItems[randomItems.length / 2];     // returns the median item in this list
    }

    /**The Merge sort wrapper method. Used to create a temporary array and make the initial recursive call.
     * @param A the array to be sorted*/
    public static void MergeSort(int[] A){
        int[] temp = new int[A.length];
        myMergeSort(A, temp, 0, A.length - 1);
    }

    /**The recursive helper method of the MergeSort method
     * @param arr the array to be sorted
     * @param temp the temporary array
     * @param start the start of the subarray
     * @param end the end of the subarray*/
    public static void myMergeSort(int[] arr, int[] temp, int start, int end){
        if(start >= end){return;} // the base case

        int middle = (start + end)/2; // the splitting step

        //sort the first and second halves
        myMergeSort(arr, temp, start, middle);
        myMergeSort(arr, temp, middle + 1, end);

        merge(arr,temp,start,middle,middle+1,end);
    }

    /**Helper method for */
    public static void merge(int[] arr, int[] temp, int leftStart, int leftEnd, int rightStart, int rightEnd){
        int i = leftStart;
        int j = rightStart;
        int k = leftStart; // index into temp

        while(i <= leftEnd && j <= rightEnd){
            if(arr[i] <= arr[j]){
                temp[k] = arr[i];
                i++;
            }
            else{
                temp[k] = arr[j];
                j++;
            }
            k++;
            while(i <= leftEnd){       // copying remaining elements
                temp[k] = arr[i];
                i++;
                k++;
            }
        }
        for(i = leftStart; i <= rightEnd; i++){ // putting everything back sorted.
            arr[i] = temp[i];
        }
    }

    /**Method to visualize an array
     * @param arr the array to be printed*/
    public static void printArray(int[] arr){
        for(int x : arr){
            System.out.print(x + ", ");
        }
    }

    /**A method to return an array of a certain range of random integers
     * @param n the size of the array
     * @param a the minimum of the interval (inclusive)
     * @param b the maximum of the interval (inclusive)*/
    public static int[] RandomArray(int n, int a, int b){
        int[] output = new int[n];
        for(int i = 0; i < n; i++){
            output[i] = (int)((Math.random() * (b-a + 2)) + a - 1);     // makes sure interval is inclusive
        }
        return output;
    }
}
