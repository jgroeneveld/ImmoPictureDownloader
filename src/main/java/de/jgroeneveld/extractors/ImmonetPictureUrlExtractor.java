package de.jgroeneveld.extractors;

import de.jgroeneveld.PictureUrlExtractor;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jgroeneveld on 28.11.14.
 */
public class ImmonetPictureUrlExtractor implements PictureUrlExtractor {
    @Override
    public List<String> extract(Document document) {
        return extractFromThumbs(document);
    }

    private List<String> extractFromThumbs(Document doc) {
        Elements elements = doc.select(".mediaThumbsSlider img");

        List<String> urls = elements.stream().map((element) -> {
            return fullPictureUrl(element.attr("src"));
        }).collect(Collectors.toList());

        return urls;
    }

    private String fullPictureUrl(String url) {
        return url.replaceFirst("_\\d+x\\d+.jpg$", ".jpg");
    }
}
