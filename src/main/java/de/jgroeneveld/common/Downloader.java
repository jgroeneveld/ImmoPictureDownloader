package de.jgroeneveld.common;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by jgroeneveld on 02.12.14.
 */
public class Downloader {
    private String targetFolder;

    public Downloader(String targetFolder) {
        this.targetFolder = targetFolder;
    }

    public void download(URL url) throws IOException {
        File destination = new File(targetFolder + "/" + getDestinationFileName(url));
        FileUtils.copyURLToFile(url, destination);
        System.out.println("Wrote " + url + " to " + destination.getAbsolutePath());
    }

    private String getDestinationFileName(URL url) {
        String filePath = url.getFile();
        File f = new File(filePath);
        return f.getName();
    }
}
