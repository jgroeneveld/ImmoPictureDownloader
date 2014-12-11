package de.jgroeneveld;

import com.google.inject.Inject;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by jgroeneveld on 05.12.14.
 */
public class Application {
    private PictureDownloader pictureDownloader;
    private PictureUrlExtractorFactory pictureUrlExtractorFactory;
    private DocumentFetcher documentFetcher;
    private Presentation presentation;

    @Inject
    public Application(Presentation presentation, DocumentFetcher documentFetcher, PictureUrlExtractorFactory pictureUrlExtractorFactory, PictureDownloader pictureDownloader) {
        this.presentation = presentation;
        this.documentFetcher = documentFetcher;
        this.pictureDownloader = pictureDownloader;
        this.pictureUrlExtractorFactory = pictureUrlExtractorFactory;
    }

    public void run(AppArgs appArgs) {
        PictureUrlExtractor pictureUrlExtractor = pictureUrlExtractorFactory.build(appArgs);
        if (pictureUrlExtractor == null) {
            presentation.displayError("url '" + appArgs.sourceUrl + "' not supported (no urlExtractor available)");
            return;
        }

        try {
            Document doc = documentFetcher.fetchDocument(appArgs.sourceUrl);
            List<String> urls = pictureUrlExtractor.extract(doc);
            if (urls.size() > 0) {
                presentation.display("Found Picture Urls:");
                urls.stream().forEach(presentation::display);

                urls.parallelStream().forEach((urlString) -> {
                    try {
                        URL url = new URL(urlString);
                        pictureDownloader.download(appArgs.targetFolder, url);
                    } catch (IOException e) {
                        presentation.displayError("error reading image url " + urlString);
                        presentation.printStackTrace(e);
                    }
                });
            } else {
                presentation.display("No Picture Urls Found");
            }
        } catch (IOException e) {
            presentation.printStackTrace(e);
        }
    }

}
