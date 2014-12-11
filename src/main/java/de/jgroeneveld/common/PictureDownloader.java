package de.jgroeneveld.common;

import com.google.inject.Inject;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by jgroeneveld on 02.12.14.
 */
public class PictureDownloader {
    Presentation presentation;

    @Inject
    public PictureDownloader(Presentation presentation) {
        this.presentation = presentation;
    }

    public void download(String targetFolder, URL url) throws IOException {
        File destination = new File(targetFolder + "/" + getDestinationFileName(url));
        FileUtils.copyURLToFile(url, destination);
        presentation.display("Wrote " + url + " to " + destination.getAbsolutePath());
        getDestinationFileName(url);
    }

    private String getDestinationFileName(URL url) {
        String filePath = url.getFile();
        File f = new File(filePath);
        return f.getName();
    }
}
