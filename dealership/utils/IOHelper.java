package dealership.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IOHelper {
    public static char COMMENT_CHAR = '#';
    private final String rootDirPath;
    private final File rootDir;
    private final File[] files;

    /**
     * Instantiate an object of IOHelper with the specified directory.
     * The constructor attempts to open the specified directory and ensure that
     * it is a directory and is readable.
     *
     * @param rootDirPath path to the directory
     * @throws IllegalArgumentException if the specified path doesn't exist,
     * or it is not a directory, or it is not readable
     */
    public IOHelper(String rootDirPath) {
        if(rootDirPath == null || rootDirPath.trim().isEmpty()) {
            throw new IllegalArgumentException("Data directory cannot be null or an a empty string");
        }
        this.rootDirPath = rootDirPath.trim();
        this.rootDir = new File(rootDirPath);
        if(!rootDir.exists() || ! rootDir.isDirectory() || !rootDir.canRead()) {
            throw new IllegalArgumentException("Can't open data directory for read");
        }
        this.files = this.rootDir.listFiles();
    }
    public File[] getFiles() {
        return this.files;
    }

    /**
     * Get a File object in the directory with the specified name.
     *
     * @param fileName the name of the file. This should be the name of the file only,
     *                 without any path information.
     * @return a File object if a file (not a directory) exists in the root directory
     * if exists. Otherwise, if the name doesn't match an entry or the matching entry
     * is a directory, return null.
     */
    public File getFileByName(String fileName) {
        List<File> matchedFiles = Arrays.stream(this.files)
                .filter(f -> f.getName().equals(fileName))
                .collect(Collectors.toList());
        return matchedFiles.size() > 0 ? matchedFiles.get(0) : null;
    }

    /**

    /**
     * Read the content of the specified file. Each line is read and stored in String.
     *
     * @param fileName the name of file whose content is to be read
     * @param dropCommentLines indicates if comment lines (preceded by #) should bd dropped
     * @param dropEmptyLines indicates if empty lines (containing only whitespaces) should be dropped
     * @return an array of Strings, each corresponding with a line in the file
     * @throws IOException if the file cannot be opened for reading
     */
    public List<String> readFileContent(String fileName, boolean dropCommentLines, boolean dropEmptyLines) throws IOException {
        File f = getFileByName(fileName);
        if(f != null) {
            return readFileContent(f, dropCommentLines, dropEmptyLines);
        }
        return new ArrayList<>(0);
    }

    /**
     * Read the content of the specified file. Each line is read and stored in String.
     *
     * @param file the file whose content is to be read
     * @param dropCommentLines indicates if comment lines (preceded by #) should bd dropped
     * @param dropEmptyLines indicates if empty lines (containing only whitespaces) should be dropped
     * @return an array of Strings, each corresponding with a line in the file
     * @throws IOException if the file cannot be opened for reading
     */
    public List<String> readFileContent(File file, boolean dropCommentLines, boolean dropEmptyLines) throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader br = null;
        try {
            if(file.isFile() && file.canRead()) {
                br = new BufferedReader(new FileReader(file));
                String line = null;
                while ((line = br.readLine()) != null) {
                    if (dropEmptyLines && line.trim().length() == 0) {
                        continue;
                    }
                    if (dropCommentLines) {
                        String trimmedLine = line.trim();
                        if (!trimmedLine.isEmpty() && trimmedLine.charAt(0) == COMMENT_CHAR) {
                            continue;
                        }
                    }
                    lines.add(line);
                }
            }
        } finally {
            if(br != null) {
                br.close();
            }
        }
        return lines;
    }

    /**
     *
     * @param inventoryFileName
     */
    public void writeLines(String inventoryFileName, List<String> csvLines) throws IOException {
        File file = getFileByName(inventoryFileName);
        FileWriter fr = null;
        try {
            if(file.isFile() && file.canWrite()) {
                BufferedWriter br = new BufferedWriter(new FileWriter(file));
                for(String csvLine : csvLines) {
                    br.write(csvLine);
                    br.newLine();
                }
                br.flush();
            }
        } finally {
            if(fr != null) {
                fr.close();
            }
        }
    }
}
