package de.jgroeneveld.ipdl.testutil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by jgroeneveld on 28.11.14.
 */
public class Resources {
    public static Resources instance = new Resources();

    public String readResource(String path) {
        URL testFileUrl = getClass().getResource(path);
        if (testFileUrl == null) {
            throw new RuntimeException("Resource '" + path + "' not found");
        }

        File file = new File(testFileUrl.getFile());
        try {
            return readFile(file);
        } catch (IOException e) {
            throw new RuntimeException("Error reading Resource '" + path + "': " + e);
        }
    }

    private String readFile(File file) throws IOException {
        StringBuilder fileContents = new StringBuilder((int) file.length());
        String lineSeparator = System.getProperty("line.separator");

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine()).append(lineSeparator);
            }
            return fileContents.toString();
        }
    }
}
