import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Autocorrect
 * <p>
 * A command-line tool to suggest similar words when given one not in the dictionary.
 * </p>
 * @author Zach Blick
 * @author Damian  Villarreal-Ayala
 *
 * NOTES SECTION:
 * Potential ideas for checking differences:
 * - Count the num of changes that are present between the two words
 * ("you", "your", 1 change)
 *
 * - Using Levenshtein distance = edit distance
 * - Base Cases:
 *   - if length of String b = 0, distance = length of String a.
 *   - if length of String a = 0, distance = length of String b.
 *   - if last character of both strings is the same, distance is distance between strings - last character.
 *   - else, distance will be 1 + the minimum distance of the square to the left, above, and left diagonalally.
 *
 * - Can be solved using both tabulation and memoization
 *
 * - Currently using substring, but better to replace with something more efficient
 *  - Tries? Hashing or smth?
 * Test
 *  - Sort first by the word(alphabetically), and then sort them by edit distance
 */
public class Autocorrect {

    /**
     * Constructs an instance of the Autocorrect class.
     * @param words The dictionary of acceptable words.
     * @param threshold The maximum number of edits a suggestion can have.
     */
    public Autocorrect(String[] words, int threshold)
    {
//        String testWord = what should be user input
        String[] dictCopy = words;
        int thresholdCopy = threshold;



    }

    public int levenshteinDist(String string1, String string2)
    {
        int[][] inspectionTable = new int[string1.length() + 1][string2.length() + 1];

        for(int i = 0; i <= string1.length(); i++)
        {
            for(int j = 0; j <= string2.length(); j++)
            {
                //Base Case: if length of String a = 0, distance = length of String b.
                if(i == 0)
                {
                    inspectionTable[i][j] = j;
                }
                //Base Case: length of String b = 0, distance = length of String a.
                else if(j == 0)
                {
                    inspectionTable[i][j] = i;
                }
                //Base Case: if last character of both strings is the same, distance is distance between strings - last character
                else if(string1.charAt(i - 1) == string2.charAt(j -1))
                {
                    inspectionTable[i][j] = inspectionTable[i - 1][j - 1];
                }
            }
        }

        return inspectionTable[string1.length()][string2.length()];
    }

    /**
     * Runs a test from the tester file, AutocorrectTester.
     * @param typed The (potentially) misspelled word, provided by the user.
     * @return An array of all dictionary words with an edit distance less than or equal
     * to threshold, sorted by edit distance, then sorted alphabetically.
     */
    public String[] runTest(String typed) {

        return new String[0];
    }


    /**
     * Loads a dictionary of words from the provided textfiles in the dictionaries directory.
     * @param dictionary The name of the textfile, [dictionary].txt, in the dictionaries directory.
     * @return An array of Strings containing all words in alphabetical order.
     */
    private static String[] loadDictionary(String dictionary)  {
        try {
            String line;
            BufferedReader dictReader = new BufferedReader(new FileReader("dictionaries/" + dictionary + ".txt"));
            line = dictReader.readLine();

            // Update instance variables with test data
            int n = Integer.parseInt(line);
            String[] words = new String[n];

            for (int i = 0; i < n; i++) {
                line = dictReader.readLine();
                words[i] = line;
            }
            return words;
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}