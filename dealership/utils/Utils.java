package dealership.utils;

import java.io.Console;

public class Utils {
    private static final String DEFAULT_PROMPT = "Enter password:";
    private static final String CSV_SEPARATOR = ",";

    /**
     * Read and return password from the console. Please note that this is not
     * the right way to manage password in a real-world product since it poses
     * some security risk. Use this only for non-production code for testing
     * and experimentation.
     *
     * @param message message to be presented to the user. If null, a default
     *                message is used.
     * @return A string containing the user's password.
     */
    public static String readPasswordFromConsole(String message) {
        String prompt = message != null ? message : DEFAULT_PROMPT;

        Console console = System.console();

        if(console == null) {
            throw new RuntimeException("Console not available.");
        }
        char[] pwdArray = console.readPassword(prompt);

        // the array will be null if the user didn't enter any character,
        // i.e. pressed the return at the prompt.
        return pwdArray != null ? String.valueOf(pwdArray) :null;
    }

    /**
     * Parse a String containing csv content into individual strings. If no value appears
     * or after a comma, an empty string is stored in the resulting array. For example
     * ",a,b,,c,," will result in ["", "a", "b", "", "c", "", ""]
     *
     * @param csvContent string containing the csv content to be parsed
     * @param trimSpaces indicates whether parsed values should be trimmed,
     *                   which removes any surrounding whitespaces from them.
     * @return an array of string containing parsed values from the content.
     */
    public static String[] parseCSV(String csvContent, boolean trimSpaces) {
        if(csvContent == null || csvContent.trim().isEmpty()) {
            return new String[0];
        }
        String[] values = csvContent.trim().split(CSV_SEPARATOR);
        if(trimSpaces) {
            String[] trimmedValues = new String[values.length];
            for(int i = 0; i < values.length; i++){
                trimmedValues[i] = values[i].trim();
            }
            values = trimmedValues;
        }
        return values;
    }
}
