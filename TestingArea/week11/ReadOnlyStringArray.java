package week11;


/**
 * Starter class for Basic Exception Handling exercises.
 * 
 * @author sam scott 
 */
public class ReadOnlyStringArray {

    private String[] contents;

    /**
     * Constructor to create an array from a variable length list of strings.
     *
     * @param contents
     */
    public ReadOnlyStringArray(String... contents) {
        this.contents = contents;
    }

    /**
     * Gets the contents of array element i
     *
     * @param i the array index
     * @return the contents at location i
     */
    public String get(int i) {
        return contents[i];
    }
}
