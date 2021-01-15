import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * File: Assignment5Part4.java
 * <p>
 * Read a certain column of the csv file.
 */
public class CSVParsing {

    /* The name and location of the food-origins csv file. */
    private static final String FOOD_ORIGINS_FILE = "assets/food-origins.csv";


    public static void main(String[] args) {
        System.out.println(extractColumn(FOOD_ORIGINS_FILE, 0));
        System.out.println(extractColumn(FOOD_ORIGINS_FILE, 1));
    }

    /**
     * Read csv file and extract certain column from it. Columns count starts from 0.
     *
     * @param filename    name and location of file.
     * @param columnIndex index of column that would be extracted.
     * @return Arraylist that contain certain column from the file.
     */
    private static ArrayList<String> extractColumn(String filename, int columnIndex) {
        /* List contains all strings from the @filename*/
        ArrayList<String> stringsFromFile = new ArrayList<>();

        try {
            /* Open the file for reading. */
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            while (true) {
                String word = bufferedReader.readLine();
                /* no data left*/
                if (word == null)
                    break;
                stringsFromFile.add(word);
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Some problem with input");
        }
        /* List that contains the extracted column */
        ArrayList<String> extractedColumn = new ArrayList<>();

        for (String stringFromFile : stringsFromFile) {
            extractedColumn.add(fieldsIn(stringFromFile).get(columnIndex));
        }
        return extractedColumn;
    }

    /**
     * Comma-separated string
     *
     * @param line string of the file
     * @return List that contains as elements each separate semantic word or phrase
     */
    private static ArrayList<String> fieldsIn(String line) {
        /* return list */
        ArrayList<String> fieldsIn = new ArrayList<>();

        /* collect word by symbols(chars) */
        StringBuilder fieldBuilder = new StringBuilder();
        fieldBuilder.append("\"");
        /* determines whether the quote is closed or not. */
        boolean isQuoteClosed = true;
        for (int i = 0; i < line.length(); i++) {
            /* each char of word */
            char currentChar = line.charAt(i);

            if (currentChar == '"') {
                isQuoteClosed = !isQuoteClosed;

            } else if (currentChar == ',') {
                /* Add word to the list */
                if (isQuoteClosed) {
                    fieldBuilder.append("\"");
                    fieldsIn.add(fieldBuilder.toString());

                    /* start new word */
                    fieldBuilder = new StringBuilder();
                    fieldBuilder.append("\"");
                } else {
                    fieldBuilder.append(currentChar);
                }

            } else {
                fieldBuilder.append(currentChar);
            }

        }
        fieldBuilder.append("\"");

        fieldsIn.add(fieldBuilder.toString());
        return fieldsIn;
    }
}
