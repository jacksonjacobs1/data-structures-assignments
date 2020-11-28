/**A data structure used for the purpose of indexing the words input to wordCount*/
public class WordHashTable {
    /**A nested class defining the object stored in WordHashTable*/
    private class Entry implements Comparable<Entry>{
        @Override
        public int compareTo(Entry e2){
            if(this.getOccurrances() > e2.getOccurrances()){
                return -1;
            }
            else{
                return 1;
            }
        }

        private String key;
        private int occurrences;
        private Entry nextInChain;      // implementing separate chaining with a linked list.

        /**Getters for private fields*/
        private String getKey(){return this.key;}
        private int getOccurrances(){return this.occurrences;}
        private Entry getNextInChain(){return this.nextInChain;}

        /**Increments the occurrences of an entry in the hash table*/
        private void incrementOccurrences(){this.occurrences++;}
        /**Setter method for the next item in the chain
         * @param nextInChain the next entry.*/
        private void setNextInChain(Entry nextInChain){this.nextInChain = nextInChain;}

        /**Constructor for the Entry class
         * made private so that a new entry can only be created when called within WordHashTable
         * @param key the key of the entry*/
        private Entry(String key){
            this.key = key;
            this.occurrences = 1;
            this.nextInChain = null;
        }
    }

    // fields for WordHashTable
    private Entry[] table;
    private int tableSize;
    private int numOfEntries;

    /**Constructor for WordHashTable
     * @param size the initial size of the table*/
    public WordHashTable(int size){
        table = new Entry[size];
        tableSize = size;
        numOfEntries = 0;
    }

    /**Getters and setters for WordHashTable fields*/
    public void setTable(Entry[] table){this.table = table;}
    public Entry[] getTable(){return this.table;}

    public void setTableSize(int tableSize){this.tableSize = tableSize;}
    public int getTableSize(){return this.tableSize;}

    public void incrementNumOfEntries(){this.numOfEntries++;}
    private void resetNumEntries(){this.numOfEntries = 0;}
    public int getNumOfEntries(){return this.numOfEntries;}

    /**The hash function for WordHashTable
     * @param key the string to be converted to a position
     * @return the position in the hash table corresponding to the key*/
    private int h(String key){
        return Math.abs(key.hashCode()) % getTableSize();
    }

    /**Calculates the load factor of the table.
     * @return the load factor*/
    public double loadFactor(){
        return (double)getNumOfEntries()/getTableSize();
    }

    /**Searches for a particular key within the hash table. If the key is found, increments the occurrences of the entry*/
    public Entry search(String key){
        int hashcode = h(key);
        if(getTable()[hashcode] != null){      // checks whether an entry exists at the position
            Entry ptr = getTable()[hashcode];  // the pointer to increment through the linked list
            if(ptr.getKey().equals(key)){return ptr;}   // returns the first entry if its key matches the target key
            while(ptr.getNextInChain() != null){                    // terminates when end of linked list reached
                ptr = ptr.getNextInChain();
                if(ptr.getKey().equals(key)){return ptr;}   // returns the entry if its key matches the target
            }
            return ptr;     // returns the last entry in the chain
        }
        return null;        // return null if the position was null
    }

    /**Attempts to add a word to the table. If a matching entry already exists, increment occurrences.*/
    public void addWord(String key){
        Entry target = search(key);
        if(!key.equals("")) {
            if (target == null) {                         // if the position was empty
                getTable()[h(key)] = new Entry(key);        // create a new entry in that position
                incrementNumOfEntries();
            } else if (target.getKey().equals(key)) {       // if search found the position of the target
                target.incrementOccurrences();              // increment the occurrences of the target
            } else if(target.getNextInChain() == null){                                       // if search returned the last entry of the chain
                target.setNextInChain(new Entry(key));      // create a new entry at the end of the chain
                incrementNumOfEntries();
            }
        }
    }

    /**Method that updates the table size and rehashes the old values to the new table
     * @fixme Sets up the new table but still needs the rehashing function
     **/
    public void rehash(){
        Entry[] oldTableEntries = listAllEntries();

        setTableSize(getTableSize() * 2);
        resetNumEntries();
        setTable(new Entry[getTableSize()]);

        for(int i = 0; i < oldTableEntries.length; i++){
            addWord(oldTableEntries[i].getKey());
        }

    }


    /**Prints each element of the hash table in array form. Array is sorted by number of occurrences.*/
    public void printTable(){
        Entry[] copy = listAllEntries();
        bubbleSort(copy);
        for(Entry x:copy){
            System.out.println(x.getKey() + ": " + x.getOccurrances() + ", ");
        }
    }

    /**Generates an array of all the entries in the hashtable (no more linked lists!).
     * Helper method for print table and rehash*/
    private Entry[] listAllEntries(){
        Entry[] copyArray = new Entry[getNumOfEntries()];       // empty array to be copied into
        int openIndex = 0;
        for(int i = 0; i < getTableSize(); i++){                // for each slot of the table array
            if(getTable()[i] != null){                          // given the slot was not null
                Entry ptr = getTable()[i];
                copyArray[openIndex] = ptr;
                openIndex++;
                while(ptr.getNextInChain() != null){            // iterates down the chain to add entries to the list
                    ptr = ptr.getNextInChain();
                    copyArray[openIndex] = ptr;
                    openIndex++;
                }
            }
        }
        return copyArray;
    }

    /**A bubble sort algorithm for an array of entry objects
     * @param arr the array to be sorted*/
    private static void bubbleSort(Entry[] arr){
        for(int i = arr.length - 1; i > 0; i--){        // i is the index of the sorted region.
            for(int j = 0; j < i; j++){                 // j is the iterator that traverses the unsorted array for swaps
                if(arr[j].compareTo(arr[j + 1]) > 0){   // bubbles the larger item up
                    Entry temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    /**NOT USED
     * Helper method for rehash. Finds the next prime number after the input.*/
    private static int nextPrime(int input){
        int x = input;
        while(!isPrime(x)){        // increments x until it becomes prime
            x++;
        }
        return x;
    }

    /**NOT USED
     * Helper method for nextPrime. Checks if the input integer is prime*/
    private static boolean isPrime(int input){
        if(input == 2){
            return true;
        }
        else if(input < 2){
            return false;
        }
        else{
            for(int i = 2; i < input - 1; i++){     // tries to find an integer between 1 and input - 1 that divides input.
                if(input % i == 0){
                    return false;                   // if an integer is found, the input cannot be prime
                }
            }
            return true;                            // if not, the input must be prime
        }
    }
}
