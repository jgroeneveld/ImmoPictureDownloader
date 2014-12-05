package de.jgroeneveld;

import com.google.inject.Inject;
import de.jgroeneveld.common.DocumentFetcher;
import de.jgroeneveld.common.PictureDownloader;
import de.jgroeneveld.common.PictureUrlExtractor;
import de.jgroeneveld.common.PictureUrlExtractorFactory;
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

    @Inject
    public Application(DocumentFetcher documentFetcher, PictureUrlExtractorFactory pictureUrlExtractorFactory, PictureDownloader pictureDownloader) {
        this.documentFetcher = documentFetcher;
        this.pictureDownloader = pictureDownloader;
        this.pictureUrlExtractorFactory = pictureUrlExtractorFactory;
    }

    public void run(AppArgs appArgs) {
        PictureUrlExtractor pictureUrlExtractor = pictureUrlExtractorFactory.build(appArgs);
        if (pictureUrlExtractor == null) {
            System.err.println("url '" + appArgs.sourceUrl + "' not supported (no urlExtractor available)");
            return;
        }

        try {
            Document doc = documentFetcher.fetchDocument(appArgs.sourceUrl);
            List<String> urls = pictureUrlExtractor.extract(doc);
            if (urls.size() > 0) {
                System.out.println("Found Picture Urls:");
                urls.stream().forEach(System.out::println);

                urls.parallelStream().forEach((urlString) -> {
                    try {
                        URL url = new URL(urlString);
                        pictureDownloader.download(appArgs.targetFolder, url);
                    } catch (IOException e) {
                        System.err.println("error reading image url " + urlString);
                        e.printStackTrace();
                    }
                });
            } else {
                System.out.println("No Picture Urls Found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
