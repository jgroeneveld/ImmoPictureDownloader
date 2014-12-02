package de.jgroeneveld;

import de.jgroeneveld.common.Downloader;
import de.jgroeneveld.common.PictureUrlExtractor;
import de.jgroeneveld.immonet.ImmonetPictureUrlExtractor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AppArgs appArgs = new AppArgs(args);
//        appArgs.sourceUrl = "http://www.immonet.de/angebot/22740920?drop=sel";

        if (appArgs.hasErrors()) {
            System.err.println("Argument Error: " + appArgs.getErrorMessage());
            return;
        }

        run(appArgs);
    }

    private static void run(AppArgs appArgs) {
        Downloader downloader = new Downloader(appArgs.targetFolder);

        PictureUrlExtractor pictureUrlExtractor = getPictureUrlExtractor(appArgs);
        if (pictureUrlExtractor == null) {
            System.err.println("url '" + appArgs.sourceUrl + "' not supported");
            return;
        }

        try {
            Document doc = Jsoup.connect(appArgs.sourceUrl)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                    .timeout(12000)
                    .get();
            List<String> urls = pictureUrlExtractor.extract(doc);
            if (urls.size() > 0) {
                System.out.println("Found Picture Urls:");
                urls.stream().forEach(System.out::println);

                urls.parallelStream().forEach((urlString) -> {
                    try {
                        URL url = new URL(urlString);
                        downloader.download(url);
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

    private static PictureUrlExtractor getPictureUrlExtractor(AppArgs appArgs) {
        PictureUrlExtractor pictureUrlExtractor;
        if (appArgs.sourceUrl.contains("immonet")) {
            pictureUrlExtractor = new ImmonetPictureUrlExtractor();
        } else {
            return null;
        }
        return pictureUrlExtractor;
    }
}
