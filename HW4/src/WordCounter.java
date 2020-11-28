/**A class that contains the wordCount method and helper methods*/
public class WordCounter {

    /**A method that takes a string input and prints all words alongside their frequency.
     * @param input the string input to be parsed, indexed and printed.*/
    public static void wordCount(String input){
        String[] wordList = split(input);                        // splits the input into words
        WordHashTable hashTable = new WordHashTable(71);    // chose 71 as the initial size of the array.
        for(String key: wordList){                               // each word in the list is treated as a key
            hashTable.addWord(key);                              // pass each key into the addWord method
            if(hashTable.loadFactor() >= 1){                     // condition to rehash the table
                hashTable.rehash();
            }
        }
        hashTable.printTable();
    }


    /**A method that parses the input string into a list of strings representing words.
     * @param input the input string to be parsed.
     * @return the list of words*/
    private static String[] split(String input){
        input = input.toLowerCase();
        return input.split("\\P{Alpha}");
    }
}
