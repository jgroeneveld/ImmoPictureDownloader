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
            throw new RuntimeException("Resource '" + path + "' not found");
        }
    }

    private String readFile(File file) throws IOException {
        StringBuilder fileContents = new StringBuilder((int) file.length());
        Scanner scanner = new Scanner(file);
        String lineSeparator = System.getProperty("line.separator");

        try {
            while (scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine() + lineSeparator);
            }
            return fileContents.toString();
        } finally {
            scanner.close();
        }
    }
}
