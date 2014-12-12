package de.jgroeneveld.ipdl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by jgroeneveld on 05.12.14.
 */
public class DocumentFetcher {
    public Document fetchDocument(String url) throws IOException {
        return Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                .timeout(12000)
                .get();
    }
}
